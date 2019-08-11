package mytweetyapp;

import java.util.*;
import java.util.concurrent.*;

import java.sql.*;

import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class App {
	static Random rand = new Random();
	
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
	
	public static List<Action> setOfActions = new ArrayList<Action>();
	
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

	
	public static Policy obligedToRemoveSpecialCharacters = new Policy(new Modality("Obliged"), speccharacters, speccharactersNOK, speccharactersOK, 10, 0.5f);
	public static Policy obligedToSecureCredentials = new Policy(new Modality("Obliged"), securecredentials, securecredentialsNOK, securecredentialsOK, 10, 0.5f);
	public static Policy obligedToBackupWebsite = new Policy(new Modality("Obliged"), backupwebsite, backupwebsiteNOK, backupwebsiteOK, 10, 0.5f);
	public static Policy obligedToFixErrors = new Policy(new Modality("Obliged"), fixerrors, fixerrorsNOK, fixerrorsOK, 10, 0.5f);
	public static Policy obligedToBackupDatabase = new Policy(new Modality("Obliged"), backupdatabase, backupdatabaseNOK, backupdatabaseOK, 10, 0.5f);
	public static Policy obligedToEncryptCredentials = new Policy(new Modality("Obliged"), encryptcredentials, encryptcredentialsNOK, encryptcredentialsOK, 10, 0.5f);
	public static Policy obligedToSecureWarehouse = new Policy(new Modality("Obliged"), securewarehouse, warehouseSecurityNOK, warehouseSecurityOK, 10, 0.5f);
	public static Policy obligedToBackupFinanceData = new Policy(new Modality("Obliged"), backupfinancedata, financeDataBackedUpNOK, financeDataBackedUpOK, 10, 0.5f);
	public static Policy obligedToAuditFinances = new Policy(new Modality("Obliged"), auditfinances, financialControlNOK, financialControlOK, 10, 0.5f);
	
	public static List<Policy> setOfPolicies = new ArrayList<Policy>();
	
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
	
	public static void setPoliciesValues() {
		for(Policy policy:setOfPolicies) {
			policy.reward = rand.nextFloat() * 20;
			policy.punishment = rand.nextFloat() + 0.0001f;
		}
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
	/*
	 * adapted from https://www.geeksforgeeks.org/randomly-select-items-from-a-list-in-java/
	 */
	
	public static List<Action> getPlayersActions(List<Action> setofactions, int totalactions) {
		
		// create a temporary list for storing 
		// selected element 
		List<Action> newActions = new ArrayList<Action>();
		List<Action> listactions = new ArrayList<Action>(setofactions);
		for (int i = 0; i < totalactions; i++) { 
			
			// take a random index between 0 to size  
			// of given List 
			int randomIndex = rand.nextInt(listactions.size()); 
			
			// add element in temporary list 
			newActions.add(listactions.get(randomIndex)); 
			
			// Remove selected element from orginal list 
			listactions.remove(randomIndex); 
		} 
		return newActions; 
	}
	
	public static List<Policy> applicablePolicies(List<Action> setofactions) {
		
		List<Policy> applicablePolicies = new ArrayList<Policy>();
		for (Action action:setofactions) {
			for (Policy policy:setOfPolicies) {
				if (policy.actionName.equals(action)) {
					applicablePolicies.add(policy);
				}
			} 
		}
		return applicablePolicies;
	}
	
	/*
	 * taken on https://www.baeldung.com/java-executor-wait-for-threads
	 */
	public static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
		threadPool.shutdown();
		try {
			if(!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
				threadPool.shutdownNow();
			}
		}
		catch (InterruptedException ex) {
			threadPool.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		initialiseSetOfActions();
		initialiseSetOfPolicies();
		initialiseStateOfGame();
		initialiseRoleActionsMap();
		
		int maxsetupid = 0;
		Connection conn = Util.connect();
		String sqlmaxid = "select max(setup.setupid) maxsetupid from setup";
		try {
			int numrows = 0;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) nber from setup");
			while (rs.next()) {
				numrows = rs.getInt(1);
			}
			if (numrows != 0) {
				rs = stmt.executeQuery(sqlmaxid);
				while (rs.next()) {
					maxsetupid = rs.getInt(1);
				}
			}
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(1000);
		

		for (int setupid = maxsetupid + 1; setupid <= 26; setupid++) {
			Random rand = new Random();
			//System.out.println("...");
			setPoliciesValues();
			for(Policy policy:setOfPolicies) {
				Util.insertSetup(setupid, policy.getName(), policy.reward, policy.punishment);
			}
			
			int  totalactions = 3;
			
			for (int sessionid = 1; sessionid <= 5; sessionid++) {
				//int numPlayers = rand.nextInt(10) + 1;
				//ExecutorService executor = Executors.newFixedThreadPool(50);
				for (int runid = 1; runid <= 30; runid++) {
					int numPlayers = rand.nextInt(10) + 1;
					Map<String, Player> playerMap = new HashMap<String, Player>();
					for (int k = 0; k < numPlayers; k++) {
						String playerName = "agent" + Integer.toString(k);
						List<Action> playerActions = getPlayersActions(setOfActions, totalactions);
						List<Policy> playerPolicies = applicablePolicies(playerActions);
			        	playerMap.put(playerName, new Player(playerActions, playerPolicies, playerName, "", setupid, sessionid, runid));
			        	playerMap.get(playerName).setName(playerName);
			        	Util.insertMapping(setupid, sessionid, runid, playerName, playerActions.size(), playerPolicies.size());
					}
					Map<String, GameEngine> geMap = new HashMap<String, GameEngine>();
			        geMap.put("gameengine", new GameEngine("gameengine", sessionid * 100 + 1, setupid, sessionid, runid));
			        
			        Game game = new Game(geMap, playerMap);
			        executor.submit(game);     
				}
						
				executor.awaitTermination(14 * sessionid, TimeUnit.SECONDS);
				executor.shutdown();
			}
		}

		
		/**
		 * https://stackoverflow.com/questions/2711067/how-do-i-dynamically-name-objects-in-java
		 */
		
		System.out.println("END OF SIMULATION");
	}
}