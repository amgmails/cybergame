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
	
	public static Action descriptions = new Action(new Proposition("descriptions"), new HashSet<PropositionalFormula>(Arrays.asList(descriptionsNOK)) , new HashSet<PropositionalFormula>(Arrays.asList(descriptionsOK)), "marketing", "marketing", 20, 12, 10, 15);
	public static Action speccharacters = new Action(new Proposition("speccharacters"), new HashSet<PropositionalFormula>(Arrays.asList(speccharactersNOK)), new HashSet<PropositionalFormula>(Arrays.asList(speccharactersOK)), "marketing", "", 18, 15, 10, 12);
	public static Action advertisement = new Action(new Proposition("advertisement"), new HashSet<PropositionalFormula>(Arrays.asList(advertisementNOK)), new HashSet<PropositionalFormula>(Arrays.asList(advertisementOK)), "marketing", "", 16, 15, 5, 5);
	public static Action securecredentials = new Action(new Proposition("securecredentials"), new HashSet<PropositionalFormula>(Arrays.asList(securecredentialsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(securecredentialsOK)), "marketing", "", 18, 18, 5, 10);
	public static Action buildawareness = new Action(new Proposition("buildawareness"), new HashSet<PropositionalFormula>(Arrays.asList(buildawarenessNOK)), new HashSet<PropositionalFormula>(Arrays.asList(buildawarenessOK)), "marketing", "", 14, 10, 6, 4);
	public static Action resolvecomplaints = new Action(new Proposition("resolvecomplaints"), new HashSet<PropositionalFormula>(Arrays.asList(resolvecomplaintsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(resolvecomplaintsOK)), "marketing", "", 19, 7, 7, 3);
	public static Action backupwebsite = new Action(new Proposition("backupwebsite"), new HashSet<PropositionalFormula>(Arrays.asList(backupwebsiteNOK)), new HashSet<PropositionalFormula>(Arrays.asList(backupwebsiteOK)), "technical", "", 11, 10, 6, 6);
	public static Action fixerrors = new Action(new Proposition("fixerrors"), new HashSet<PropositionalFormula>(Arrays.asList(fixerrorsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(fixerrorsOK)), "technical", "", 14, 12, 10, 3);
	public static Action improvedesign = new Action(new Proposition("improvedesign"), new HashSet<PropositionalFormula>(Arrays.asList(improvedesignNOK)), new HashSet<PropositionalFormula>(Arrays.asList(improvedesignOK)), "technical", "", 12, 12, 6, 11);
	public static Action backupdatabase = new Action(new Proposition("backupdatabase"), new HashSet<PropositionalFormula>(Arrays.asList(backupdatabaseNOK)), new HashSet<PropositionalFormula>(Arrays.asList(backupdatabaseOK)), "technical", "", 9, 8, 7, 5);
	public static Action encryptcredentials = new Action(new Proposition("encryptcredentials"), new HashSet<PropositionalFormula>(Arrays.asList(encryptcredentialsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(encryptcredentialsOK)), "technical", "", 8, 8, 5, 4);
	public static Action improveperformance = new Action(new Proposition("improveperformance"), new HashSet<PropositionalFormula>(Arrays.asList(improveperformanceNOK)), new HashSet<PropositionalFormula>(Arrays.asList(improveperformanceOK)), "technical", "", 15, 8, 7, 9);
	public static Action optimisesupplychains = new Action(new Proposition("optimisesupplychains"), new HashSet<PropositionalFormula>(Arrays.asList(optimiseSupplyChainsNOK)), new HashSet<PropositionalFormula>(Arrays.asList(optimiseSupplyChainsOK)), "logistical", "", 20, 17, 7, 10);
	public static Action securewarehouse = new Action(new Proposition("securewarehouse"), new HashSet<PropositionalFormula>(Arrays.asList(warehouseSecurityNOK)), new HashSet<PropositionalFormula>(Arrays.asList(warehouseSecurityOK)), "logistical", "", 11, 7, 7, 6);
	public static Action modernisewarehouse = new Action(new Proposition("modernisewarehouse"), new HashSet<PropositionalFormula>(Arrays.asList(warehouseModernityNOK)), new HashSet<PropositionalFormula>(Arrays.asList(warehouseModernityOK)), "logistical", "", 14, 13, 9, 13);
	public static Action processinvoicesnorders = new Action(new Proposition("processinvoicesnorders"), new HashSet<PropositionalFormula>(Arrays.asList(IOProcessedNOK)), new HashSet<PropositionalFormula>(Arrays.asList(IOProcessedOK)), "finance", "", 9, 7, 4, 3);
	public static Action backupfinancedata = new Action(new Proposition("backupfinancedata"), new HashSet<PropositionalFormula>(Arrays.asList(financeDataBackedUpNOK)), new HashSet<PropositionalFormula>(Arrays.asList(financeDataBackedUpOK)), "finance", "", 8, 8, 3, 6);
	public static Action executereporting = new Action(new Proposition("executereporting"), new HashSet<PropositionalFormula>(Arrays.asList(financialReportingNOK)), new HashSet<PropositionalFormula>(Arrays.asList(financialReportingOK)), "finance", "", 13, 10, 5, 2);
	public static Action auditfinances = new Action(new Proposition("auditfinances"), new HashSet<PropositionalFormula>(Arrays.asList(financialControlNOK)), new HashSet<PropositionalFormula>(Arrays.asList(financialControlOK)), "finance", "", 19, 13, 8, 11);
	
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
		
	public static Set<PropositionalFormula> stateOfGame = new HashSet<PropositionalFormula>();
	
	private static void initialiseStateOfGame() {
		stateOfGame.add(start);
		for(Action action:setOfActions) {
			stateOfGame.addAll(action.preCondition);
		}
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
        for (int k=0; k<1; k++){
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