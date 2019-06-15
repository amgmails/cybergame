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
	
//    private /*synchronized*/ void sendBulk(Message msg) 
//    		throws InterruptedException, IOException, TimeoutException 
//    {
//    	
//    	String EXCHANGE_NAME = "cybergame_players";
//    	ConnectionFactory factory = new ConnectionFactory();
//    	factory.setHost("localhost");
//    	Connection connection = factory.newConnection();
//    	Channel channel = connection.createChannel();
//    	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//    	
//    	channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes());
//    	
//    	System.out.println("Message is sent: " + msg.toString());
//    	
//    	channel.close();
//    	connection.close();
// 
//    }
    
    private void sendBulk(Message msg) throws Exception {

        String EXCHANGE_NAME = "cybergame_players";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        //try (Connection connection = factory.newConnection();
             //Channel channel = connection.createChannel()) {
            
        	Connection connection = factory.newConnection();
        	Channel channel = connection.createChannel();
        
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.toJSON().getBytes("UTF-8"));
            //channel.basicPublish(EXCHANGE_NAME, "", null, Util.fromJavaToJson(msg).getBytes("UTF-8"));
            //ObjectMapper mapper = new ObjectMapper(); // taken on https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/
            //String jsonString = mapper.writeValueAsString(msg);
            //System.out.println("i have reached there "+ jsonString);
            //channel.basicPublish(EXCHANGE_NAME, "", null, jsonString.getBytes("UTF-8"));
            System.out.println("Message is sent: " + msg.toString());
        //}
        
    }
    

    
//    private void receive(Message message) throws Exception {
//    	Message msg = new Message();
//    	msg.setFrom("gameEngine");
//    	msg.setTo("players");
//    	msg.setHeader("inform-state");
//    	String content = message.content;
//    	
//    	if (content == "pass") {
//    		sendBulk(msg);
//        	msg.ticks +=1;
//        	msg.msgNo +=1;
//    	}
//    	else {
//        	System.out.println("The action selected by player is: " + content);
//        	Action selectedAction = actionMap.get(content);
//        	updateStateOfGame(selectedAction);
//        	PropositionalFormula state_of_game = new Conjunction(stateOfGame);
//        	msg.setContent(state_of_game.toString());
//        	sendBulk(msg);
//    	    msg.ticks += 1;
//    	    msg.msgNo +=1;
//    	} 	
//    	
//    }

    
    @Override
    public void run() {
    	
    	try {
    		initialiseSetOfActions();
    		initialiseSetOfPolicies();
    		initialiseStateOfGame();
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
        	//ticks +=1;
        	//msg.msgNo +=1;
        	
        	msg.setHeader("inform-state");
        	PropositionalFormula state_of_game = new Conjunction(stateOfGame);
        	msg.setContent(state_of_game.toString());
        	//msg.setContent(stateOfGame);
        	
        	msg.ticks +=1;
        	msg.msgNo +=1;
        	
        	sendBulk(msg);
        	
        	msg.ticks +=1;
        	msg.msgNo +=1;
        	
        	
        	DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	    String message = new String(delivery.getBody(), "UTF-8");
        	    //ObjectMapper mapper = new ObjectMapper();
        	    //Message messageobject = mapper.readValue(message, Message.class);
        	    System.out.println("Game engine Received: " + Message.fromJSON(message).toString());
        	    //System.out.println("Game engine Received: " + Util.fromJsonToJava(message, Message.class).toString());
        	    //System.out.println("Game engine Received: " + messageobject.toString());
        	    if (Message.fromJSON(message).content.toString().contentEquals("pass")) {
        	    //if (messageobject.content.toString().contentEquals("pass")) {
    				System.out.println("Player "+ Message.fromJSON(message).from + " decided to pass");
        	    	//System.out.println("Player "+ messageobject.from + " decided to pass");
    			}
    			else {
    	        	System.out.println("Player " + Message.fromJSON(message).from + " selected action " +  Message.fromJSON(message).content);
    				//Action selectedAction = (Action) messageobject.content;
    				//System.out.println("Player " + messageobject.from + " selected action " +  selectedAction.actionName);
    	        	Action selectedAction = actionMap.get(Message.fromJSON(message).content);
    	        	updateStateOfGame(selectedAction);
    	        	System.out.println("State of game updated with action " + selectedAction.actionName);
    			}
        	};
        	
        	
        	while (true) {
        		Queue.DeclareOk feedback = channel.queueDeclarePassive(queueName);
        		//System.out.println(feedback.getMessageCount());
        		if (feedback.getMessageCount() == playerMap.size()) {
	        		channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
	//        		Queue.DeclareOk feedback = channel.queueDeclarePassive(queueName);
	//        		
	//        		while (feedback.getMessageCount() < playerMap.size()) {
	//        			System.out.println(feedback.getMessageCount());
	//        			feedback = channel.queueDeclarePassive(queueName);
	//        		}
	//        		
	//        		for (int i = 0; i < feedback.getMessageCount(); i++) {
	//        			boolean autoAck = true;
	//        			GetResponse response = channel.basicGet(queueName, autoAck);
	//        			byte[] body = response.getBody();
	//        			String message = new String(body);
	//        			System.out.println("Game engine Received: " + Message.fromJSON(message).toString());
	//        			
	//        			if (Message.fromJSON(message).content == "pass") {
	//        				System.out.println("Player "+ Message.fromJSON(message).from + " decided to pass");
	//        			}
	//        			else {
	//        	        	System.out.println("else branch Player " + Message.fromJSON(message).from + " selected action " +  Message.fromJSON(message).content);
	//        	        	Action selectedAction = actionMap.get(Message.fromJSON(message).content);
	//        	        	updateStateOfGame(selectedAction);
	//        	        	state_of_game = new Conjunction(stateOfGame);
	//        	        	System.out.println("State of game updated");
	//        			}
	//        		}
	        		
		        	state_of_game = new Conjunction(stateOfGame);
	            	msg.setContent(state_of_game.toString());
	        		//msg.setContent(stateOfGame);
	            	sendBulk(msg);
	        	    msg.ticks += 1;
	        	    msg.msgNo +=1;
        	    
        		}
        		
        	}
    	}
    	catch (Exception e) {	
    	}
    }   

}
