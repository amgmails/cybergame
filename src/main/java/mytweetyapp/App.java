package mytweetyapp;

import java.util.*;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class App {
	
	public static void main(String[] args) {
		PropositionalFormula start = new Proposition("start");
		PropositionalFormula end = new Proposition("end");
		PropositionalFormula top = new Proposition("top");
		PropositionalFormula bottom = new Proposition("bottom");
		
		PropositionalFormula reportNotWritten = new Proposition("reportNotWritten");
		PropositionalFormula reportWritten = new Proposition("reportWritten");
		
		PropositionalFormula excelSheetNotDone = new Proposition("excelSheetNotDone");
		PropositionalFormula excelSheetDone = new Proposition("excelSheetDone");
		
		PropositionalFormula sensitiveFolderNotTreated = new Proposition("sensitiveFolderNotTreated");
		PropositionalFormula sensitiveFolderTreated = new Proposition("sensitiveFolderTreated");
		
		Action reportWriting = new Action(new Proposition("reportWriting"), 20, 8, reportNotWritten, reportWritten);
		Action excelWork = new Action(new Proposition("excelWork"), 10, 4, excelSheetNotDone, excelSheetDone);
		Action treatSensitiveFolder = new Action(new Proposition("treatSensitiveFolder"), 10, 4, sensitiveFolderNotTreated, sensitiveFolderTreated);
		
		Set<Action> setOfActions = new HashSet<Action>();
		setOfActions.add(reportWriting);
		setOfActions.add(excelWork);
		setOfActions.add(treatSensitiveFolder);
		
		Policy obligedToTreatSensitiveFolder = new Policy(new Modality("Obliged"), treatSensitiveFolder, sensitiveFolderNotTreated, sensitiveFolderTreated);
		
		Set<Policy> setOfPolicies = new HashSet<Policy>();
		setOfPolicies.add(obligedToTreatSensitiveFolder);
		
		Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
		stateOfGame.add(start);
		
		int score = 0;
		
		while (!stateOfGame.contains(end)) {
			
			//put the availableActions into a method so that is it separate from main function
			Set<Action> actions = availableActions(setOfActions, stateOfGame);
			Set<Policy> policies = availablePolicies(setOfPolicies, stateOfGame);
			
			if (!actions.isEmpty()) {
				int actionSize = actions.size();
				List<Action> listactions = new ArrayList<Action>(actions);
				Random rand = new Random();
				int numChoice = rand.nextInt(actionSize); 
				Action action = listactions.get(numChoice);
				System.out.println("action chosen is " + action.actionName.getName());
				stateOfGame.add(action.postCondition);
				score = score + action.benefit - action.cost;
				System.out.println("score is " + score);
			} else {
				stateOfGame.add(end);
			}

		}
		
		//PropositionalFormula helloWorld = new Proposition("HelloWorld");
		//System.out.println(helloWorld);
	}
	
	public static Set<Action> availableActions(Set<Action> setOfActions, Set<PropositionalFormula> stateOfGame){
		Set<Action> results = new HashSet<Action>();
		
		//check if the postcondition is in the state
		//better to base it on the precondition
		for (Action action:setOfActions) {
			if (!stateOfGame.contains(action.postCondition)) {
				results.add(action);
			}
		}
		
		return results;
	}
	
	public static Set<Policy> availablePolicies(Set<Policy> setOfPolicies, Set<PropositionalFormula> stateOfGame){
		Set<Policy> results = new HashSet<Policy>();
		
		for (Policy policy:setOfPolicies) {
			if (!stateOfGame.contains(policy.deactivationCondition)) {
				results.add(policy);
			}
		}
		
		return results;
	}
	

}