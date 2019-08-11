package mytweetyapp;

import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import net.sf.tweety.logics.pl.syntax.Conjunction;
//import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

//import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/*
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 */

public class GameEngine extends Thread{
	
	String engineName;
	Map<String, Player> playerMap;
	int setupid, sessionid, runs, runid;
	public int ticks = 0;
	
	//private int num_attacks = 0;
	
	public Vector<Message> messageQueue = new Vector<Message>();
	private Map<Action, Integer> effectiveActionMap = new HashMap<Action, Integer>();
	List<String[]> gameData = new ArrayList<String[]>();
	
	
	
	public List<Action> setOfActions = new ArrayList<Action>();
	
	private void initialiseSetOfActions() {
		for(String player:playerMap.keySet()) {
			for(Action action:playerMap.get(player).setOfActions) {
				setOfActions.add(action);
			}
		}
	}
	
	public Set<Policy> setOfPolicies = new HashSet<Policy>();
	
	private void initialiseSetOfPolicies() {
		for(String player:playerMap.keySet()) {
			for(Policy policy:playerMap.get(player).setOfPolicies) {
				setOfPolicies.add(policy);
			}
		}
	}
	
	public static Map<String, Action> actionMap = new HashMap<String, Action>();
	private void initialiseActionMap() {
		for (Action action:setOfActions) {
			actionMap.put(action.actionName.getName(), action);
		}
	}
	
	public Map<String, Action> tempActionMap = new HashMap<String, Action>();
	public Map<String, Float> scoreMap = new HashMap<String, Float>();
	public Map<String, Integer> plaComplMap = new HashMap<String, Integer>();
	public Map<String, Integer> plaViolMap = new HashMap<String, Integer>();
	public Map<String, Integer> polComplMap = new HashMap<String, Integer>();
	public Map<String, Integer> polViolMap = new HashMap<String, Integer>();
	
	private void initialiseScores() {
		
		for (String playerName:playerMap.keySet()) {
			scoreMap.put(playerName, 0.0f);
		}

	}
		
	public Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	//public Set<PropositionalFormula> tempStateOfGame = new HashSet<PropositionalFormula>();
	
	private void initialiseStateOfGame() {
		for(Action action:setOfActions) {
			stateOfGame.addAll(action.preCondition);
		}
	}
	
	public synchronized void updateStateOfGame(String playerName, Action action) {
		try {
			stateOfGame.addAll(tempActionMap.get(playerName).postCondition);
			effectiveActionMap.put(tempActionMap.get(playerName), this.ticks + tempActionMap.get(playerName).effect);
			computeScore(playerName);
			stateOfGame.removeAll(tempActionMap.get(playerName).preCondition);
			//int oldValue = scoreMap.get(playerName);
			//scoreMap.replace(playerName, oldValue + tempActionMap.get(playerName).utility1 - tempActionMap.get(playerName).cost);
			tempActionMap.replace(playerName, action);
		}
		catch(Exception e) {
			tempActionMap.put(playerName, action);
		}
	}
	
	public synchronized void computeScore(String playerName) {
        //int num_role = 0;
        int num_action = 0;
        Action chosen_action = tempActionMap.get(playerName);
		for (String complayer:tempActionMap.keySet()){
			if (playerMap.get(complayer).role == playerMap.get(playerName).role){
				//num_role += 1;
				if(tempActionMap.get(playerName) == tempActionMap.get(complayer)){
                    num_action +=1;
				}
			}
		}
		
		if(num_action >1){
            float new_score = scoreMap.get(playerName) + chosen_action.utility1;
            scoreMap.replace(playerName, new_score);
		}
		if(num_action == 1){
			float new_score = scoreMap.get(playerName) + chosen_action.utility2;
            scoreMap.replace(playerName, new_score);
		}     
	}
	
	private boolean isPolicyViolated(Policy policy) {
		if (policy.modality.modality.contentEquals("Permitted")) {
			return false;
		}
		else if (policy.modality.modality.contentEquals("Obliged")) {
			if (stateOfGame.containsAll(policy.actionName.preCondition)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (stateOfGame.containsAll(policy.actionName.preCondition)) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	public GameEngine(/*Map<String, Player> playerMap,*/ String engineName, int runs, int setupid, int sessionid, int runid) {
		this.engineName = engineName;
		//this.playerMap = playerMap;
		this.runs = runs;
		this.setupid = setupid;
		this.sessionid = sessionid;
		this.runid = runid;
	}
	
	public synchronized void sendMessage(Channel channel, String playerName, Message message) throws Exception {
		String queue_name = playerName + "_" + Integer.toString(setupid) + "_" + Integer.toString(sessionid) + "_" + Integer.toString(runid);
		channel.queueDeclare(queue_name, false, false, false, null);
		channel.basicPublish("", queue_name, null, message.toJSON().getBytes("UTF-8"));
        System.out.println("GE [x] Sent '" + message.printMessage() + "'" + " to "+ playerName + ".");
	}
	    
    @Override
    public synchronized void run() {
    	Random rand = new Random();
    	
    	try {
    		ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();
	        channel.queueDelete("ge" + "_" + Integer.toString(setupid) + "_" + Integer.toString(sessionid) + "_" + Integer.toString(runid));
	        channel.queueDeclare("ge" + "_" + Integer.toString(setupid) + "_" + Integer.toString(sessionid) + "_" + Integer.toString(runid), false, false, false, null);
	        
    		initialiseSetOfActions();
    		initialiseSetOfPolicies();
    		initialiseStateOfGame();
    		initialiseActionMap();
    		initialiseScores();
        	
            Message msg = new Message();
            msg.msgNo = 0;
            msg.ticks = 0;
            msg.header = "inform-game-on";
            msg.content = "inform-game-on";
            msg.from = this.engineName;
            
          //sending to all players
            for (String player:playerMap.keySet()) {
            	System.out.println("INFORMING GAME START");
            	sendMessage(channel, player, msg);
            }
            //System.out.println(this.engineName + " sent to all players message: " + msg.printMessage());
            this.ticks +=1;
            
            msg.header = "inform-state";
            PropositionalFormula state_of_game = new Conjunction(stateOfGame);
        	msg.content = state_of_game.toString();
            msg.msgNo = this.ticks;
            msg.ticks = this.ticks;
        	
            //sending to all players
            System.out.println("REQUEST FOR ACTIONS --> SENDING STATE OF GAME");
            for (String player:playerMap.keySet()) {
            	sendMessage(channel, player, msg);
            }
            //System.out.println(this.engineName + " sent to all players message: " + msg.printMessage());
            this.ticks +=1;
        	
        	
        	while (this.ticks <= this.runs) {
        		
          		Set<Action> listOfActions = new HashSet<Action>();
				for(Action oneAction:effectiveActionMap.keySet()) {
					if(this.ticks == effectiveActionMap.get(oneAction)) {
						listOfActions.add(oneAction);
					}
				}
				effectiveActionMap.keySet().removeAll(listOfActions);
				
				for(Action oneAction:listOfActions) {
					stateOfGame.removeAll(oneAction.postCondition);
					stateOfGame.addAll(oneAction.preCondition);
				}
        		
    		    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
    		        String message = new String(delivery.getBody(), "UTF-8");
    		        Message contenu = Message.fromJSON(message);
    		        System.out.println("GE [x] Received '" + message + "' from " + contenu.from);
    		        
    		        if (contenu.content.contentEquals("pass")) {
        				System.out.println(contenu.from + " decided to pass");
        			}
        			else {
        				Action selectedAction = actionMap.get(contenu.content);
        				updateStateOfGame(contenu.from, selectedAction);
        				System.out.println(contenu.from + " new score is " + scoreMap.get(contenu.from));
        			}
    		    };
    		    
    		    wait(5);
    		    	
    		    channel.basicConsume("ge" + "_" + Integer.toString(setupid) + "_" + Integer.toString(sessionid) + "_" + Integer.toString(runid), true, deliverCallback, consumerTag -> { });
    		    
    		    state_of_game = new Conjunction(stateOfGame);
            	msg.content = state_of_game.toString();
            	msg.msgNo = this.ticks;
	            msg.ticks = this.ticks;
            	
                //inserting values into the database/csv file
        		for (String player:playerMap.keySet()) {
        			gameData.add(new String[] {Integer.toString(setupid), Integer.toString(sessionid), Integer.toString(runid), Integer.toString(this.ticks - 1), player, Float.toString(scoreMap.get(player))/*, Integer.toString(plaViolMap.get(player)), Integer.toString(plaComplMap.get(player))*/});
                }
                
	            //System.out.println("REQUEST FOR ACTIONS --> SENDING STATE OF GAME");
                for (String player:playerMap.keySet()) {
                	sendMessage(channel, player, msg);
                }
                
                int attack = 0;
                if (rand.nextDouble() < 0.03) {
                	attack = 1;
                	System.out.println("THERE HAS BEEN AN ATTACK");
                	//num_attacks +=1;
                }
                
                if (attack == 1) {
                	//System.out.println("RECALCULATING PLAYERS SCORE");
                	for (String player:playerMap.keySet()) {
                		float max_penalty = 0.0f;
                		for(Policy policy:playerMap.get(player).setOfPolicies) {
                			if (isPolicyViolated(policy)) {
                				try {
                					plaViolMap.put(player, plaViolMap.get(player) + 1);
                				}
                				catch(Exception e) {
                					plaViolMap.put(player, 1);
                				}
                				try {
                					polViolMap.put(policy.actionName.actionName.getName(), polViolMap.get(policy.actionName.actionName.getName()) + 1);
                				}
                				catch(Exception e) {
                					polViolMap.put(policy.actionName.actionName.getName(), 1);
                				}
                				if(policy.punishment > max_penalty) {
                					max_penalty = policy.punishment;
                				}
                			}
                			else {
                				try {
                					plaComplMap.put(player, plaComplMap.get(player) + 1);
                				}
                				catch(Exception e) {
                					plaComplMap.put(player, 1);
                				}
                				try {
                					polComplMap.put(policy.actionName.actionName.getName(), polComplMap.get(policy.actionName.actionName.getName()) + 1);
                				}
                				catch(Exception e) {
                					polComplMap.put(policy.actionName.actionName.getName(), 1);
                				}
                			}
                		}
                		
                		scoreMap.put(player, scoreMap.get(player) * (1 - max_penalty));
                	}
                }
                
//            	for (String player:playerMap.keySet()) {
////            		float max_penalty = 0.0f;
//            		for(Policy policy:playerMap.get(player).setOfPolicies) {
//            			if (isPolicyViolated(policy)) {
//            				try {
//            					plaViolMap.put(player, plaViolMap.get(player) + 1);
//            				}
//            				catch(Exception e) {
//            					plaViolMap.put(player, 1);
//            				}
//            				try {
//            					polViolMap.put(policy.actionName.actionName.getName(), polViolMap.get(policy.actionName.actionName.getName()) + 1);
//            				}
//            				catch(Exception e) {
//            					polViolMap.put(policy.actionName.actionName.getName(), 1);
//            				}
////            				if(policy.punishment > max_penalty) {
////            					max_penalty = policy.punishment;
////            				}
//            			}
//            			else {
//            				try {
//            					plaComplMap.put(player, plaComplMap.get(player) + 1);
//            				}
//            				catch(Exception e) {
//            					plaComplMap.put(player, 1);
//            				}
//            				try {
//            					polComplMap.put(policy.actionName.actionName.getName(), polComplMap.get(policy.actionName.actionName.getName()) + 1);
//            				}
//            				catch(Exception e) {
//            					polComplMap.put(policy.actionName.actionName.getName(), 1);
//            				}
//            			}
//            		}
//            		
////            		scoreMap.put(player, scoreMap.get(player) * (1 - max_penalty));
//            	}
                
                this.ticks +=1;
                wait(5);
        	}
        	
        	msg.header = "inform-game-off";
            msg.content = "inform-game-off";
            
            System.out.println("INFORMING GAME END");
            for (String player:playerMap.keySet()) {
            	sendMessage(channel, player, msg);
            }
            
            //Util.writeDataAtOnce("game_" + Integer.toString(setupid) + "_" + Integer.toString(sessionid) + "_" + Integer.toString(runid) + ".csv",gameData);
            
            File file = new File("results.csv");

	        if (file.createNewFile()) {
	            System.out.println("File has been created.");  
	        }
	        else {
	            System.out.println("File already exists.");
	        }

            Float average = 0.0f;
            Float stdev = 0.0f;
            for (String player:scoreMap.keySet()) {
            	average += scoreMap.get(player);
            }
            
            average /= scoreMap.size();
            
            for (String player:scoreMap.keySet()) {
            	stdev += (scoreMap.get(player) - average) * (scoreMap.get(player) - average);
            }
            
            stdev /= scoreMap.size();
            stdev = (float) Math.sqrt(stdev);
            
            List<String> rolesList = new ArrayList<String>();
            List<Float> scoreList = new ArrayList<Float>();
            List<String> policyList = new ArrayList<String>();
            List<Integer> plaVioList = new ArrayList<Integer>();
            List<Integer> plaCompList = new ArrayList<Integer>();
            List<Integer> polVioList = new ArrayList<Integer>();
            List<Integer> polCompList = new ArrayList<Integer>();
            for(String player:playerMap.keySet()) {
            	rolesList.add(playerMap.get(player).role);
            	scoreList.add(scoreMap.get(player));
            	if(plaViolMap.containsKey(player)) {
            		plaVioList.add(plaViolMap.get(player));
            	}
            	else {
            		plaVioList.add(0);
            	}
            	if(plaComplMap.containsKey(player)) {
            		plaCompList.add(plaComplMap.get(player));
            	}
            	else {
            		plaCompList.add(0);
            	}
            }
            
            for(Policy policy:setOfPolicies) {
            	policyList.add(policy.actionName.actionName.getName());
            	if(polViolMap.containsKey(policy.actionName.actionName.getName())) {
            		polVioList.add(polViolMap.get(policy.actionName.actionName.getName()));
            	}
            	else {
            		polVioList.add(0);
            	}
            	if(polComplMap.containsKey(policy.actionName.actionName.getName())) {
            		polCompList.add(polComplMap.get(policy.actionName.actionName.getName()));
            	}
            	else {
            		polCompList.add(0);
            	}
            }
            
            String[] data = {Float.toString(average), Float.toString(stdev), rolesList.toString(), scoreList.toString(), plaVioList.toString(), plaCompList.toString(), policyList.toString(), polVioList.toString(), polCompList.toString()};
            FileWriter outputfile = new FileWriter(file,true);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(data);
            writer.close();
            
            channel.queueDelete("ge" + "_" + Integer.toString(setupid) + "_" + Integer.toString(sessionid) + "_" + Integer.toString(runid));
            channel.close();
		    connection.close();
        	
    	}
    	
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }   

}
