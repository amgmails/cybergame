package mytweetyapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//import com.rabbitmq.client.AMQP.Queue;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.DeliverCallback;
//import com.rabbitmq.client.GetResponse;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.Conjunction;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

//import org.codehaus.jackson.map.ObjectMapper;


/*
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 */


public class GameEngine extends Thread{
	
	String engineName;
	Map<String, Player> playerMap;
	
	Properties properties = new Properties();
	
	public static PropositionalFormula start = new Proposition("start");
	public static PropositionalFormula end = new Proposition("end");
	public static PropositionalFormula top = new Proposition("top");
	public static PropositionalFormula bottom = new Proposition("bottom");
	
	public static PropositionalFormula reportNotWritten = new Proposition("reportNotWritten");
	public static PropositionalFormula reportWritten = new Proposition("reportWritten");
	
	public static PropositionalFormula excelSheetNotDone = new Proposition("excelSheetNotDone");
	public static PropositionalFormula excelSheetDone = new Proposition("excelSheetDone");
	
	public static PropositionalFormula sensitiveFolderNotTreated = new Proposition("sensitiveFolderNotTreated");
	public static PropositionalFormula sensitiveFolderTreated = new Proposition("sensitiveFolderTreated");

	public static PropositionalFormula ANotDone = new Proposition("ANotDone");
	public static PropositionalFormula ADone = new Proposition("ADone");
	
	public static PropositionalFormula BNotDone = new Proposition("BNotDone");
	public static PropositionalFormula BDone = new Proposition("BDone");
	
	public static PropositionalFormula CNotDone = new Proposition("CNotDone");
	public static PropositionalFormula CDone = new Proposition("CDone");
	
	public static Action reportWriting = new Action(new Proposition("reportWriting"), 20, 8, reportNotWritten, reportWritten);
	public static Action excelWork = new Action(new Proposition("excelWork"), 10, 6, excelSheetNotDone, excelSheetDone);
	public static Action treatSensitiveFolder = new Action(new Proposition("treatSensitiveFolder"), 13, 4, sensitiveFolderNotTreated, sensitiveFolderTreated);
	public static Action A = new Action(new Proposition("A"), 25, 15, ANotDone, ADone);
	public static Action B = new Action(new Proposition("B"), 19, 10, BNotDone, BDone);
	public static Action C = new Action(new Proposition("C"), 8, 1, CNotDone, CDone);
	
	
	public static Set<Action> setOfActions = new HashSet<Action>();
	
	public static Map<String, Action> actionMap = new HashMap<String, Action>();
	public static Map<String, Integer> scoreMap = new HashMap<String, Integer>();
	
	private void initialiseSetOfActions() {
		setOfActions.add(reportWriting);
		setOfActions.add(excelWork);
		setOfActions.add(treatSensitiveFolder);
		setOfActions.add(A);
		setOfActions.add(B);
		setOfActions.add(C);
		
		actionMap.put(reportWriting.actionName.getName(), reportWriting);
		actionMap.put(excelWork.actionName.getName(), excelWork);
		actionMap.put(treatSensitiveFolder.actionName.getName(), treatSensitiveFolder);
		actionMap.put(A.actionName.getName(), A);
		actionMap.put(B.actionName.getName(), B);
		actionMap.put(C.actionName.getName(), C);
	}
	
	private void initialiseScores() {
		
		for (String playerName:playerMap.keySet()) {
			scoreMap.put(playerName, 0);
		}

	}

	
	public static Policy obligedToTreatSensitiveFolder = new Policy(new Modality("Obliged"), treatSensitiveFolder, sensitiveFolderNotTreated, sensitiveFolderTreated);
	
	public static Set<Policy> setOfPolicies = new HashSet<Policy>();
	
	private void initialiseSetOfPolicies() {
		setOfPolicies.add(obligedToTreatSensitiveFolder);
	}
		
	public static Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	
	private void initialiseStateOfGame() {
		stateOfGame.add(start);
	}
	
	public void updateStateOfGame(Action action) {
		stateOfGame.add(action.postCondition);
	}
	
	public void updateScore(String playerName, Action action) {
		int oldValue = scoreMap.get(playerName);
		scoreMap.replace(playerName, oldValue + action.benefit - action.cost);
	}
	
	public GameEngine(Map<String, Player> playerMap, String engineName) {
		this.engineName = engineName;
		this.playerMap = playerMap;
	}
	    
    @Override
    public void run() {
    	
    	try {
    		initialiseSetOfActions();
    		initialiseSetOfPolicies();
    		initialiseStateOfGame();
    		initialiseScores();
        	System.out.println("Game engine Waiting for the messages.........");

            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("hello.properties"));
            Context context = new InitialContext(properties);
            System.out.println("context done");
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("qpidConnectionFactory");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
            System.out.println("session created without problems");
            
            Topic players = (Topic) context.lookup("players");
            MessageProducer messageProducer = session.createProducer(players);
            System.out.println("game engine messageproducer created without problems");
            
            Queue ge = (Queue) context.lookup("gameengine");
            MessageConsumer messageConsumer = session.createConsumer(ge);
            System.out.println("game engine messageconsumer created without problems");
        	
            Message msg = session.createMessage();
            msg.setIntProperty("msgNo", 0);
            msg.setIntProperty("ticks", 0);
            msg.setStringProperty("header", "inform-game-on");
            msg.setStringProperty("content", "inform-game-on");
            msg.setStringProperty("from", "gameengine");
            
            messageProducer.send(msg);
            session.commit();
            System.out.println(this.engineName + " sent message: " + msg.toString());
            msg.setIntProperty("msgNo", msg.getIntProperty("msgNo") + 1);
            msg.setIntProperty("ticks", msg.getIntProperty("ticks") + 1);
        	       	
        	msg.setStringProperty("header", "inform-state");
        	PropositionalFormula state_of_game = new Conjunction(stateOfGame);
        	msg.setStringProperty("content", state_of_game.toString());
        	
            messageProducer.send(msg);
            session.commit();
            System.out.println(this.engineName + " sent message: " + msg.toString());
            msg.setIntProperty("msgNo", msg.getIntProperty("msgNo") + 1);
            msg.setIntProperty("ticks", msg.getIntProperty("ticks") + 1);
        	
        	
        	while (!setOfActions.isEmpty()) {
        		
        		for (int i = 0; i < playerMap.size(); i++) {
        			Message message = messageConsumer.receive(1000);
        			session.commit();
        			System.out.println(this.engineName +" Received from " + message.getStringProperty("from") + " message: " + message.toString());
        			
        			if (message.getStringProperty("content").contentEquals("pass")) {
        				System.out.println("Player "+ message.getStringProperty("from") + " decided to pass");
        			}
        			else {
        	        	Action selectedAction = actionMap.get(message.getStringProperty("content"));
        	        	updateStateOfGame(selectedAction);
        	        	updateScore(message.getStringProperty("from"), selectedAction);

        	        	System.out.println("Player " + message.getStringProperty("from") + " new score is " + scoreMap.get(message.getStringProperty("from")).toString());
        			}
        		}
        		
        		System.out.println(stateOfGame.toString() + " is state of game");
	        	state_of_game = new Conjunction(stateOfGame);
	        	msg.setStringProperty("content", state_of_game.toString());
	        	
	            messageProducer.send(msg);
	            session.commit();
	            System.out.println(this.engineName + " sent message: " + msg.toString());
	            msg.setIntProperty("msgNo", msg.getIntProperty("msgNo") + 1);
	            msg.setIntProperty("ticks", msg.getIntProperty("ticks") + 1);
        		
        	}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		//System.out.println("Error: " + e.toString());
    		
    	}
    }   

}
