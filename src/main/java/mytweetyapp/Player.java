package mytweetyapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

//import net.sf.tweety.logics.pl.parser.PlParser;
//import net.sf.tweety.logics.pl.syntax.Conjunction;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;


/**
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 * https://www.geeksforgeeks.org/asynchronous-synchronous-callbacks-java/
 */


public class Player extends Thread{
	
	Set<Action> setOfActions;
	String playerName;
	public Map<String, GameEngine> geMap;
	private int ticks = 0;
	
	public Vector<Message> messageQueue = new Vector<Message>();
	
	
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
    
    public synchronized void run() {
    	
    	try {

        	System.out.println(this.playerName + ": "+ "Waiting for the messages.........");
            
            Message msg = new Message();
            msg.msgNo = 0;
            msg.ticks = 0;
            msg.header = "action";
            msg.content = "pass";
            msg.from = this.playerName;
       	
        	while (!setOfActions.isEmpty()) {
        		//System.out.println(this.messageQueue.size());
        		while (this.messageQueue.size() == 0) {
        			wait(5);
        		}
        		
    			Message message = (Message) this.messageQueue.firstElement();
    	        // extracts the message from the queue 
    			this.messageQueue.removeElement(message);
        		
    			System.out.println(this.playerName +" Received from " + message.from + " message: " + message.toString());
    			
    			if (message.header.contentEquals("inform-game-on")) {
    				System.out.println(this.playerName +": just an information, i do nothing");
    			}
    			else {

    				if (message.ticks != this.ticks) {
    					msg.content = "pass";
    					msg.msgNo = this.ticks;
    		            msg.ticks = this.ticks;
    					geMap.get("gameengine").messageQueue.addElement(msg);
    					notify();
    					System.out.println(this.playerName + " sent message: " + msg.toString());
    				}
    				else {
    					Set<PropositionalFormula> state_of_game = (Set<PropositionalFormula>) message.content;
    	        		Set<Action> availableActions = availableActions(setOfActions, state_of_game);
    	        		
    					int actionSize = availableActions.size();
    					List<Action> listactions = new ArrayList<Action>(availableActions);
    					Random rand = new Random();
    					int numChoice = rand.nextInt(actionSize); 
    					Action action = listactions.get(numChoice);
    					msg.content = action;
    					msg.msgNo = this.ticks;
    		            msg.ticks = this.ticks;
    					geMap.get("gameengine").messageQueue.addElement(msg);
    					notify();
    					System.out.println(this.playerName + " sent message: " + msg.toString());
    				}
    			}
                //msg.msgNo += 1;
                //msg.ticks += 1;
    			this.ticks += 1;
                sleep(1000);
        	}
    	}
    	catch (Exception e) {	
    		System.out.println("Error: " + e.toString());
    	}
    }
}
