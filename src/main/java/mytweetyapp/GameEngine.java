package mytweetyapp;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.GetResponse;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.Conjunction;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;


/*
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 */


public class GameEngine extends Thread{
	
	String engineName;
	Map<String, Player> playerMap;
	public int ticks = 0;
	
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
	
	public static Action reportWriting = new Action(new Proposition("reportWriting"), 20, 8, reportNotWritten, reportWritten);
	public static Action excelWork = new Action(new Proposition("excelWork"), 10, 4, excelSheetNotDone, excelSheetDone);
	public static Action treatSensitiveFolder = new Action(new Proposition("treatSensitiveFolder"), 10, 4, sensitiveFolderNotTreated, sensitiveFolderTreated);
	
	
	public static Set<Action> setOfActions = new HashSet<Action>();
	
	Map<String, Action> actionMap = new HashMap<String, Action>();
	
	private void initialiseSetOfActions() {
		setOfActions.add(reportWriting);
		setOfActions.add(excelWork);
		setOfActions.add(treatSensitiveFolder);
		
		actionMap.put(reportWriting.actionName.getName(), reportWriting);
		actionMap.put(excelWork.actionName.getName(), excelWork);
		actionMap.put(treatSensitiveFolder.actionName.getName(), treatSensitiveFolder);
		
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
	
	public GameEngine(Map<String, Player> playerMap, String engineName) {
		this.engineName = engineName;
		this.playerMap = playerMap;
	}
	
    private synchronized void sendBulk(Message msg) 
    		throws InterruptedException, IOException, TimeoutException 
    {
    	
    	String EXCHANGE_NAME = "cybergame_players";
    	ConnectionFactory factory = new ConnectionFactory();
    	factory.setHost("localhost");
    	Connection connection = factory.newConnection();
    	Channel channel = connection.createChannel();
    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
    	
    	channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
    	
    	System.out.println("Message is sent: " + msg.toString());
    	
    	//channel.close();
    	//connection.close();
 
    }

    
    @Override
    public void run() {
    	
    	try {
    		initialiseSetOfActions();
    		initialiseSetOfPolicies();
    		initialiseStateOfGame();
        	String QUEUE_NAME = "cybergame_gameengine";
        	ConnectionFactory factory = new ConnectionFactory();
        	factory.setHost("localhost");
        	Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
        	channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        	System.out.println("Game engine Waiting for the messages.........");
        	
        	Message msg = new Message();
        	msg.setFrom("gameEngine");
        	msg.setTo("players");
        	msg.setHeader("inform-game-on");
        	msg.setContent("inform-game-on");
        	msg.setTicks(ticks);
        	
        	sendBulk(msg);
        	//ticks +=1;
        	//msg.msgNo +=1;
        	
        	msg.setHeader("inform-state");
        	PropositionalFormula state_of_game = new Conjunction(stateOfGame);
        	msg.setContent(state_of_game.toString());
        	msg.setTicks(ticks);
        	
        	sendBulk(msg);
        	this.ticks +=1;
        	msg.msgNo +=1;
        	
        	while (true) {
        		        		
        		int queueMessageSize=channel.queueDeclarePassive(QUEUE_NAME).getMessageCount();
        		
        		if (queueMessageSize == this.playerMap.size()) {
        			for (int i = 0; i < queueMessageSize ; i++ ) {
        				GetResponse response = channel.basicGet(QUEUE_NAME, true);
        				byte[] body = response.getBody();
        				String message = new String(body);
        				System.out.println("Game engine Received: " + Message.fromJSON(message).toString());
        				
        				if (Message.fromJSON(message).content == "pass") {
        					sendBulk(msg);
        		        	this.ticks +=1;
        		        	msg.msgNo +=1;
            	        }
        				else {
            				Action selectedAction = actionMap.get(Message.fromJSON(message).content);
            	        	updateStateOfGame(selectedAction);
            	        	System.out.println("The action selected by player is: " + Message.fromJSON(message).content);
                    		msg.setTicks(ticks);
                    		state_of_game = new Conjunction(stateOfGame);
                    		msg.setContent(state_of_game.toString());
                    		sendBulk(msg);
                    	    this.ticks += 1;
                    	    msg.msgNo +=1;
        				}

        	        	
        			}
        		}

        	}
    	}
    	catch (IOException e) {	
    	}
    	catch (ConsumerCancelledException e) {
    	}
    	catch (TimeoutException e) {
    	}
    	catch (InterruptedException e) {
    	}
    }
    
    
    

}
