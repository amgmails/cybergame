package mytweetyapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import net.sf.tweety.logics.pl.syntax.Conjunction;
import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

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
	int runs = 100;
	int setupid, sessionid;
	private int ticks = 0;
	
	private int num_attacks = 0;
	
	public Vector<Message> messageQueue = new Vector<Message>();
	private Map<Action, Integer> effectiveActionMap = new HashMap<Action, Integer>();
	
	public static PropositionalFormula start = new Proposition("start");
	public static PropositionalFormula end = new Proposition("end");
	public static PropositionalFormula top = new Proposition("top");
	public static PropositionalFormula bottom = new Proposition("bottom");
	
	public static PropositionalFormula descriptionsNOK = new Proposition("descriptionsNOK");
	public static PropositionalFormula descriptionsOK = new Proposition("descriptionsOK");
	
	public static PropositionalFormula speccharactersNOK = new Proposition("speccharactersNOK");
	public static PropositionalFormula speccharactersOK = new Proposition("speccharactersOK");
	
	public static PropositionalFormula advertisementNOK = new Proposition("advertisementNOK");
	public static PropositionalFormula advertisementOK = new Proposition("advertisementOK");
	
	public static PropositionalFormula securecredentialsNOK = new Proposition("securecredentialsNOK");
	public static PropositionalFormula securecredentialsOK = new Proposition("securecredentialsOK");
	
	public static PropositionalFormula buildawarenessNOK = new Proposition("buildawarenessNOK");
	public static PropositionalFormula buildawarenessOK = new Proposition("buildawarenessOK");
	
	public static PropositionalFormula resolvecomplaintsNOK = new Proposition("resolvecomplaintsNOK");
	public static PropositionalFormula resolvecomplaintsOK = new Proposition("resolvecomplaintsOK");
	
	public static PropositionalFormula backupwebsiteNOK = new Proposition("backupwebsiteNOK");
	public static PropositionalFormula backupwebsiteOK = new Proposition("backupwebsiteOK");
	
	public static PropositionalFormula fixerrorsNOK = new Proposition("fixerrorsNOK");
	public static PropositionalFormula fixerrorsOK = new Proposition("fixerrorsOK");
	
	public static PropositionalFormula improvedesignNOK = new Proposition("improvedesignNOK");
	public static PropositionalFormula improvedesignOK = new Proposition("improvedesignOK");
	
	public static PropositionalFormula backupdatabaseNOK = new Proposition("backupdatabaseNOK");
	public static PropositionalFormula backupdatabaseOK = new Proposition("backupdatabaseOK");
	
	public static PropositionalFormula encryptcredentialsNOK = new Proposition("encryptcredentialsNOK");
	public static PropositionalFormula encryptcredentialsOK = new Proposition("encryptcredentialsOK");
	
	public static PropositionalFormula improveperformanceNOK = new Proposition("improveperformanceNOK");
	public static PropositionalFormula improveperformanceOK = new Proposition("improveperformanceOK");
	
	public static PropositionalFormula optimiseSupplyChainsNOK = new Proposition("optimiseSupplyChainsNOK");
	public static PropositionalFormula optimiseSupplyChainsOK = new Proposition("optimiseSupplyChainsOK");
	
	public static PropositionalFormula warehouseSecurityNOK = new Proposition("warehouseSecurityNOK");
	public static PropositionalFormula warehouseSecurityOK = new Proposition("warehouseSecurityOK");
	
	public static PropositionalFormula warehouseModernityNOK = new Proposition("warehouseModernityNOK");
	public static PropositionalFormula warehouseModernityOK = new Proposition("warehouseModernityOK");
	
	public static PropositionalFormula IOProcessedNOK = new Proposition("IOProcessedNOK");
	public static PropositionalFormula IOProcessedOK = new Proposition("IOProcessedOK");
	
	public static PropositionalFormula financeDataBackedUpNOK = new Proposition("financeDataBackedUpNOK");
	public static PropositionalFormula financeDataBackedUpOK = new Proposition("financeDataBackedUpOK");
	
	public static PropositionalFormula financialReportingNOK = new Proposition("financialReportingNOK");
	public static PropositionalFormula financialReportingOK = new Proposition("financialReportingOK");
	
	public static PropositionalFormula financialControlNOK = new Proposition("financialControlNOK");
	public static PropositionalFormula financialControlOK = new Proposition("financialControlOK");
	
	public static Action descriptions = new Action(new Proposition("descriptions"), new HashSet<PropositionalFormula>(Arrays.asList(descriptionsNOK)) , new HashSet<PropositionalFormula>(Arrays.asList(descriptionsOK)), "", "", 20, 12, 10, 15);
	public static Action speccharacters = new Action(new Proposition("speccharacters"), new HashSet<PropositionalFormula>(Arrays.asList(speccharactersNOK)), new HashSet<PropositionalFormula>(Arrays.asList(speccharactersOK)), "", "", 18, 15, 10, 12);
	public static Action advertisement = new Action(new Proposition("advertisement"), new HashSet<PropositionalFormula>(Arrays.asList(advertisementNOK)), new HashSet<PropositionalFormula>(Arrays.asList(advertisementOK)), "", "", 16, 15, 5, 5);
	public static Action securecredentials = new Action(new Proposition("securecredentials"), new HashSet<PropositionalFormula>(Arrays.asList(securecredentialsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(securecredentialsOK)), "", "", 18, 18, 5, 10);
	public static Action buildawareness = new Action(new Proposition("buildawareness"), new HashSet<PropositionalFormula>(Arrays.asList(buildawarenessNOK)), new HashSet<PropositionalFormula>(Arrays.asList(buildawarenessOK)), "", "", 14, 10, 6, 4);
	public static Action resolvecomplaints = new Action(new Proposition("resolvecomplaints"), new HashSet<PropositionalFormula>(Arrays.asList(resolvecomplaintsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(resolvecomplaintsOK)), "", "", 19, 7, 7, 3);
	public static Action backupwebsite = new Action(new Proposition("backupwebsite"), new HashSet<PropositionalFormula>(Arrays.asList(backupwebsiteNOK)), new HashSet<PropositionalFormula>(Arrays.asList(backupwebsiteOK)), "", "", 11, 10, 6, 6);
	public static Action fixerrors = new Action(new Proposition("fixerrors"), new HashSet<PropositionalFormula>(Arrays.asList(fixerrorsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(fixerrorsOK)), "", "", 14, 12, 10, 3);
	public static Action improvedesign = new Action(new Proposition("improvedesign"), new HashSet<PropositionalFormula>(Arrays.asList(improvedesignNOK)), new HashSet<PropositionalFormula>(Arrays.asList(improvedesignOK)), "", "", 12, 12, 6, 11);
	public static Action backupdatabase = new Action(new Proposition("backupdatabase"), new HashSet<PropositionalFormula>(Arrays.asList(backupdatabaseNOK)), new HashSet<PropositionalFormula>(Arrays.asList(backupdatabaseOK)), "", "", 9, 8, 7, 5);
	public static Action encryptcredentials = new Action(new Proposition("encryptcredentials"), new HashSet<PropositionalFormula>(Arrays.asList(encryptcredentialsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(encryptcredentialsOK)), "", "", 8, 8, 5, 4);
	public static Action improveperformance = new Action(new Proposition("improveperformance"), new HashSet<PropositionalFormula>(Arrays.asList(improveperformanceNOK)), new HashSet<PropositionalFormula>(Arrays.asList(improveperformanceOK)), "", "", 15, 8, 7, 9);
	public static Action optimisesupplychains = new Action(new Proposition("optimisesupplychains"), new HashSet<PropositionalFormula>(Arrays.asList(optimiseSupplyChainsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(optimiseSupplyChainsOK)), "", "", 20, 17, 7, 10);
	public static Action securewarehouse = new Action(new Proposition("securewarehouse"), new HashSet<PropositionalFormula>(Arrays.asList(warehouseSecurityNOK)), new HashSet<PropositionalFormula>(Arrays.asList(warehouseSecurityOK)), "", "", 11, 7, 7, 6);
	public static Action modernisewarehouse = new Action(new Proposition("modernisewarehouse"), new HashSet<PropositionalFormula>(Arrays.asList(warehouseModernityNOK)), new HashSet<PropositionalFormula>(Arrays.asList(warehouseModernityOK)), "", "", 14, 13, 9, 13);
	public static Action processinvoicesnorders = new Action(new Proposition("processinvoicesnorders"), new HashSet<PropositionalFormula>(Arrays.asList(IOProcessedNOK)), new HashSet<PropositionalFormula>(Arrays.asList(IOProcessedOK)), "", "", 9, 7, 4, 3);
	public static Action backupfinancedata = new Action(new Proposition("backupfinancedata"), new HashSet<PropositionalFormula>(Arrays.asList(financeDataBackedUpNOK)), new HashSet<PropositionalFormula>(Arrays.asList(financeDataBackedUpOK)), "", "", 8, 8, 3, 6);
	public static Action executereporting = new Action(new Proposition("executereporting"), new HashSet<PropositionalFormula>(Arrays.asList(financialReportingNOK)), new HashSet<PropositionalFormula>(Arrays.asList(financialReportingOK)), "", "", 13, 10, 5, 2);
	public static Action auditfinances = new Action(new Proposition("auditfinances"), new HashSet<PropositionalFormula>(Arrays.asList(financialControlNOK)), new HashSet<PropositionalFormula>(Arrays.asList(financialControlOK)), "", "", 19, 13, 8, 11);
	
	public static Set<Action> setOfActions = new HashSet<Action>();
	
	private static void initialiseSetOfActions() {
		setOfActions.add(descriptions);
		setOfActions.add(speccharacters);
		setOfActions.add(advertisement);
		setOfActions.add(securecredentials);
		setOfActions.add(buildawareness);
		setOfActions.add(resolvecomplaints);
		setOfActions.add(backupwebsite);
		setOfActions.add(fixerrors);
		setOfActions.add(improvedesign);
		setOfActions.add(backupdatabase);
		setOfActions.add(encryptcredentials);
		setOfActions.add(improveperformance);
		setOfActions.add(optimisesupplychains);
		setOfActions.add(securewarehouse);
		setOfActions.add(modernisewarehouse);
		setOfActions.add(processinvoicesnorders);
		setOfActions.add(backupfinancedata);
		setOfActions.add(executereporting);
		setOfActions.add(auditfinances);
	}

	
	public static Policy obligedToRemoveSpecialCharacters = new Policy(new Modality("Obliged"), speccharacters, speccharactersNOK, speccharactersOK, 0, 0.1f);
	public static Policy obligedToSecureCredentials = new Policy(new Modality("Obliged"), securecredentials, securecredentialsNOK, securecredentialsOK, 0, 0.2f);
	public static Policy obligedToBackupWebsite = new Policy(new Modality("Obliged"), backupwebsite, backupwebsiteNOK, backupwebsiteOK, 0, 0.15f);
	public static Policy obligedToFixErrors = new Policy(new Modality("Obliged"), fixerrors, fixerrorsNOK, fixerrorsOK, 0, 0.25f);
	public static Policy obligedToBackupDatabase = new Policy(new Modality("Obliged"), backupdatabase, backupdatabaseNOK, backupdatabaseOK, 0, 0.05f);
	public static Policy obligedToEncryptCredentials = new Policy(new Modality("Obliged"), encryptcredentials, encryptcredentialsNOK, encryptcredentialsOK, 0, 0.15f);
	public static Policy obligedToSecureWarehouse = new Policy(new Modality("Obliged"), securewarehouse, warehouseSecurityNOK, warehouseSecurityOK, 0, 0.125f);
	public static Policy obligedToBackupFinanceData = new Policy(new Modality("Obliged"), backupfinancedata, financeDataBackedUpNOK, financeDataBackedUpOK, 0, 0.225f);
	public static Policy obligedToAuditFinances = new Policy(new Modality("Obliged"), auditfinances, financialControlNOK, financialControlOK, 0, 0.095f);
	
	public static Set<Policy> setOfPolicies = new HashSet<Policy>();
	
	private static void initialiseSetOfPolicies() {
		setOfPolicies.add(obligedToRemoveSpecialCharacters);
		setOfPolicies.add(obligedToSecureCredentials);
		setOfPolicies.add(obligedToBackupWebsite);
		setOfPolicies.add(obligedToFixErrors);
		setOfPolicies.add(obligedToBackupDatabase);
		setOfPolicies.add(obligedToEncryptCredentials);
		setOfPolicies.add(obligedToSecureWarehouse);
		setOfPolicies.add(obligedToBackupFinanceData);
		setOfPolicies.add(obligedToAuditFinances);
		
	}
	
	
//	public static Set<Action> setOfActions = new HashSet<Action>();
	
	public static Map<String, Action> actionMap = new HashMap<String, Action>();
	private void initialiseActionMap() {
		for (Action action:setOfActions) {
			actionMap.put(action.actionName.getName(), action);
		}
	}
	
	public Map<String, Action> tempActionMap = new HashMap<String, Action>();
	public Map<String, Float> scoreMap = new HashMap<String, Float>();
	
	private void initialiseScores() {
		
		for (String playerName:playerMap.keySet()) {
			scoreMap.put(playerName, 0.0f);
		}

	}
		
	public Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	//public Set<PropositionalFormula> tempStateOfGame = new HashSet<PropositionalFormula>();
	
	private void initialiseStateOfGame() {
		stateOfGame.add(start);
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
		if (policy.modality.modality.contentEquals("P")) {
			return false;
		}
		else if (policy.modality.modality.contentEquals("O")) {
			if (stateOfGame.contains(policy.actionName.preCondition)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (stateOfGame.contains(policy.actionName.preCondition)) {
				return false;
			}
			else {
				return true;
			}
		}
	}

	
	public GameEngine(/*Map<String, Player> playerMap,*/ String engineName, int runs, int setupid, int sessionid) {
		this.engineName = engineName;
		//this.playerMap = playerMap;
		this.runs = runs;
		this.setupid = setupid;
		this.sessionid = sessionid;
	}
	
	public synchronized void sendMessage(Channel channel, String playerName, Message message) throws Exception {
		channel.queueDeclare(playerName, false, false, false, null);
		channel.basicPublish("", playerName, null, message.toJSON().getBytes("UTF-8"));
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
	        channel.queueDeclare("gameengine", false, false, false, null);
	        
    		initialiseSetOfActions();
    		initialiseSetOfPolicies();
    		initialiseStateOfGame();
    		initialiseActionMap();
    		initialiseScores();
        	//System.out.println("Game engine Waiting for the messages.........");
        	
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
    		    channel.basicConsume("gameengine", true, deliverCallback, consumerTag -> { });
    		    
    		    state_of_game = new Conjunction(stateOfGame);
            	msg.content = state_of_game.toString();
            	msg.msgNo = this.ticks;
	            msg.ticks = this.ticks;
	            
        		System.out.println("STATE OF GAME");
        		System.out.println(stateOfGame);
            	
                //inserting values into the database
        		for (String player:playerMap.keySet()) {
        			Util.insertSession(setupid, sessionid, this.ticks, player, scoreMap.get(player));
        			wait(50);
                }
                
	            System.out.println("REQUEST FOR ACTIONS --> SENDING STATE OF GAME");
                for (String player:playerMap.keySet()) {
                	sendMessage(channel, player, msg);
                }
                
                int attack = 0;
                if (rand.nextDouble() < 0.03) {
                	attack = 1;
                	System.out.println("THERE HAS BEEN AN ATTACK");
                }
                //double attackProb = rand.nextDouble();
                
                if (attack == 1) {
                	System.out.println("RECALCULATING PLAYERS SCORE");
                	for (String player:playerMap.keySet()) {
                		float max_penalty = 0.0f;
                		for(Policy policy:playerMap.get(player).setOfPolicies) {
                			if (isPolicyViolated(policy)) {
                				if(policy.punishment > max_penalty) {
                					max_penalty = policy.punishment;
                				}
                			}
                		}
                		
                		scoreMap.put(player, scoreMap.get(player) * (1 - max_penalty));
                	}
                }
                
                this.ticks +=1;
        			            
	            wait(50);
        	}
        	
        	msg.header = "inform-game-off";
            msg.content = "inform-game-off";
            
            System.out.println("INFORMING GAME END");
            for (String player:playerMap.keySet()) {
            	sendMessage(channel, player, msg);
            }
            
            wait(100);
            stop();
        	
        	
        	
    	}
    	
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }   

}
