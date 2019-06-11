package mytweetyapp;

import java.util.*;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class App {
	
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
	
	private static void initialiseSetOfActions() {
		setOfActions.add(reportWriting);
		setOfActions.add(excelWork);
		setOfActions.add(treatSensitiveFolder);
	}

	
	public static Policy obligedToTreatSensitiveFolder = new Policy(new Modality("Obliged"), treatSensitiveFolder, sensitiveFolderNotTreated, sensitiveFolderTreated);
	
	public static Set<Policy> setOfPolicies = new HashSet<Policy>();
	
	private static void initialiseSetOfPolicies() {
		setOfPolicies.add(obligedToTreatSensitiveFolder);
	}
		
	public static Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	
	private static void initialiseStateOfGame() {
		stateOfGame.add(start);
	}
	
	
	
	public static void main(String[] args) {
		initialiseSetOfActions();
		initialiseSetOfPolicies();
		initialiseStateOfGame();

		/**
		 * https://stackoverflow.com/questions/2711067/how-do-i-dynamically-name-objects-in-java
		 */
		
		Map<String, Player> playerMap = new HashMap<String, Player>();
        for (int k=0; k<2; k++){
        	String playerName = "agent_" + Integer.toString(k);
        	playerMap.put(playerName, new Player(setOfActions, playerName));
        	playerMap.get(playerName).start();
        	playerMap.get(playerName).setName(playerName);
        }
        
        GameEngine gameEngine = new GameEngine(playerMap, "gameengine");
        gameEngine.start();
        gameEngine.setName("gameengine");
		
	}
}