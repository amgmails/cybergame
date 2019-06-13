package mytweetyapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
	
	public Player(Set<Action> setOfActions, String playerName) {
		this.playerName = playerName;
		this.setOfActions = setOfActions;
	}
	
	
    private void sendSingle(Message msg) throws Exception {
    	
    	String QUEUE_NAME = "cybergame_gameengine";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	
    	try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {
    		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    		channel.basicPublish("", QUEUE_NAME, null, msg.toJSON().getBytes());
        	System.out.println("Message is sent: " + msg.toString());
    	}
    }
    
    
    private void receive(Message message) throws Exception {
    	Message msg = new Message();
    	msg.setFrom(this.playerName);
    	msg.setTo("gameEngine");
    	msg.setHeader("action");
    	String header = message.header;
    	String content = message.content;
    	int ticks = message.ticks;
    	
    	if (header == "inform-state") {
    		if (ticks == msg.ticks) {
            	PlParser parser = new PlParser();
            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula(content);
        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getPredicates());
				int actionSize = availableActions.size();
				List<Action> listactions = new ArrayList<Action>(availableActions);
				Random rand = new Random();
				int numChoice = rand.nextInt(actionSize); 
				Action action = listactions.get(numChoice);
				msg.setContent(action.actionName.getName());
				sendSingle(msg);
        	    msg.ticks += 1;
        	    msg.msgNo +=1;
    		}
    		else {
				msg.setContent("pass");
				sendSingle(msg);
        	    msg.ticks += 1;
        	    msg.msgNo +=1;
    		}
    	}
    	else {
    	} 	 	
    }

    
    private Set<Action> availableActions(Set<Action> setOfActions, Set stateOfGame){
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
        	
        	Message msg = new Message();
        	msg.setFrom(this.playerName);
        	msg.setTo("gameEngine");
        	msg.setHeader("action");
        	msg.setContent("pass");
        	msg.ticks =1;
        	
        	DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	    String message = new String(delivery.getBody(), "UTF-8");
        	    System.out.println(this.playerName + " Received: " + Message.fromJSON(message).toString());
        	    try {
        	    	receive(Message.fromJSON(message));
        	    }
        	    catch (Exception e){
        	    }
        	};
       	
        	while (true) {
        		
        		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { System.out.println(consumerTag.toString());});       	    

        	}
    	}
    	catch (Exception e) {	
    	}
    }

}
