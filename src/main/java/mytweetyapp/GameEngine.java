package mytweetyapp;

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
	
	public Policy obligedToTreatSensitiveFolder = new Policy(new Modality("Obliged"), treatSensitiveFolder, sensitiveFolderNotTreated, sensitiveFolderTreated);
	
	public Set<Policy> setOfPolicies = new HashSet<Policy>();
	
	private void initialiseSetOfPolicies() {
		setOfPolicies.add(obligedToTreatSensitiveFolder);
	}
		
	public Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	
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
        		sleep(20);
        		
        		while (this.messageQueue.size() < playerMap.size()) {
        			wait(5);
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
        	        	updateStateOfGame(selectedAction);
        	        	updateScore(message.from, selectedAction);

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
	            
	            sleep(1000);
        	}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		
    	}
    }   

}
