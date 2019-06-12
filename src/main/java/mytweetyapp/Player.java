package mytweetyapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.lang.InterruptedException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DeliverCallback;

import net.sf.tweety.logics.pl.parser.PlParser;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;


/**
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 * https://www.geeksforgeeks.org/asynchronous-synchronous-callbacks-java/
 */


public class Player extends Thread{
	
	Set<Action> setOfActions;
	String playerName;
	Integer ticks = 0;
	
	public Player(Set<Action> setOfActions, String playerName) {
		this.playerName = playerName;
		this.setOfActions = setOfActions;
	}
	
	
    private synchronized void sendSingle(Message msg) 
    		throws InterruptedException, IOException, TimeoutException 
    {
    	
    	String EXCHANGE_NAME = "cybergame_gameengine";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "direct");
    	
    	channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	channel.close();
    	connection.close();
 
    }
    
    private synchronized Set<Action> availableActions(Set<Action> setOfActions, Set stateOfGame){
		//Set<Action> results = new HashSet<Action>();
		
		/**
		 * check if the postcondition is in the state. It is better to base it on the precondition
		*/
		//for (Action action:setOfActions) {
			//if (!stateOfGame.contains(action.postCondition)) {
				//results.add(action);
			//}
		//}
		
		//return results;
    	return setOfActions;
	}
    
    /**
    * Some part are taken from the book Mastering RabbitMQ. Others are inspired by geeks for geeks
    */
    
    public void run() {
    	
    	try {

        	String EXCHANGE_NAME = "cybergame_players";
        	ConnectionFactory factory = new ConnectionFactory();
        	factory.setHost("localhost");
        	Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
        	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        	String queueName = channel.queueDeclare().getQueue();
        	channel.queueBind(queueName, EXCHANGE_NAME, "");
        	System.out.println(this.playerName + ": "+ "Waiting for the messages.........");
        	//DefaultConsumer consumer = new DefaultConsumer(channel);
        	//channel.basicConsume(queueName, true, consumer);
        	
        	Message msg = new Message();
        	msg.setFrom(this.playerName);
        	msg.setTo("gameEngine");
        	msg.setHeader("action");
        	msg.setContent("pass");
        	
       	
        	while (true) {
        		
        		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	        String message = new String(delivery.getBody(), "UTF-8");
        	        //System.out.println(" [x] Received '" + message + "'");
        	        System.out.println("Received: " + Message.fromJSON(message).toString());
        	        if (Message.fromJSON(message).header == "inform-state") {
        	        	if (Message.fromJSON(message).ticks != ticks) {
        	        		msg.setContent("pass");
        	        	    ticks +=1;
        	        	    msg.msgNo +=1;
        	        	}
        	        	else {
        	            	PlParser parser = new PlParser();
        	            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula( (String) Message.fromJSON(message).content);
        	        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getPredicates());
        					int actionSize = availableActions.size();
        					List<Action> listactions = new ArrayList<Action>(availableActions);
        					Random rand = new Random();
        					int numChoice = rand.nextInt(actionSize); 
        					Action action = listactions.get(numChoice);
        					msg.setContent(action.actionName.getName());
        	        	    ticks +=1;
        	        	    msg.msgNo +=1;
        					
        	        	}
        	        	
        	        }
        	    };
        	    
        	    int queueMessageSize=channel.queueDeclarePassive(queueName).getMessageCount();
        	    
        	    if (queueMessageSize == 1) {
            	    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
            	    sendSingle(msg);
        	    }
        	    

        	}
    	}
    	catch (IOException e) {	
    	}
    	catch (ConsumerCancelledException e) {
    	}
    	catch (TimeoutException e) {
    	}
    	catch (InterruptedException e) {
    	}
    }

}
