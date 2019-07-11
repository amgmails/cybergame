package mytweetyapp;

import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Sender extends Thread{
	Map<String, Receiver> receiverMap;
	
	public Sender(Map<String, Receiver> receiverMap) {
		this.receiverMap = receiverMap;
	}
	
	public synchronized void run() {
		
		try {
			
			while (true) {
				String QUEUE_NAME = "logs";
		        ConnectionFactory factory = new ConnectionFactory();
		        factory.setHost("localhost");
		        Connection connection = factory.newConnection();
		        Channel channel = connection.createChannel();
		        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		        for (String receiver:receiverMap.keySet()) {
			        String message = "maixent___" + receiver;
			        channel.basicPublish("", receiver, null, message.getBytes("UTF-8"));
			        System.out.println(" [x] Sent '" + message + "'" + " to "+ receiver);
			        wait(1000);
		        }
		        channel.queueDeclare("sender", false, false, false, null);
		        
			    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			        String message = new String(delivery.getBody(), "UTF-8");
			        System.out.println(" [x] Received '" + message + "'");
			    };
			    channel.basicConsume("sender", true, deliverCallback, consumerTag -> { });
			}
		}
        
        catch (Exception e) {
        	e.printStackTrace();
        }
            
      
    }
}
