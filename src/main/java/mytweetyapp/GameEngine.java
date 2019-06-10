package mytweetyapp;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/*
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 */


public class GameEngine extends Thread{
	
	String engineName;
	Map<String, Player> playerMap;
	
	public GameEngine(Map<String, Player> playerMap, String engineName) {
		this.engineName = engineName;
		this.playerMap = playerMap;
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
    	msg.setTo("All players");
    	msg.setHeader("StartOfGame");
    	msg.setContent("The game has started");
    	
    	channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	channel.close();
    	connection.close();
 
    }
    
    private synchronized void sendSingle(Player player) 
    		throws InterruptedException, IOException, TimeoutException 
    {
    	
    	String EXCHANGE_NAME = "cybergame_rabbitmq_topic";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "topic");
    	Message msg = new Message();
    	msg.setFrom("gameEngine");
    	msg.setTo("Player");
    	msg.setHeader("request for action");
    	msg.setContent("your action is requested");
    	
    	channel.basicPublish(EXCHANGE_NAME, "*.OneAgent.*", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	channel.close();
    	connection.close();
 
    }
    
    @Override
    public void run() 
    { 
        try {
        	sendBulk();
            while (true) { 
  
                // producing a message to send to the consumer 
                putMessage(); 
  
                // producer goes to sleep when the queue is full 
                sleep(1000); 
            } 
        } 
        catch (InterruptedException e) { 
        } 
    }

}
