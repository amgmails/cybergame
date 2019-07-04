package mytweetyapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

/*
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 */

public class GameEngine extends Thread{
	
	String engineName;
	Map<String, Player> playerMap;
	private int ticks = 0;
	
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
	
	public static Action descriptions = new Action(new Proposition("descriptions"), new HashSet<PropositionalFormula>(Arrays.asList(descriptionsNOK)) , new HashSet<PropositionalFormula>(Arrays.asList(descriptionsOK)), "marketing", "marketing", 18, 12, 5, 10);
	public static Action speccharacters = new Action(new Proposition("speccharacters"), new HashSet<PropositionalFormula>(Arrays.asList(speccharactersNOK)), new HashSet<PropositionalFormula>(Arrays.asList(speccharactersOK)), "marketing", "", 18, 18, 5, 9);
	public static Action advertisement = new Action(new Proposition("advertisement"), new HashSet<PropositionalFormula>(Arrays.asList(advertisementNOK)), new HashSet<PropositionalFormula>(Arrays.asList(advertisementOK)), "marketing", "", 18, 18, 5, 8);
	public static Action securecredentials = new Action(new Proposition("securecredentials"), new HashSet<PropositionalFormula>(Arrays.asList(securecredentialsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(securecredentialsOK)), "marketing", "", 18, 18, 5, 10);
	public static Action buildawareness = new Action(new Proposition("buildawareness"), new HashSet<PropositionalFormula>(Arrays.asList(buildawarenessNOK)), new HashSet<PropositionalFormula>(Arrays.asList(buildawarenessOK)), "marketing", "", 18, 18, 5, 9);
	public static Action resolvecomplaints = new Action(new Proposition("resolvecomplaints"), new HashSet<PropositionalFormula>(Arrays.asList(resolvecomplaintsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(resolvecomplaintsOK)), "marketing", "", 18, 18, 5, 8);
	public static Action backupwebsite = new Action(new Proposition("backupwebsite"), new HashSet<PropositionalFormula>(Arrays.asList(backupwebsiteNOK)), new HashSet<PropositionalFormula>(Arrays.asList(backupwebsiteOK)), "technical", "", 18, 18, 5, 7);
	public static Action fixerrors = new Action(new Proposition("fixerrors"), new HashSet<PropositionalFormula>(Arrays.asList(fixerrorsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(fixerrorsOK)), "technical", "", 18, 18, 5, 6);
	public static Action improvedesign = new Action(new Proposition("improvedesign"), new HashSet<PropositionalFormula>(Arrays.asList(improvedesignNOK)), new HashSet<PropositionalFormula>(Arrays.asList(improvedesignOK)), "technical", "", 18, 18, 5, 10);
	public static Action backupdatabase = new Action(new Proposition("backupdatabase"), new HashSet<PropositionalFormula>(Arrays.asList(backupdatabaseNOK)), new HashSet<PropositionalFormula>(Arrays.asList(backupdatabaseOK)), "technical", "", 18, 18, 5, 9);
	public static Action encryptcredentials = new Action(new Proposition("encryptcredentials"), new HashSet<PropositionalFormula>(Arrays.asList(encryptcredentialsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(encryptcredentialsOK)), "technical", "", 18, 18, 5, 8);
	public static Action improveperformance = new Action(new Proposition("improveperformance"), new HashSet<PropositionalFormula>(Arrays.asList(improveperformanceNOK)), new HashSet<PropositionalFormula>(Arrays.asList(improveperformanceOK)), "technical", "", 18, 18, 5, 7);
	
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
	}

	
	public static Policy obligedToRemoveSpecialCharacters = new Policy(new Modality("Obliged"), speccharacters, speccharactersNOK, speccharactersOK, 0, 1);
	public static Policy obligedToSecureCredentials = new Policy(new Modality("Obliged"), securecredentials, securecredentialsNOK, securecredentialsOK, 0, 1);
	public static Policy obligedToBackupWebsite = new Policy(new Modality("Obliged"), backupwebsite, backupwebsiteNOK, backupwebsiteOK, 0, 1);
	public static Policy obligedToFixErrors = new Policy(new Modality("Obliged"), fixerrors, fixerrorsNOK, fixerrorsOK, 0, 1);
	public static Policy obligedToBackupDatabase = new Policy(new Modality("Obliged"), backupdatabase, backupdatabaseNOK, backupdatabaseOK, 0, 1);
	public static Policy obligedToEncryptCredentials = new Policy(new Modality("Obliged"), encryptcredentials, encryptcredentialsNOK, encryptcredentialsOK, 0, 1);
	
	public static Set<Policy> setOfPolicies = new HashSet<Policy>();
	
	private static void initialiseSetOfPolicies() {
		setOfPolicies.add(obligedToRemoveSpecialCharacters);
		setOfPolicies.add(obligedToSecureCredentials);
		setOfPolicies.add(obligedToBackupWebsite);
		setOfPolicies.add(obligedToFixErrors);
		setOfPolicies.add(obligedToBackupDatabase);
		setOfPolicies.add(obligedToEncryptCredentials);
		
	}
	
	
//	public static Set<Action> setOfActions = new HashSet<Action>();
	
	public static Map<String, Action> actionMap = new HashMap<String, Action>();
	public static Map<String, Action> tempActionMap = new HashMap<String, Action>();
	public static Map<String, Integer> scoreMap = new HashMap<String, Integer>();
	
//	private void initialiseSetOfActions() {
//		setOfActions.add(reportWriting);
//		setOfActions.add(excelWork);
//		setOfActions.add(treatSensitiveFolder);
//		setOfActions.add(A);
//		setOfActions.add(B);
//		setOfActions.add(C);
//		
//		actionMap.put(reportWriting.actionName.getName(), reportWriting);
//		actionMap.put(excelWork.actionName.getName(), excelWork);
//		actionMap.put(treatSensitiveFolder.actionName.getName(), treatSensitiveFolder);
//		actionMap.put(A.actionName.getName(), A);
//		actionMap.put(B.actionName.getName(), B);
//		actionMap.put(C.actionName.getName(), C);
//	}
	
	private void initialiseScores() {
		
		for (String playerName:playerMap.keySet()) {
			scoreMap.put(playerName, 0);
		}

	}
	
//	public Policy obligedToTreatSensitiveFolder = new Policy(new Modality("Obliged"), treatSensitiveFolder, sensitiveFolderNotTreated, sensitiveFolderTreated);
//	
//	public Set<Policy> setOfPolicies = new HashSet<Policy>();
	
//	private void initialiseSetOfPolicies() {
//		setOfPolicies.add(obligedToTreatSensitiveFolder);
//	}
		
	public Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	//public Set<PropositionalFormula> tempStateOfGame = new HashSet<PropositionalFormula>();
	
	private void initialiseStateOfGame() {
		stateOfGame.add(start);
		stateOfGame.add(advertisementNOK);
		stateOfGame.add(backupdatabaseNOK);
		stateOfGame.add(backupwebsiteNOK);
		stateOfGame.add(buildawarenessNOK);
		stateOfGame.add(descriptionsNOK);
		stateOfGame.add(encryptcredentialsNOK);
		stateOfGame.add(fixerrorsNOK);
		stateOfGame.add(improvedesignNOK);
		stateOfGame.add(improveperformanceNOK);
		stateOfGame.add(resolvecomplaintsNOK);
		stateOfGame.add(securecredentialsNOK);
		stateOfGame.add(speccharactersNOK);
	}
	
	public void updateStateNScore(String playerName, Action action) {
		try {
			stateOfGame.addAll(tempActionMap.get(playerName).postCondition);
			stateOfGame.removeAll(tempActionMap.get(playerName).preCondition);
			effectiveActionMap.put(tempActionMap.get(playerName), this.ticks + tempActionMap.get(playerName).effect);
			computeScore(playerName);
			//int oldValue = scoreMap.get(playerName);
			//scoreMap.replace(playerName, oldValue + tempActionMap.get(playerName).utility1 - tempActionMap.get(playerName).cost);
			tempActionMap.put(playerName, action);
		}
		catch(Exception e) {
			tempActionMap.put(playerName, action);
		}
	}
	
	public void computeScore(String playerName) {
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
            int new_score = scoreMap.get(playerName) + chosen_action.utility1;
            scoreMap.put(playerName, new_score);
		}
		if(num_action == 1){
            int new_score = scoreMap.get(playerName) + chosen_action.utility2;
            scoreMap.put(playerName, new_score);
		}
}

	
	public GameEngine(/*Map<String, Player> playerMap,*/ String engineName) {
		this.engineName = engineName;
		//this.playerMap = playerMap;
	}
	    
    @Override
    public synchronized void run() {
    	
    	try {
    		initialiseSetOfActions();
    		initialiseSetOfPolicies();
    		initialiseStateOfGame();
    		initialiseScores();
        	System.out.println("Game engine Waiting for the messages.........");
        	
            Message msg = new Message();
            msg.msgNo = 0;
            msg.ticks = 0;
            msg.header = "inform-game-on";
            msg.content = "inform-game-on";
            msg.from = this.engineName;
            
          //sending to all players
            for (String player:playerMap.keySet()) {
            	playerMap.get(player).messageQueue.addElement(msg);
            	notify();
            }
            System.out.println(this.engineName + " sent message: " + msg.toString());
            //msg.msgNo += 1;
            //msg.ticks += 1;
            this.ticks +=1;
            
            msg.header = "inform-state";
            msg.content = stateOfGame;
            msg.msgNo = this.ticks;
            msg.ticks = this.ticks;
        	
            //sending to all players
            for (String player:playerMap.keySet()) {
            	playerMap.get(player).messageQueue.addElement(msg);
            	notify();
            }
            System.out.println(this.engineName + " sent message: " + msg.toString());
            //msg.msgNo += 1;
            //msg.ticks += 1;
            this.ticks +=1;
        	
        	
        	while (!setOfActions.isEmpty()) {
        		//sleep(20);
        		
        		while (this.messageQueue.size() < playerMap.size()) {
        			wait(5);
        		}
        		
        		Set<Action> listOfActions = new HashSet<Action>();
				for(Action oneAction:effectiveActionMap.keySet()) {
					if(this.ticks == effectiveActionMap.get(oneAction)) {
						listOfActions.add(oneAction);
						//effectiveActionMap.remove(oneAction);
						//stateOfGame.removeAll(oneAction.postCondition);
						//stateOfGame.addAll(oneAction.preCondition);
					}
				}
				effectiveActionMap.keySet().removeAll(listOfActions);
				
				for(Action oneAction:listOfActions) {
					stateOfGame.removeAll(oneAction.postCondition);
					stateOfGame.addAll(oneAction.preCondition);
				}
				
				
        		for (int i = 0; i < playerMap.size(); i++) {
        			
        			//System.out.println(this.messageQueue.size());
        			Message message = (Message) this.messageQueue.firstElement();
        	        // extracts the message from the queue 
        			this.messageQueue.removeElement(message);

        			System.out.println(this.engineName +" Received from " + message.from + " message: " + message.toString());
        			
        			if (message.content.toString().contentEquals("pass")) {
        				System.out.println("Player "+ message.from + " decided to pass");
        			}
        			else {
        	        	Action selectedAction = (Action) message.content;
        	        	updateStateNScore(message.from, selectedAction);

        	        	System.out.println("Player " + message.from + " new score is " + scoreMap.get(message.from).toString());
        			}
        		}
	        		
        		System.out.println(stateOfGame.toString() + " is state of game");

	        	msg.content = stateOfGame;
	            msg.msgNo = this.ticks;
	            msg.ticks = this.ticks;
	        	
	            //sending to all players
	            for (String player:playerMap.keySet()) {
	            	playerMap.get(player).messageQueue.addElement(msg);
	            	notify();
	            }
	            System.out.println(this.engineName + " sent message: " + msg.toString());
	            //msg.msgNo += 1;
	            //msg.ticks += 1;
	            this.ticks +=1;
	            
	            wait(1000);
        	}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		
    	}
    }   

}
