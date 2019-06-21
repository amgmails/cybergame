package mytweetyapp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
//import com.rabbitmq.client.GetResponse;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.Conjunction;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

//import org.codehaus.jackson.map.ObjectMapper;


/*
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 */


public class GameEngine extends Thread{
	
	String engineName;
	Map<String, Player> playerMap;
	
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
	
   
    private void sendBulk(Message msg) throws Exception {

        String EXCHANGE_NAME = "cybergame_players";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
            
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes("UTF-8"));
        System.out.println("Message is sent: " + msg.toString());
        
    }

    
    @Override
    public void run() {
    	
    	try {
    		initialiseSetOfActions();
    		initialiseSetOfPolicies();
    		initialiseStateOfGame();
    		initialiseScores();
        	String EXCHANGE_NAME = "cybergame_gameengine";
        	ConnectionFactory factory = new ConnectionFactory();
        	factory.setHost("localhost");
        	Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
        	channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        	String queueName = channel.queueDeclare().getQueue();
        	channel.queueBind(queueName, EXCHANGE_NAME, "");
        	System.out.println("Game engine Waiting for the messages.........on queue " + queueName);
        	
        	Message msg = new Message();
        	msg.setFrom("gameEngine");
        	msg.setTo("players");
        	msg.setHeader("inform-game-on");
        	msg.setContent("inform-game-on");
        	
        	sendBulk(msg);
        	
        	msg.setHeader("inform-state");
        	PropositionalFormula state_of_game = new Conjunction(stateOfGame);
        	msg.setContent(state_of_game.toString());
        	
        	msg.ticks +=1;
        	msg.msgNo +=1;
        	
        	sendBulk(msg);
        	
        	msg.ticks +=1;
        	msg.msgNo +=1;
        	
        	
        	DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	    String message = new String(delivery.getBody(), "UTF-8");
        	    System.out.println("Game engine Received: " + Message.fromJSON(message).toString());

        	    if (Message.fromJSON(message).content.toString().contentEquals("pass")) {
    				System.out.println("Player "+ Message.fromJSON(message).from + " decided to pass");

    			}
    			else {

    	        	Action selectedAction = actionMap.get(Message.fromJSON(message).content);
    	        	updateStateOfGame(selectedAction);
    	        	updateScore(Message.fromJSON(message).from, selectedAction);

    	        	System.out.println("Player " + Message.fromJSON(message).from + " new score is " + scoreMap.get(Message.fromJSON(message).from).toString());
    			}
        	};
        	
        	
        	while (!setOfActions.isEmpty()) {
        		Queue.DeclareOk feedback = channel.queueDeclarePassive(queueName);

        		if (feedback.getMessageCount() == playerMap.size()) {
	        		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
	        		System.out.println(stateOfGame.toString() + " is state of game");
		        	state_of_game = new Conjunction(stateOfGame);
	            	msg.setContent(state_of_game.toString());
	            	sendBulk(msg);
	        	    msg.ticks += 1;
	        	    msg.msgNo +=1;
        	    
        		}
        		
        	}
    	}
    	catch (Exception e) {	
    		System.out.println("Error: " + e.toString());
    		
    	}
    }   

}
