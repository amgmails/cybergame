package mytweetyapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

//import org.codehaus.jackson.map.ObjectMapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
//import com.rabbitmq.client.GetResponse;
//import com.rabbitmq.client.AMQP.Queue;

import net.sf.tweety.logics.pl.parser.PlParser;
//import net.sf.tweety.logics.pl.syntax.Conjunction;
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
    	
    	String EXCHANGE_NAME = "cybergame_gameengine";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    		
		Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());

    	System.out.println("Message is sent: " + msg.toString());
    	//}
    }

    
    private Set<Action> availableActions(Set<Action> setOfActions, Set<PropositionalFormula> stateOfGame){
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
        	System.out.println(this.playerName + ": "+ "Waiting for the messages.........on queue " + queueName);
        	
        	Message msg = new Message();
        	msg.setFrom(this.playerName);
        	msg.setTo("gameEngine");
        	msg.setHeader("action");
        	msg.setContent("pass");
        	msg.ticks =0;
        	msg.msgNo =0;
        	
        	DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	    String message = new String(delivery.getBody(), "UTF-8");

        	    System.out.println(this.playerName + " Received: " + Message.fromJSON(message).toString());

        	    if (Message.fromJSON(message).header.toString().contentEquals("inform-game-on")) {
    				//System.out.println(this.playerName +": just an information, i do nothing");

    			}
    			else {

    				if (Message.fromJSON(message).ticks != msg.ticks) {

    					msg.setContent("pass");
    				}
    				else {
    					System.out.println("In the situation where game content is not <<inform-game-on>> and ticks coincide");
    	            	PlParser parser = new PlParser();
    	            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula(Message.fromJSON(message).content.toString());
    	        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getLiterals());
    	        		
    					int actionSize = availableActions.size();
    					List<Action> listactions = new ArrayList<Action>(availableActions);
    					Random rand = new Random();
    					int numChoice = rand.nextInt(actionSize); 
    					Action action = listactions.get(numChoice);
    					msg.setContent(action.actionName.getName().toString());

    				}
    			}
        	    
        	    try {
        	    sendSingle(msg);
        	    msg.ticks += 1;
        	    msg.msgNo +=1;

        	    }
        	    catch (Exception e){
        	    }
        	};	
       	
        	while (!setOfActions.isEmpty()) {
        		
        		
        		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

        	}
    	}
    	catch (Exception e) {	
    		System.out.println("Error: " + e.toString());
    	}
    }

}
