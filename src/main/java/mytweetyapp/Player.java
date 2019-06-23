package mytweetyapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

//import org.codehaus.jackson.map.ObjectMapper;

//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.DeliverCallback;
//import com.rabbitmq.client.GetResponse;
//import com.rabbitmq.client.AMQP.Queue;

import net.sf.tweety.logics.pl.parser.PlParser;
//import net.sf.tweety.logics.pl.syntax.Conjunction;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;


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

        	System.out.println(this.playerName + ": "+ "Waiting for the messages.........");
        	
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("hello.properties"));
            Context context = new InitialContext(properties);
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("qpidConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
        	
            Topic players = (Topic) context.lookup("players");
            MessageConsumer subscriber = session.createDurableSubscriber(players, this.playerName);
            
            Queue ge = (Queue) context.lookup("gameengine");
            MessageProducer messageProducer = session.createProducer(ge);
            
            Message msg = session.createMessage();
            msg.setIntProperty("msgNo", 0);
            msg.setIntProperty("ticks", 0);
            msg.setStringProperty("header", "action");
            msg.setStringProperty("content", "pass");
            msg.setStringProperty("from", this.playerName);	
       	
        	while (!setOfActions.isEmpty()) {
        		
    			Message message = subscriber.receive(1000);
    			session.commit();
    			System.out.println(this.playerName +" Received from " + message.getStringProperty("from") + " message: " + message.toString());
    			
    			if (message.getStringProperty("header").contentEquals("inform-game-on")) {
    				System.out.println(this.playerName +": just an information, i do nothing");
    			}
    			else {

    				if (message.getIntProperty("ticks") != msg.getIntProperty("ticks")) {
    					msg.setStringProperty("content", "pass");
    				}
    				else {
    					//System.out.println("In the situation where game content is not <<inform-game-on>> and ticks coincide");
    	            	PlParser parser = new PlParser();
    	            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula(message.getStringProperty("content"));
    	        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getLiterals());
    	        		
    					int actionSize = availableActions.size();
    					List<Action> listactions = new ArrayList<Action>(availableActions);
    					Random rand = new Random();
    					int numChoice = rand.nextInt(actionSize); 
    					Action action = listactions.get(numChoice);
    					msg.setStringProperty("content", action.actionName.getName().toString());

    				}
    			}
    			
                messageProducer.send(msg);
                session.commit();
                System.out.println(this.playerName + " sent message: " + msg.toString());
                msg.setIntProperty("msgNo", msg.getIntProperty("msgNo") + 1);
                msg.setIntProperty("ticks", msg.getIntProperty("ticks") + 1);

        	}
    	}
    	catch (Exception e) {	
    		System.out.println("Error: " + e.toString());
    	}
    }

}
