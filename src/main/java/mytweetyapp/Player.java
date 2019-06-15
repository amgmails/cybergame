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
    	
    	//try (Connection connection = factory.newConnection();
          //      Channel channel = connection.createChannel()) {
    		
    		Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
    		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
    		channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
    		//channel.basicPublish(EXCHANGE_NAME, "", null, Util.fromJavaToJson(msg).getBytes());
    		//ObjectMapper mapper = new ObjectMapper();
            //String jsonString = mapper.writeValueAsString(msg);
            //channel.basicPublish(EXCHANGE_NAME, "", null, jsonString.getBytes("UTF-8"));
        	System.out.println("Message is sent: " + msg.toString());
    	//}
    }
    
    
//    private void receive(Message message) throws Exception {
//    	Message msg = new Message();
//    	msg.setFrom(this.playerName);
//    	msg.setTo("gameEngine");
//    	msg.setHeader("action");
//    	String header = message.header;
//    	String content = message.content;
//    	int ticks = message.ticks;
//    	
//    	if (header == "inform-state") {
//    		System.out.println(header);
//    		if (ticks == msg.ticks) {
//    			
//            	PlParser parser = new PlParser();
//            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula(content);
//        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getPredicates());
//				int actionSize = availableActions.size();
//				List<Action> listactions = new ArrayList<Action>(availableActions);
//				Random rand = new Random();
//				int numChoice = rand.nextInt(actionSize); 
//				Action action = listactions.get(numChoice);
//				msg.setContent(action.actionName.getName());
//				sendSingle(msg);
//        	    msg.ticks += 1;
//        	    msg.msgNo +=1;
//    		}
//    		else {
//				msg.setContent("pass");
//				sendSingle(msg);
//        	    msg.ticks += 1;
//        	    msg.msgNo +=1;
//    		}
//    	}
//    	else {
//    	} 	 	
//    }

    
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
        	    //ObjectMapper mapper = new ObjectMapper();
        	    //Message messageobject = mapper.readValue(message, Message.class);
        	    System.out.println(this.playerName + " Received: " + Message.fromJSON(message).toString());
        	    //System.out.println(this.playerName + " Received: " + messageobject.toString());
        	    if (Message.fromJSON(message).header.toString().contentEquals("inform-game-on")) {
        	    //if (messageobject.header.contentEquals("inform-game-on")) {
    				System.out.println(this.playerName +": just an information, i do nothing");

    			}
    			else {
    				System.out.println("In the situation where game content is not <<inform-game-on>>");
    				System.out.println(String.format("Message ticks :%o, msg.ticks: %o", Message.fromJSON(message).ticks, msg.ticks));
    				if (Message.fromJSON(message).ticks != msg.ticks) {
    				//if (messageobject.ticks != msg.ticks) {
    					
    					System.out.println("Ticks do not coincide, i decide to pass");
    					msg.setContent("pass");
    				}
    				else {
    					System.out.println("In the situation where game content is not <<inform-game-on>> and ticks coincide");
    	            	PlParser parser = new PlParser();
    	            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula(Message.fromJSON(message).content.toString());
    	        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getLiterals());
    					//Set<Action> availableActions = availableActions(setOfActions, state_of_game.getPredicates());
    					//Set<PropositionalFormula> state_of_game =  (Set<PropositionalFormula>) messageobject.content;
    					//Set<Action> availableActions = availableActions(setOfActions, state_of_game);
    					int actionSize = availableActions.size();
    					List<Action> listactions = new ArrayList<Action>(availableActions);
    					Random rand = new Random();
    					int numChoice = rand.nextInt(actionSize); 
    					Action action = listactions.get(numChoice);
    					msg.setContent(action.actionName.getName().toString());
    					//msg.setContent(action);
    				}
    			}
        	    
        	    try {
        	    sendSingle(msg);
        	    msg.ticks += 1;
        	    msg.msgNo +=1;
//        	    	receive(Message.fromJSON(message));
        	    }
        	    catch (Exception e){
        	    }
        	};	
       	
        	while (true) {
        		
        		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
//        		Queue.DeclareOk feedback = channel.queueDeclarePassive(queueName);
//        		System.out.println(feedback.toString());
//        		
//        		while (feedback.getMessageCount() < 1) {
//        			wait(1000);
//        			feedback = channel.queueDeclarePassive(queueName);
//        		}
//        		
//        		for (int i = 0; i < feedback.getMessageCount(); i++) {
//	        		boolean autoAck = true;
//	    			GetResponse response = channel.basicGet(queueName, autoAck);
//	    			byte[] body = response.getBody();
//	    			String message = new String(body);
//	    			System.out.println(this.playerName + " Received: " + Message.fromJSON(message).toString());
//	    			
//	    			if (Message.fromJSON(message).header == "inform-game-on") {
//	    				System.out.println("Just an information, i do nothing");
//		        	    msg.ticks += 1;
//		        	    msg.msgNo +=1;
//	    			}
//	    			else {
//	    				if (Message.fromJSON(message).ticks != msg.ticks) {
//	    					System.out.println("Ticks do not coincide, i decide to pass");
//	    					msg.setContent("pass");
//	    					sendSingle(msg);
//	    	        	    msg.ticks += 1;
//	    	        	    msg.msgNo +=1;
//	    				}
//	    				else {
//	    	            	PlParser parser = new PlParser();
//	    	            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula(Message.fromJSON(message).content);
//	    	        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getPredicates());
//	    					int actionSize = availableActions.size();
//	    					List<Action> listactions = new ArrayList<Action>(availableActions);
//	    					Random rand = new Random();
//	    					int numChoice = rand.nextInt(actionSize); 
//	    					Action action = listactions.get(numChoice);
//	    					msg.setContent(action.actionName.getName());
//	    					sendSingle(msg);
//	    	        	    msg.ticks += 1;
//	    	        	    msg.msgNo +=1;
//	    				}
//	    			}
//        		}

        	}
    	}
    	catch (Exception e) {	
    	}
    }

}
