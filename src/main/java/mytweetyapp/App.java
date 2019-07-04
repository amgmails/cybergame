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
		
	public static Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	
	private static void initialiseStateOfGame() {
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
	
	public static Map<String, Set<Action>> roleActionsMap = new HashMap<String, Set<Action>>();
	
	private static void initialiseRoleActionsMap() {
		for (Action action:setOfActions) {
			if (!action.role1.contentEquals("")) {
				try {
					roleActionsMap.get(action.role1).add(action);
				}
				catch (Exception e) {
					roleActionsMap.put(action.role1, new HashSet<Action>());
				}
			}
			
			if (!action.role2.contentEquals("")) {
				try {
					roleActionsMap.get(action.role2).add(action);
				}
				catch (Exception e) {
					roleActionsMap.put(action.role2, new HashSet<Action>());
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		initialiseSetOfActions();
		initialiseSetOfPolicies();
		initialiseStateOfGame();
		initialiseRoleActionsMap();

		/**
		 * https://stackoverflow.com/questions/2711067/how-do-i-dynamically-name-objects-in-java
		 */
		
		Map<String, Player> playerMap = new HashMap<String, Player>();
        for (int k=0; k<2; k++){
        	Random rand = new Random();
        	int numChoice = rand.nextInt(roleActionsMap.size());
        	List<String> listofroles = new ArrayList<String>(roleActionsMap.keySet());
			String role = listofroles.get(numChoice);
        	String playerName = "agent_" + Integer.toString(k);
        	playerMap.put(playerName, new Player(roleActionsMap.get(role), setOfPolicies, playerName, role));
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