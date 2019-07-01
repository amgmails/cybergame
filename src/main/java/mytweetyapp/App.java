package mytweetyapp;

import java.util.*;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class App {
	
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
	
	public static Action descriptions = new Action(new Proposition("descriptions"), descriptionsNOK, descriptionsOK, "marketing", "marketing", 18, 12, 5);
	public static Action speccharacters = new Action(new Proposition("speccharacters"), speccharactersNOK, speccharactersOK, "marketing", "", 18, 18, 5);
	public static Action advertisement = new Action(new Proposition("advertisement"), advertisementNOK, advertisementOK, "marketing", "", 18, 18, 5);
	public static Action securecredentials = new Action(new Proposition("securecredentials"), securecredentialsNOK, securecredentialsOK, "marketing", "", 18, 18, 5);
	public static Action buildawareness = new Action(new Proposition("buildawareness"), buildawarenessNOK, buildawarenessOK, "marketing", "", 18, 18, 5);
	public static Action resolvecomplaints = new Action(new Proposition("resolvecomplaints"), resolvecomplaintsNOK, resolvecomplaintsOK, "marketing", "", 18, 18, 5);
	public static Action backupwebsite = new Action(new Proposition("backupwebsite"), backupwebsiteNOK, backupwebsiteOK, "technical", "", 18, 18, 5);
	public static Action fixerrors = new Action(new Proposition("fixerrors"), fixerrorsNOK, fixerrorsOK, "technical", "", 18, 18, 5);
	public static Action improvedesign = new Action(new Proposition("improvedesign"), improvedesignNOK, improvedesignOK, "technical", "", 18, 18, 5);
	public static Action backupdatabase = new Action(new Proposition("backupdatabase"), backupdatabaseNOK, backupdatabaseOK, "technical", "", 18, 18, 5);
	public static Action encryptcredentials = new Action(new Proposition("encryptcredentials"), encryptcredentialsNOK, encryptcredentialsOK, "technical", "", 18, 18, 5);
	public static Action improveperformance = new Action(new Proposition("improveperformance"), improveperformanceNOK, improveperformanceOK, "technical", "", 18, 18, 5);
	
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
        for (int k=0; k<1; k++){
        	String playerName = "agent_" + Integer.toString(k);
        	playerMap.put(playerName, new Player(setOfActions, setOfPolicies, playerName));
        	//playerMap.get(playerName).start();
        	playerMap.get(playerName).setName(playerName);
        }
        
        Map<String, GameEngine> geMap = new HashMap<String, GameEngine>();
        geMap.put("gameengine", new GameEngine("gameengine"));
//        GameEngine gameEngine = new GameEngine(playerMap, "gameengine");
        //gameEngine.start();
        //gameEngine.setName("gameengine");
        
        for (String playerName:playerMap.keySet()) {
        	playerMap.get(playerName).geMap = geMap;
        	playerMap.get(playerName).start();
        }
        
        for (String geName:geMap.keySet()) {
        	geMap.get(geName).playerMap = playerMap;
        	geMap.get(geName).start();
        }
		
	}
}