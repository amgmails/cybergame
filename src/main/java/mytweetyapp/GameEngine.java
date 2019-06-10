package mytweetyapp;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/*
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 */


public class GameEngine extends Thread{
	
    private synchronized void sendBulk() 
    		throws InterruptedException, IOException, TimeoutException 
    {
    	
    	String EXCHANGE_NAME = "cybergame_rabbitmq";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    	Message msg = new Message();
    	msg.setFrom("gameEngine");
    	msg.setTo("Nicky");
    	msg.setHeader("Hello World");
    	msg.setContent("Hello World Again");
    	
    	channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	channel.close();
    	connection.close();
 
    }
    
    private synchronized void sendSingle() 
    		throws InterruptedException, IOException, TimeoutException 
    {
    	
    	String EXCHANGE_NAME = "cybergame_rabbitmq";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    	Message msg = new Message();
    	msg.setFrom("gameEngine");
    	msg.setTo("Nicky");
    	msg.setHeader("Hello World");
    	msg.setContent("Hello World Again");
    	
    	channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	channel.close();
    	connection.close();
 
    }

}
