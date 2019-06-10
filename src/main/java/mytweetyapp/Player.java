package mytweetyapp;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.lang.InterruptedException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.Delivery;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DeliverCallback;


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
	
	
    private synchronized void sendBulk() 
    		throws InterruptedException, IOException, TimeoutException 
    {
    	
    	String EXCHANGE_NAME = "cybergame_rabbitmq_fanout";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    	Message msg = new Message();
    	msg.setFrom("gameEngine");
    	msg.setTo("AllAgents");
    	msg.setHeader("Hello World");
    	msg.setContent("Hello World Again");
    	
    	channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	channel.close();
    	connection.close();
 
    }
    
    private synchronized void sendSingle(GameEngine gameEngine) 
    		throws InterruptedException, IOException, TimeoutException 
    {
    	
    	String EXCHANGE_NAME = "cybergame_rabbitmq_topic";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    	Message msg = new Message();
    	msg.setFrom("OneAgent");
    	msg.setTo("gameEngine");
    	msg.setHeader("Hello World");
    	msg.setContent("Hello World Again");
    	
    	channel.basicPublish(EXCHANGE_NAME, "*.OneAgent.*", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	channel.close();
    	connection.close();
 
    }
    
    private synchronized Set<Action> availableActions(Set<Action> setOfActions, Set<PropositionalFormula> stateOfGame){
		Set<Action> results = new HashSet<Action>();
		
		/**
		 * check if the postcondition is in the state. It is better to base it on the precondition
		*/
		for (Action action:setOfActions) {
			if (!stateOfGame.contains(action.postCondition)) {
				results.add(action);
			}
		}
		
		return results;
	}
    
    /**
    * Some part are taken from the book Mastering RabbitMQ. Others are inspired by geeks for geeks
    */
    
    public void run() {
    	
    	try {
    		
        	String EXCHANGE_NAME_fanout = "cybergame_rabbitmq_fanout";
        	String EXCHANGE_NAME_topic = "cybergame_rabbitmq_topic";
        	ConnectionFactory factory = new ConnectionFactory();
        	factory.setHost("localhost");
        	Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
        	channel.exchangeDeclare(EXCHANGE_NAME_fanout, "fanout");
        	channel.exchangeDeclare(EXCHANGE_NAME_topic, "topic");
        	String queueName = channel.queueDeclare().getQueue();
        	channel.queueBind(queueName, EXCHANGE_NAME_fanout, "");
        	channel.queueBind(queueName, EXCHANGE_NAME_topic, this.playerName);
        	System.out.println("Waiting for the messages.........");
        	//DefaultConsumer consumer = new DefaultConsumer(channel);
        	//channel.basicConsume(queueName, true, consumer);
        	
        	while (true) {
        		
        		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	        String message = new String(delivery.getBody(), "UTF-8");
        	        //System.out.println(" [x] Received '" + message + "'");
        	        System.out.println("Received: " + Message.fromJSON(message).toString());
        	        
        	        if (Message.fromJSON(message).content == "your action is requested") {
        	        	
        	        }
        	    };
        	    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        		/*Delivery delivery = consumer.nextDelivery();
        		String msg = new String(delivery.getBody());
        		System.out.println("Received: " + Message.fromJSON(msg).toString());
        		*/
        	}
    	}
    	catch (IOException e) {	
    	}
    	catch (ConsumerCancelledException e) {
    	}
    	catch (TimeoutException e) {
    	}
    }

}
