package mytweetyapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import net.sf.tweety.logics.pl.parser.PlParser;
import net.sf.tweety.logics.pl.syntax.Conjunction;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;


/**
 * inspired from https://www.geeksforgeeks.org/message-passing-in-java/
 * https://www.geeksforgeeks.org/asynchronous-synchronous-callbacks-java/
 */


public class Player extends Thread{
	
	Set<Action> setOfActions;
	Set<Policy> setOfPolicies;
	String playerName;
	String role;
	public Map<String, GameEngine> geMap;
	private int ticks = 0;
	
	public Vector<Message> messageQueue = new Vector<Message>();
	public Action tempAction = null;
	private Map<Action, Integer> effectiveActionMap = new HashMap<Action, Integer>();
	
	
	public Player(Set<Action> setOfActions, Set<Policy> setOfPolicies, String playerName, String role) {
		this.playerName = playerName;
		this.setOfActions = setOfActions;
		this.setOfPolicies = setOfPolicies;
		this.role = role;
	}
	
	private Set<Policy> getActivePolicies(Set<Policy> setOfPolicies, Set<PropositionalFormula> stateOfGame) {
		Set<Policy> activePolicies = new HashSet<Policy>();
		for (Policy policy:setOfPolicies) {
			if (policy.isActive(stateOfGame)) {
				activePolicies.add(policy);
			}
		}
		
		return activePolicies;
	}
	
	private boolean doesActionFulfillPolicy(Action action, Policy policy) {
		if (policy.modality.modality.contentEquals("P")) {
			return true;
		}
		else {
			if (!(action.equals(policy.actionName))) {
				return false;
			}
			else {
				if (policy.modality.modality.contentEquals("O")) {
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	
	private boolean doesActionViolatePolicy(Action action, Policy policy) {
		if (policy.modality.modality.contentEquals("P")) {
			return false;
		}
		else {
			if (!(action.equals(policy.actionName))) {
				return false;
			}
			else {
				if (policy.modality.modality.contentEquals("O")) {
					return false;
				}
				else {
					return true;
				}
			}
		}
	}
	
	private Set<Policy> fulfilledPolicies (Action action, Set<Policy> setOfPolicies) {
		Set<Policy> result = new HashSet<Policy>();
		for (Policy policy:setOfPolicies) {
			if (doesActionFulfillPolicy(action, policy)) {
				result.add(policy);
			}
		}
		return result;
	}
	
	private Set<Policy> violatedPolicies (Action action, Set<Policy> SetOfPolicies) {
		Set<Policy> result = new HashSet<Policy>();
		for (Policy policy:SetOfPolicies) {
			if (doesActionViolatePolicy(action, policy)) {
				result.add(policy);
			}
		}
		return result;
	}
	
	private int utility(Action action, Set<PropositionalFormula> stateOfGame) {
		int utility = action.utility1 - action.cost;
		
		Set<Policy> activePolicies = getActivePolicies(setOfPolicies, stateOfGame);
		Set<Policy> fulfilledPolicies = fulfilledPolicies(action, activePolicies);
		Set<Policy> violatedPolicies = violatedPolicies(action, activePolicies);
		
		for (Policy  policy:fulfilledPolicies) {
			utility += policy.reward;
		}
		
		for (Policy  policy:violatedPolicies) {
			utility -= policy.punishment;
		}
		
		return utility;
	}
  
    private Set<Action> availableActions(Set<Action> setOfActions, Set<PropositionalFormula> stateOfGame){
		Set<Action> results = new HashSet<Action>();
		
		/**
		 * check if the postcondition is in the state. It is better to base it on the precondition
		*/
		for (Action action:setOfActions) {
			if (stateOfGame.containsAll(action.preCondition)) {
				if (! effectiveActionMap.keySet().contains(action)) {
					results.add(action);
				}
			}
		}
		
		return results;
    	//return setOfActions;
	}
    
	public void sendMessage(Channel channel, String playerName, Message message) throws Exception {
		channel.queueDeclare("gameengine", false, false, false, null);
		channel.basicPublish("", "gameengine", null, message.toJSON().getBytes("UTF-8"));
        System.out.println(this.getName() + " [x] Sent '" + message.printMessage() + "'" + " to GE.");
	}
    
    /**
    * Some part are taken from the book Mastering RabbitMQ. Others are inspired by geeks for geeks
    */
    
    public synchronized void run() {
    	
    	try {
    		
    		ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        Connection connection = factory.newConnection();
	        Channel channel = connection.createChannel();
	        channel.queueDeclare(this.playerName, false, false, false, null);

        	System.out.println(this.playerName + ": "+ "Waiting for the messages.........");
            
            Message msg = new Message();
            msg.msgNo = 0;
            msg.ticks = 0;
            msg.header = "action";
            msg.content = "pass";
            msg.from = this.playerName;
       	
        	while (!setOfActions.isEmpty()) {
        		
        		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
    		        String message = new String(delivery.getBody(), "UTF-8");
    		        Message contenu = Message.fromJSON(message);
    		        System.out.println(this.playerName + " [x] Received '" + message + "' from " + contenu.from);
    		        
        			Set<Action> listOfActions = new HashSet<Action>();
    				for(Action oneAction:effectiveActionMap.keySet()) {
    					if(contenu.ticks == effectiveActionMap.get(oneAction)) {
    						listOfActions.add(oneAction);
    					}
    				}
    				effectiveActionMap.keySet().removeAll(listOfActions);
    		        
    		        if (contenu.header.contentEquals("inform-game-on")) {
        				System.out.println(this.playerName +": just an information, i do nothing");
        				this.ticks +=1;
        			}
        			else {

        				if (contenu.ticks != this.ticks) {
        					msg.content = "pass";
        					msg.msgNo = this.ticks;
        		            msg.ticks = this.ticks;
        		            try {
        		            	sendMessage(channel, "gameengine", msg);
        		            }
        		            catch (Exception e) {
        		            	e.printStackTrace();
        		            }
        				}
        				else {
        					
        					PlParser parser = new PlParser();
        	            	PropositionalFormula state_of_game = (PropositionalFormula) parser.parseFormula(contenu.content);
        	        		Set<Action> availableActions = availableActions(setOfActions, state_of_game.getLiterals());
        	        		
        					int actionSize = availableActions.size();
        					List<Action> listactions = new ArrayList<Action>(availableActions);
        					Random rand = new Random();
        					int numChoice = rand.nextInt(actionSize); 
        					Action action = listactions.get(numChoice);
        					msg.content = action.actionName.getName();

        					if (tempAction == null) {
        						tempAction = action;
        					}
        					else {
        						effectiveActionMap.put(tempAction, this.ticks + tempAction.effect);
        						tempAction = action;
        					}
        					
        					msg.msgNo = this.ticks;
        		            msg.ticks = this.ticks;
        		            try {
        		            	sendMessage(channel, "gameengine", msg);
        		            }
        		            catch (Exception e) {
        		            	e.printStackTrace();
        		            }
        					this.ticks += action.cost;
        				}
        			}
    		    };
    		    
    		    channel.basicConsume(this.playerName, true, deliverCallback, consumerTag -> { });
    		    
                //sleep(1000);
    		    wait(50);
        	}
    	}
    	catch (Exception e) {	
    		e.printStackTrace();
    	}
    }
}
