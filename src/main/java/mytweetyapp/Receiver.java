package mytweetyapp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


public class Receiver extends Thread {
	
	public synchronized void run() {
		try {
			String QUEUE_NAME = this.getName();
		    ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);	
		    System.out.println(" [*] Waiting for messages. To exit press CTRL+C on queue " + QUEUE_NAME);
	
		    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
		        String message = new String(delivery.getBody(), "UTF-8");
		        System.out.println(" [x] Received '" + message + "'");
		        channel.queueDeclare("sender", false, false, false, null);
		        String returnmessage = this.getName() + " tells you what goes around comes around";
		        channel.basicPublish("", "sender", null, returnmessage.getBytes("UTF-8"));
		        System.out.println(" [x] sent '" + returnmessage + "'");
		    	
		    };
		    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
