package mytweetyapp;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.javatuples.Pair;

import com.opencsv.*;

//import org.apache.commons.math.stat.correlation.Covariance;
//import org.apache.commons.math.stat.correlation.Covariance;
//import org.apache.commons.math3.distribution.*;

import smile.math.distance.EuclideanDistance;
import smile.math.kernel.*;
import smile.math.rbf.GaussianRadialBasis;
import smile.regression.*;
import smile.stat.distribution.MultivariateGaussianDistribution;
import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;


public class Test {
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

    public static Action descriptions = new Action(new Proposition("descriptions"), new HashSet < PropositionalFormula > (Arrays.asList(descriptionsNOK)), new HashSet < PropositionalFormula > (Arrays.asList(descriptionsOK)), "", "", 20, 12, 10, 15);
    public static Action speccharacters = new Action(new Proposition("speccharacters"), new HashSet < PropositionalFormula > (Arrays.asList(speccharactersNOK)), new HashSet < PropositionalFormula > (Arrays.asList(speccharactersOK)), "", "", 18, 15, 10, 12);
    public static Action advertisement = new Action(new Proposition("advertisement"), new HashSet < PropositionalFormula > (Arrays.asList(advertisementNOK)), new HashSet < PropositionalFormula > (Arrays.asList(advertisementOK)), "", "", 16, 15, 5, 5);
    public static Action securecredentials = new Action(new Proposition("securecredentials"), new HashSet < PropositionalFormula > (Arrays.asList(securecredentialsNOK)), new HashSet < PropositionalFormula > (Arrays.asList(securecredentialsOK)), "", "", 18, 18, 5, 10);
    public static Action buildawareness = new Action(new Proposition("buildawareness"), new HashSet < PropositionalFormula > (Arrays.asList(buildawarenessNOK)), new HashSet < PropositionalFormula > (Arrays.asList(buildawarenessOK)), "", "", 14, 10, 6, 4);
    public static Action resolvecomplaints = new Action(new Proposition("resolvecomplaints"), new HashSet < PropositionalFormula > (Arrays.asList(resolvecomplaintsNOK)), new HashSet < PropositionalFormula > (Arrays.asList(resolvecomplaintsOK)), "", "", 19, 7, 7, 3);
    public static Action backupwebsite = new Action(new Proposition("backupwebsite"), new HashSet < PropositionalFormula > (Arrays.asList(backupwebsiteNOK)), new HashSet < PropositionalFormula > (Arrays.asList(backupwebsiteOK)), "", "", 11, 10, 6, 6);
    public static Action fixerrors = new Action(new Proposition("fixerrors"), new HashSet < PropositionalFormula > (Arrays.asList(fixerrorsNOK)), new HashSet < PropositionalFormula > (Arrays.asList(fixerrorsOK)), "", "", 14, 12, 10, 3);
    public static Action improvedesign = new Action(new Proposition("improvedesign"), new HashSet < PropositionalFormula > (Arrays.asList(improvedesignNOK)), new HashSet < PropositionalFormula > (Arrays.asList(improvedesignOK)), "", "", 12, 12, 6, 11);
    public static Action backupdatabase = new Action(new Proposition("backupdatabase"), new HashSet < PropositionalFormula > (Arrays.asList(backupdatabaseNOK)), new HashSet < PropositionalFormula > (Arrays.asList(backupdatabaseOK)), "", "", 9, 8, 7, 5);
    public static Action encryptcredentials = new Action(new Proposition("encryptcredentials"), new HashSet < PropositionalFormula > (Arrays.asList(encryptcredentialsNOK)), new HashSet < PropositionalFormula > (Arrays.asList(encryptcredentialsOK)), "", "", 8, 8, 5, 4);
    public static Action improveperformance = new Action(new Proposition("improveperformance"), new HashSet < PropositionalFormula > (Arrays.asList(improveperformanceNOK)), new HashSet < PropositionalFormula > (Arrays.asList(improveperformanceOK)), "", "", 15, 8, 7, 9);
    public static Action optimisesupplychains = new Action(new Proposition("optimisesupplychains"), new HashSet < PropositionalFormula > (Arrays.asList(optimiseSupplyChainsNOK)), new HashSet < PropositionalFormula > (Arrays.asList(optimiseSupplyChainsOK)), "", "", 20, 17, 7, 10);
    public static Action securewarehouse = new Action(new Proposition("securewarehouse"), new HashSet < PropositionalFormula > (Arrays.asList(warehouseSecurityNOK)), new HashSet < PropositionalFormula > (Arrays.asList(warehouseSecurityOK)), "", "", 11, 7, 7, 6);
    public static Action modernisewarehouse = new Action(new Proposition("modernisewarehouse"), new HashSet < PropositionalFormula > (Arrays.asList(warehouseModernityNOK)), new HashSet < PropositionalFormula > (Arrays.asList(warehouseModernityOK)), "", "", 14, 13, 9, 13);
    public static Action processinvoicesnorders = new Action(new Proposition("processinvoicesnorders"), new HashSet < PropositionalFormula > (Arrays.asList(IOProcessedNOK)), new HashSet < PropositionalFormula > (Arrays.asList(IOProcessedOK)), "", "", 9, 7, 4, 3);
    public static Action backupfinancedata = new Action(new Proposition("backupfinancedata"), new HashSet < PropositionalFormula > (Arrays.asList(financeDataBackedUpNOK)), new HashSet < PropositionalFormula > (Arrays.asList(financeDataBackedUpOK)), "", "", 8, 8, 3, 6);
    public static Action executereporting = new Action(new Proposition("executereporting"), new HashSet < PropositionalFormula > (Arrays.asList(financialReportingNOK)), new HashSet < PropositionalFormula > (Arrays.asList(financialReportingOK)), "", "", 13, 10, 5, 2);
    public static Action auditfinances = new Action(new Proposition("auditfinances"), new HashSet < PropositionalFormula > (Arrays.asList(financialControlNOK)), new HashSet < PropositionalFormula > (Arrays.asList(financialControlOK)), "", "", 19, 13, 8, 11);

    public static List < Action > setOfActions = new ArrayList < Action > ();

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

    public static List < Policy > setOfPolicies = new ArrayList < Policy > ();

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
        for (Policy policy: setOfPolicies) {
            policy.reward = rand.nextFloat() * 20;
            policy.punishment = rand.nextFloat() + 0.0001f;
        }
    }

    public static Set < PropositionalFormula > stateOfGame = new HashSet < PropositionalFormula > ();

    private static void initialiseStateOfGame() {
        stateOfGame.add(start);
        for (Action action: setOfActions) {
            stateOfGame.addAll(action.preCondition);
        }
    }

    public static Map < String, Set < Action >> roleActionsMap = new HashMap < String, Set < Action >> ();

    private static void initialiseRoleActionsMap() {
        for (Action action: setOfActions) {
            if (!action.role1.contentEquals("")) {
                try {
                    roleActionsMap.get(action.role1).add(action);
                } catch (Exception e) {
                    roleActionsMap.put(action.role1, new HashSet < Action > ());
                }
            }

            if (!action.role2.contentEquals("")) {
                try {
                    roleActionsMap.get(action.role2).add(action);
                } catch (Exception e) {
                    roleActionsMap.put(action.role2, new HashSet < Action > ());
                }
            }
        }
    }
    /*
     * adapted from https://www.geeksforgeeks.org/randomly-select-items-from-a-list-in-java/
     */

    public static List < Action > getPlayersActions(List < Action > setofactions, int totalactions) {

        // create a temporary list for storing 
        // selected element 
        List < Action > newActions = new ArrayList < Action > ();
        List < Action > listactions = new ArrayList < Action > (setofactions);
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

    public static List < Policy > applicablePolicies(List < Action > setofactions) {

        List < Policy > applicablePolicies = new ArrayList < Policy > ();
        for (Action action: setofactions) {
            for (Policy policy: setOfPolicies) {
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
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


    public static void main(String[] args) throws Exception {
        initialiseSetOfActions();
        initialiseSetOfPolicies();
        initialiseStateOfGame();
        initialiseRoleActionsMap();
        
        double[][] setups = {
                {2.1, 1, 16.6, 0.3, 10.5, 0.8, 6.9, 0.9, 3.5, 0, 13.4, 0.2, 7.1, 0.6, 12.8, 0.9, 5, 0.8},
                {19, 0.2, 11.8, 0.9, 8.1, 0.3, 9.4, 0.1, 16.1, 0.5, 17.4, 1, 18.1, 0.6, 18.8, 0.7, 9, 0.9},
                {11.4, 0, 16.5, 0.3, 0.7, 0.4, 11.3, 0.3, 2.8, 0.1, 15.9, 0.5, 6.7, 0.5, 7.7, 0.1, 9.3, 0.4},
                {0.3, 0.3, 2.6, 0.4, 9, 0.9, 15, 0.2, 5.3, 0.2, 16.7, 0.9, 6.2, 0.8, 8.1, 0.5, 9, 0.6},
                {14.8, 0.7, 3.4, 0.3, 1.4, 0.5, 3.9, 0.2, 5.6, 0, 7.3, 0.2, 0.2, 0.6, 13.9, 0.5, 1.4, 0.4},
                {0.1, 0.3, 8.5, 0.3, 5.9, 0, 7.8, 0.5, 16.8, 1, 5.4, 0.9, 3, 0.7, 16, 0.9, 2.1, 0.2},
                {18.2, 0.2, 15.9, 0.1, 6.4, 0.8, 2.4, 0.5, 10.1, 0.9, 13.8, 0.7, 17.8, 0.9, 14.4, 0.4, 18.4, 0.4},
                {16.8, 0.8, 7.1, 0.9, 12.6, 0.4, 14.2, 0.5, 3.3, 0.9, 14.1, 0.2, 12.3, 1, 1.9, 1, 2.8, 0.2},
                {10.4, 0.4, 15.2, 0.8, 19.7, 0.7, 16.4, 0.6, 16.5, 0.2, 15.4, 0.2, 10.3, 0.2, 8.5, 0.4, 6.1, 0.9},
                {10.3, 0.1, 14.9, 0.6, 8, 0.1, 1.5, 0.6, 7.7, 0.3, 3.4, 0.3, 16.2, 0.6, 0.5, 0, 13.5, 0.1},
                {8.9 ,0.5 ,12.6 ,0.9 ,16 ,0.8 ,3.7 ,0.7 ,19.9 ,0.1 ,18.6 ,0.3 ,3.2 ,0.1 ,10.8 ,0.5 ,11.4 ,0.5},
                {14 ,0.9 ,12.5 ,0.4 ,17.7 ,0.8 ,5.6 ,0.9 ,8.1 ,0.5 ,2.2 ,0.7 ,12.8 ,0.9 ,5.9 ,0.8 ,1.5 ,0.3},
                {18.9 ,0.9 ,1.2 ,0 ,17.7 ,0 ,19.9 ,0.1 ,2.7 ,0.8 ,17.7 ,0.3 ,0.1 ,1 ,9.2 ,0.5 ,3.1 ,0.7},
                {10.3 ,0.4 ,3.7 ,0.8 ,3.2 ,0.5 ,12.2 ,0.6 ,7.9 ,0.4 ,18.6 ,0 ,8.3 ,0.8 ,19.8 ,0.3 ,0.4 ,0.9},
                {16.1 ,0.2 ,2.1 ,0.9 ,15.4 ,0.5 ,13.7 ,0.5 ,4.7 ,0.5 ,18.4 ,0.1 ,3 ,0.7 ,13.7 ,0.5 ,0.2 ,0.3},
                {2.9 ,0.3 ,10.7 ,0.8 ,17.5 ,0.2 ,3.7 ,0.5 ,1.7 ,1 ,18.7 ,0.5 ,7.3 ,0.1 ,1 ,0.7 ,7.1 ,0.4},
                {15.2 ,0.4 ,16.2 ,0.7 ,7.7 ,0.3 ,13.5 ,0.3 ,13.2 ,0.4 ,16.7 ,1 ,0.2 ,0.5 ,12.7 ,1 ,9.4 ,0.4},
                {10.7 ,0.5 ,19.9 ,0.2 ,13.4 ,0.1 ,10.3 ,0.2 ,11.3 ,0.5 ,9.4 ,0.4 ,12.9 ,0.4 ,11.6 ,0.8 ,12.3 ,0.8},
                {7.8 ,0.4 ,16.5 ,0.6 ,12.6 ,0.8 ,18.6 ,1 ,15.4 ,0.7 ,5.3 ,0.2 ,5.6 ,0.7 ,16 ,0.6 ,13.7 ,0.1},
                {19.5 ,0.3 ,5.4 ,0.8 ,2.1 ,0 ,10.2 ,0.4 ,8.6 ,0.8 ,12.5 ,0.4 ,5.4 ,0.2 ,13.5 ,0.5 ,6.2 ,1},
                {19.1 ,0.6 ,17.7 ,0.2 ,3.4 ,0.4 ,15.4 ,0.3 ,13.9 ,0.2 ,7.2 ,0.2 ,11 ,0.8 ,1 ,0.9 ,2.7 ,0.1},
                {11 ,0.8 ,3.5 ,0 ,11.8 ,0.8 ,7 ,0.1 ,12.7 ,0.9 ,8.8 ,0.9 ,0.4 ,0.6 ,11.9 ,0.9 ,0.4 ,0.9},
                {18.5 ,0.2 ,9.5 ,0.2 ,0.9 ,0.6 ,11.4 ,0.4 ,3.9 ,0.2 ,10.3 ,0.9 ,7.8 ,0.4 ,1.4 ,1 ,10.5 ,0.6},
                {12.2 ,0.1 ,14.6 ,0.5 ,0 ,0.2 ,11 ,0.8 ,10.6 ,0.3 ,12.2 ,0.9 ,4.8 ,0 ,13.6 ,0.8 ,0.5 ,0.6},
                {19 ,0.9 ,16.2 ,0 ,18.9 ,0.5 ,15.2 ,0.4 ,7.8 ,0.1 ,18.4 ,0.4 ,6.1 ,0.7 ,7 ,0 ,12.7 ,0.6},
                {18.4 ,0.8 ,3.2 ,0.5 ,16.4 ,0.3 ,1.2 ,1 ,3.3 ,0.4 ,5.9 ,0.3 ,13.4 ,0.1 ,5.7 ,0.2 ,0.6 ,1},
                {9.8 ,0.9 ,4.7 ,0.3 ,18.6 ,0.7 ,16.7 ,1 ,6.1 ,0.8 ,7.7 ,0.7 ,4.9 ,0.8 ,18.8 ,0.8 ,14.7 ,0},
                {12.9 ,0.6 ,15.8 ,0.7 ,13.7 ,0.5 ,14.9 ,0.2 ,17.9 ,0.1 ,7.3 ,0.5 ,19.6 ,0.6 ,17.4 ,0.7 ,3.6 ,0.1},
                {0.3 ,0.6 ,3 ,0 ,15.5 ,0.6 ,8.8 ,0.4 ,8.3 ,0.7 ,4.6 ,0.3 ,16.2 ,0.2 ,18.7 ,0.5 ,13.6 ,0.7},
                {15 ,0.1 ,16 ,0.6 ,6.3 ,0.7 ,18.6 ,0 ,4.5 ,0.5 ,1.4 ,0.6 ,9.4 ,0 ,16.5 ,0.6 ,18.9 ,0.1}          
            };
        
        for(int taille = 0; taille < setups.length; taille++) {
            File thefile = new File("results.csv");
            thefile.delete();
        	int numero = 0;
            for (Policy policy: setOfPolicies) {
                policy.reward = (float)(setups[taille][2 * numero]);
                policy.punishment = (float)(setups[taille][2 * numero + 1]);
                numero++;
            }
            
            Pair<ArrayList<Float>, Float> idealresult = optimiseGP(false, 10);
            System.out.println("END OF SIMULATION" + idealresult.toString());
        }

        //Pair<ArrayList<Float>, Float> idealresult_gp = optimiseGP(true, 10);
        //Pair<ArrayList<Float>, Float> idealresult_rbf = optimiseRBF(false, 10);
        //Pair<ArrayList<Float>, Float> idealresult_rnd = optimiseRND(true, 10);
        //Pair<ArrayList<Float>, Float> idealresult_svr = optimiseSVR(true, 10);
        //Pair<ArrayList<Float>, Float> idealresult_pol = optimisePOL(false, 10);

        //System.out.println("END OF SIMULATION" + idealresult_gp.toString());
        //System.out.println("END OF SIMULATION" + idealresult_rbf.toString());
        //System.out.println("END OF SIMULATION" + idealresult_rnd.toString());
        //System.out.println("END OF SIMULATION" + idealresult_svr.toString());
        //System.out.println("END OF SIMULATION" + idealresult_pol.toString());
    }



    public static Pair < ArrayList < Float > , Float > optimiseGP(boolean cem, int melite) throws Exception {
        ArrayList < Float > max_datum = new ArrayList < Float > ();
        ArrayList < Float > avgscore = new ArrayList < Float > ();
        ArrayList < Float > scorestdev = new ArrayList < Float > ();
        ArrayList < Float > avgviol = new ArrayList < Float > ();
        ArrayList < Float > avgcompl = new ArrayList < Float > ();
        ArrayList < Float > avgheur = new ArrayList < Float > ();

        Float maxavgscore = 0.0f;
        ArrayList < ArrayList < Float >> data = new ArrayList < ArrayList < Float >> ();

        int totalactions = 3;

        for (int i = 0; i <= 100; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(100);
            for (int runid = 1; runid <= 30; runid++) {
                int numPlayers = rand.nextInt(10) + 1;
                Map < String, Player > playerMap = new HashMap < String, Player > ();
                for (int k = 0; k < numPlayers; k++) {
                    String playerName = "agent" + Integer.toString(k);
                    List < Action > playerActions = getPlayersActions(setOfActions, totalactions);
                    List < Policy > playerPolicies = applicablePolicies(playerActions);
                    playerMap.put(playerName, new Player(playerActions, playerPolicies, playerName, "", 1, 1, runid));
                    playerMap.get(playerName).setName(playerName);
                }
                Map < String, GameEngine > geMap = new HashMap < String, GameEngine > ();
                geMap.put("gameengine", new GameEngine("gameengine", 1 * 100 + 1, 1, 1, runid));

                Game game = new Game(geMap, playerMap);
                executor.submit(game);
            }
            executor.awaitTermination(14, TimeUnit.SECONDS);
            executor.shutdown();

            ArrayList < Float > datum = new ArrayList < Float > ();
            for (Policy policy: setOfPolicies) {
                datum.add(policy.reward);
                datum.add(policy.punishment);
            }

            Map < String, Float > polScore = new HashMap < String, Float > ();
            Map < String, Float > polStdev = new HashMap < String, Float > ();
            Map < String, Float > polCompl = new HashMap < String, Float > ();
            Map < String, Float > polViol = new HashMap < String, Float > ();
            Map < String, Float > polHeur = new HashMap < String, Float > ();
            Map < String, Integer > polNbre = new HashMap < String, Integer > ();

            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            String[] nextLine;
            int counter = 0;
            Float score = 0.0f, stdev = 0.0f, compl = 0.0f, viol = 0.0f, heur = 0.0f;
            while ((nextLine = reader.readNext()) != null) {
                if (Float.parseFloat(nextLine[0]) != 0.0) {
                    counter++;
                    score += Float.parseFloat(nextLine[0]);
                    stdev += Float.parseFloat(nextLine[1]);
                    String[] policies = nextLine[6].replace("[", "").replace("]", "").split(",");
                    String[] policies_viol = nextLine[7].replace("[", "").replace("]", "").split(",");
                    String[] policies_comp = nextLine[8].replace("[", "").replace("]", "").split(",");
                    String[] violations = nextLine[4].replace("[", "").replace("]", "").split(",");
                    String[] compliances = nextLine[5].replace("[", "").replace("]", "").split(",");

                    //	                ArrayList<String> policies = (ArrayList<String>) ((Object)nextLine[6]);
                    //	                ArrayList<Integer> policies_viol = (ArrayList<Integer>) ((Object)nextLine[7]);
                    //	                ArrayList<Integer> policies_comp = (ArrayList<Integer>) ((Object)nextLine[8]);
                    for (int bb = 0; bb < violations.length; bb++) {
                        if (!violations[bb].contentEquals("")) {
                            viol += Float.parseFloat(violations[bb]) / violations.length;
                            compl += Float.parseFloat(compliances[bb]) / violations.length;
                        }
                    }
                    for (int aa = 0; aa < policies.length; aa++) {
                        if (!policies[aa].contentEquals("")) {
                            if (polScore.containsKey(policies[aa])) {
                                polScore.put(policies[aa], polScore.get(policies[aa]) + Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], polStdev.get(policies[aa]) + Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], polNbre.get(policies[aa]) + 1);
                                polViol.put(policies[aa], polViol.get(policies[aa]) + Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], polCompl.get(policies[aa]) + Float.parseFloat(policies_comp[aa]));
                            } else {
                                polScore.put(policies[aa], Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], 1);
                                polViol.put(policies[aa], Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], Float.parseFloat(policies_comp[aa]));
                            }

                            //	            	    System.out.println(policies_viol[aa]);
                        }
                    }
                }
            }
            for (String policy: polScore.keySet()) {
                polScore.put(policy, polScore.get(policy) / polNbre.get(policy));
                polStdev.put(policy, polStdev.get(policy) / polNbre.get(policy));
                polCompl.put(policy, polCompl.get(policy) / polNbre.get(policy));
                polViol.put(policy, polViol.get(policy) / polNbre.get(policy));
                polHeur.put(policy, (1 + polScore.get(policy)) / (1 + polStdev.get(policy)) + (1 + polCompl.get(policy)) / (1 + polViol.get(policy)));
            }

            //    	    Float min_result = 1000f;
            //    	    Float avg_result = 0f;
            //    	    String min_setup = "";
            //    	    //finding the point with the maximum prediction
            //    	    for(String policy:polHeur.keySet()) {
            //    	    	if (polHeur.get(policy) < min_result) {
            //    	    		min_result = polHeur.get(policy);
            //    	    		min_setup = policy;
            //    	    	}
            //    	    	avg_result += polHeur.get(policy);
            //    	    }
            //    	    
            //    	    avg_result /= polHeur.size();

            //            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            //            String[] nextLine;
            //            int counter = 0;
            //            Float score = 0.0f, stdev = 0.0f;
            //            while ((nextLine = reader.readNext()) != null) {
            //            	if(Float.parseFloat(nextLine[0]) != 0.0) {
            //            		counter++;
            //            		score += Float.parseFloat(nextLine[0]);
            //            		stdev += Float.parseFloat(nextLine[1]);
            //            	}
            //            }

            reader.close();
            File onefile = new File("results.csv");
            onefile.delete();

            score /= counter;
            stdev /= counter;
            viol /= counter;
            compl /= counter;
            heur = (1 + score) / (1 + stdev) + (1 + compl) / (1 + viol);
            ArrayList < Float > longdatum = new ArrayList < Float > ();

            for (int index = 0; index < datum.size(); index++) {
                longdatum.add(datum.get(index));
            }

            longdatum.add(score);
            longdatum.add(stdev);
            longdatum.add(viol);
            longdatum.add(compl);
            longdatum.add(heur);
            String[] content = new String[longdatum.size()];

            for (int index = 0; index < longdatum.size(); index++) {
                content[index] = Float.toString(longdatum.get(index));
            }

            String filename;
            if (cem) {
                filename = "history_gp_cem.csv";
            } else {
                filename = "history_gp.csv";
            }
            File file = new File(filename);
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(content);
            writer.close();

            if (score > maxavgscore) {
                maxavgscore = score;
                max_datum = datum;
            }
            data.add(datum);
            avgscore.add(score);
            scorestdev.add(stdev);
            avgviol.add(viol);
            avgcompl.add(compl);
            avgheur.add(heur);

            double[][] x = new double[data.size()][datum.size()];
            for (int ji = 0; ji < data.size(); ji++) {
                for (int el = 0; el < datum.size(); el++) {
                    x[ji][el] = data.get(ji).get(el);
                }
            }
            double[] y = new double[avgscore.size()];
            for (int l = 0; l < avgscore.size(); l++) {
                //    		  y[l] = avgscore.get(l);
                y[l] = avgheur.get(l);
            }

            if (i < 2 * melite) {
                setPoliciesValues();
            } else {
                GaussianProcessRegression < double[] > gp = new GaussianProcessRegression < double[] > (x, y, new GaussianKernel(0.2), 0.01);
                if (cem) {
                    double[][] elites = getMElites(x, y, melite);
                    double[][] proposals = getSamples(elites, 1000);

                    double[] results = gp.predict(proposals);
                    double max_result = 0;
                    double[] max_setup = proposals[rand.nextInt(100)];
                    //finding the point with the maximum prediction
                    for (int pl = 0; pl < proposals.length; pl++) {
                        if (results[pl] > max_result) {
                            max_result = results[pl];
                            max_setup = proposals[pl];
                        }
                    }

                    if (rand.nextDouble() <= (1 / i)) {
                        setPoliciesValues(); //epsilon-greedy strategy
                    } else {
                        int numero = 0;
                        for (Policy policy: setOfPolicies) {
                            policy.reward = (float)(max_setup[2 * numero]);
                            policy.punishment = (float)(max_setup[2 * numero + 1]);
                            numero++;
                        }
                    }
                } else {
                    //placeholder to generate 10000 data points
                    double[][] proposals = new double[1000][datum.size()];
                    //generating the 30 data points
                    for (int ligne = 0; ligne < proposals.length; ligne++) {
                        for (int colonne = 0; colonne < datum.size(); colonne++) {
                            if (colonne % 2 == 0) {
                                proposals[ligne][colonne] = rand.nextDouble() * 20;
                            } else {
                                proposals[ligne][colonne] = rand.nextDouble() + 0.0001;
                            }
                        }
                    }
                    //using surrogate model to get predictions
                    double[] results = gp.predict(proposals);
                    double max_result = 0;
                    double[] max_setup = proposals[rand.nextInt(100)];
                    //finding the point with the maximum prediction
                    for (int pl = 0; pl < proposals.length; pl++) {
                        if (results[pl] > max_result) {
                            max_result = results[pl];
                            max_setup = proposals[pl];
                        }
                    }
                    //choose to explore the maximum selected or to explore
                    if (rand.nextDouble() <= (1 / i)) {
                        setPoliciesValues(); //epsilon-greedy strategy
                    } else {
                        int numero = 0;
                        for (Policy policy: setOfPolicies) {
                            policy.reward = (float)(max_setup[2 * numero]);
                            policy.punishment = (float)(max_setup[2 * numero + 1]);
                            numero++;
                        }
                    }
                }
            }
        }
        return new Pair < ArrayList < Float > , Float > (max_datum, maxavgscore);
    }

    public static Pair < ArrayList < Float > , Float > optimiseRBF(boolean cem, int melite) throws Exception {
        ArrayList < Float > max_datum = new ArrayList < Float > ();
        ArrayList < Float > avgscore = new ArrayList < Float > ();
        ArrayList < Float > scorestdev = new ArrayList < Float > ();
        ArrayList < Float > avgviol = new ArrayList < Float > ();
        ArrayList < Float > avgcompl = new ArrayList < Float > ();
        ArrayList < Float > avgheur = new ArrayList < Float > ();
        Float maxavgscore = 0.0f;
        ArrayList < ArrayList < Float >> data = new ArrayList < ArrayList < Float >> ();
        //ArrayList<ArrayList<Float>> longdata = new ArrayList<ArrayList<Float>>();
        double[][] centers = {
            {2.1, 1, 16.6, 0.3, 10.5, 0.8, 6.9, 0.9, 3.5, 0, 13.4, 0.2, 7.1, 0.6, 12.8, 0.9, 5, 0.8},
            {19, 0.2, 11.8, 0.9, 8.1, 0.3, 9.4, 0.1, 16.1, 0.5, 17.4, 1, 18.1, 0.6, 18.8, 0.7, 9, 0.9},
            {11.4, 0, 16.5, 0.3, 0.7, 0.4, 11.3, 0.3, 2.8, 0.1, 15.9, 0.5, 6.7, 0.5, 7.7, 0.1, 9.3, 0.4},
            {0.3, 0.3, 2.6, 0.4, 9, 0.9, 15, 0.2, 5.3, 0.2, 16.7, 0.9, 6.2, 0.8, 8.1, 0.5, 9, 0.6},
            {14.8, 0.7, 3.4, 0.3, 1.4, 0.5, 3.9, 0.2, 5.6, 0, 7.3, 0.2, 0.2, 0.6, 13.9, 0.5, 1.4, 0.4},
            {0.1, 0.3, 8.5, 0.3, 5.9, 0, 7.8, 0.5, 16.8, 1, 5.4, 0.9, 3, 0.7, 16, 0.9, 2.1, 0.2},
            {18.2, 0.2, 15.9, 0.1, 6.4, 0.8, 2.4, 0.5, 10.1, 0.9, 13.8, 0.7, 17.8, 0.9, 14.4, 0.4, 18.4, 0.4},
            {16.8, 0.8, 7.1, 0.9, 12.6, 0.4, 14.2, 0.5, 3.3, 0.9, 14.1, 0.2, 12.3, 1, 1.9, 1, 2.8, 0.2},
            {10.4, 0.4, 15.2, 0.8, 19.7, 0.7, 16.4, 0.6, 16.5, 0.2, 15.4, 0.2, 10.3, 0.2, 8.5, 0.4, 6.1, 0.9},
            {10.3, 0.1, 14.9, 0.6, 8, 0.1, 1.5, 0.6, 7.7, 0.3, 3.4, 0.3, 16.2, 0.6, 0.5, 0, 13.5, 0.1},
            {8.9 ,0.5 ,12.6 ,0.9 ,16 ,0.8 ,3.7 ,0.7 ,19.9 ,0.1 ,18.6 ,0.3 ,3.2 ,0.1 ,10.8 ,0.5 ,11.4 ,0.5},
            {14 ,0.9 ,12.5 ,0.4 ,17.7 ,0.8 ,5.6 ,0.9 ,8.1 ,0.5 ,2.2 ,0.7 ,12.8 ,0.9 ,5.9 ,0.8 ,1.5 ,0.3},
            {18.9 ,0.9 ,1.2 ,0 ,17.7 ,0 ,19.9 ,0.1 ,2.7 ,0.8 ,17.7 ,0.3 ,0.1 ,1 ,9.2 ,0.5 ,3.1 ,0.7},
            {10.3 ,0.4 ,3.7 ,0.8 ,3.2 ,0.5 ,12.2 ,0.6 ,7.9 ,0.4 ,18.6 ,0 ,8.3 ,0.8 ,19.8 ,0.3 ,0.4 ,0.9},
            {16.1 ,0.2 ,2.1 ,0.9 ,15.4 ,0.5 ,13.7 ,0.5 ,4.7 ,0.5 ,18.4 ,0.1 ,3 ,0.7 ,13.7 ,0.5 ,0.2 ,0.3},
            {2.9 ,0.3 ,10.7 ,0.8 ,17.5 ,0.2 ,3.7 ,0.5 ,1.7 ,1 ,18.7 ,0.5 ,7.3 ,0.1 ,1 ,0.7 ,7.1 ,0.4},
            {15.2 ,0.4 ,16.2 ,0.7 ,7.7 ,0.3 ,13.5 ,0.3 ,13.2 ,0.4 ,16.7 ,1 ,0.2 ,0.5 ,12.7 ,1 ,9.4 ,0.4},
            {10.7 ,0.5 ,19.9 ,0.2 ,13.4 ,0.1 ,10.3 ,0.2 ,11.3 ,0.5 ,9.4 ,0.4 ,12.9 ,0.4 ,11.6 ,0.8 ,12.3 ,0.8},
            {7.8 ,0.4 ,16.5 ,0.6 ,12.6 ,0.8 ,18.6 ,1 ,15.4 ,0.7 ,5.3 ,0.2 ,5.6 ,0.7 ,16 ,0.6 ,13.7 ,0.1},
            {19.5 ,0.3 ,5.4 ,0.8 ,2.1 ,0 ,10.2 ,0.4 ,8.6 ,0.8 ,12.5 ,0.4 ,5.4 ,0.2 ,13.5 ,0.5 ,6.2 ,1},
            {19.1 ,0.6 ,17.7 ,0.2 ,3.4 ,0.4 ,15.4 ,0.3 ,13.9 ,0.2 ,7.2 ,0.2 ,11 ,0.8 ,1 ,0.9 ,2.7 ,0.1},
            {11 ,0.8 ,3.5 ,0 ,11.8 ,0.8 ,7 ,0.1 ,12.7 ,0.9 ,8.8 ,0.9 ,0.4 ,0.6 ,11.9 ,0.9 ,0.4 ,0.9},
            {18.5 ,0.2 ,9.5 ,0.2 ,0.9 ,0.6 ,11.4 ,0.4 ,3.9 ,0.2 ,10.3 ,0.9 ,7.8 ,0.4 ,1.4 ,1 ,10.5 ,0.6},
            {12.2 ,0.1 ,14.6 ,0.5 ,0 ,0.2 ,11 ,0.8 ,10.6 ,0.3 ,12.2 ,0.9 ,4.8 ,0 ,13.6 ,0.8 ,0.5 ,0.6},
            {19 ,0.9 ,16.2 ,0 ,18.9 ,0.5 ,15.2 ,0.4 ,7.8 ,0.1 ,18.4 ,0.4 ,6.1 ,0.7 ,7 ,0 ,12.7 ,0.6},
            {18.4 ,0.8 ,3.2 ,0.5 ,16.4 ,0.3 ,1.2 ,1 ,3.3 ,0.4 ,5.9 ,0.3 ,13.4 ,0.1 ,5.7 ,0.2 ,0.6 ,1},
            {9.8 ,0.9 ,4.7 ,0.3 ,18.6 ,0.7 ,16.7 ,1 ,6.1 ,0.8 ,7.7 ,0.7 ,4.9 ,0.8 ,18.8 ,0.8 ,14.7 ,0},
            {12.9 ,0.6 ,15.8 ,0.7 ,13.7 ,0.5 ,14.9 ,0.2 ,17.9 ,0.1 ,7.3 ,0.5 ,19.6 ,0.6 ,17.4 ,0.7 ,3.6 ,0.1},
            {0.3 ,0.6 ,3 ,0 ,15.5 ,0.6 ,8.8 ,0.4 ,8.3 ,0.7 ,4.6 ,0.3 ,16.2 ,0.2 ,18.7 ,0.5 ,13.6 ,0.7},
            {15 ,0.1 ,16 ,0.6 ,6.3 ,0.7 ,18.6 ,0 ,4.5 ,0.5 ,1.4 ,0.6 ,9.4 ,0 ,16.5 ,0.6 ,18.9 ,0.1}          
        };

        //    	for(int a = 0; a < centers.length; a++) {
        //    		for(int b = 0; b < setOfPolicies.size()*2; b++) {
        //    			if(b % 2 == 0) {
        //    				centers[a][b] = 2 * (a + 1);
        //    			}
        //    			else {
        //    				centers[a][b] = 0.09 *(a + 1);
        //    			}
        //    		}
        //    	}

        int totalactions = 3;

        for (int i = 0; i <= 100; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(30);
            for (int runid = 1; runid <= 30; runid++) {
                int numPlayers = rand.nextInt(10) + 1;
                Map < String, Player > playerMap = new HashMap < String, Player > ();
                for (int k = 0; k < numPlayers; k++) {
                    String playerName = "agent" + Integer.toString(k);
                    List < Action > playerActions = getPlayersActions(setOfActions, totalactions);
                    List < Policy > playerPolicies = applicablePolicies(playerActions);
                    playerMap.put(playerName, new Player(playerActions, playerPolicies, playerName, "", 1, 1, runid));
                    playerMap.get(playerName).setName(playerName);
                    //		        	Util.insertMapping(1, 3, runid, playerName, playerActions.size(), playerPolicies.size());
                }
                Map < String, GameEngine > geMap = new HashMap < String, GameEngine > ();
                geMap.put("gameengine", new GameEngine("gameengine", 1 * 100 + 1, 1, 1, runid));

                Game game = new Game(geMap, playerMap);
                executor.submit(game);
            }
            executor.awaitTermination(14, TimeUnit.SECONDS);
            executor.shutdown();

            ArrayList < Float > datum = new ArrayList < Float > ();
            for (Policy policy: setOfPolicies) {
                datum.add(policy.reward);
                datum.add(policy.punishment);
            }

            Map < String, Float > polScore = new HashMap < String, Float > ();
            Map < String, Float > polStdev = new HashMap < String, Float > ();
            Map < String, Float > polCompl = new HashMap < String, Float > ();
            Map < String, Float > polViol = new HashMap < String, Float > ();
            Map < String, Float > polHeur = new HashMap < String, Float > ();
            Map < String, Integer > polNbre = new HashMap < String, Integer > ();

            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            String[] nextLine;
            int counter = 0;
            Float score = 0.0f, stdev = 0.0f, compl = 0.0f, viol = 0.0f, heur = 0.0f;
            while ((nextLine = reader.readNext()) != null) {
                if (Float.parseFloat(nextLine[0]) != 0.0) {
                    counter++;
                    score += Float.parseFloat(nextLine[0]);
                    stdev += Float.parseFloat(nextLine[1]);
                    String[] policies = nextLine[6].replace("[", "").replace("]", "").split(",");
                    String[] policies_viol = nextLine[7].replace("[", "").replace("]", "").split(",");
                    String[] policies_comp = nextLine[8].replace("[", "").replace("]", "").split(",");
                    String[] violations = nextLine[4].replace("[", "").replace("]", "").split(",");
                    String[] compliances = nextLine[5].replace("[", "").replace("]", "").split(",");

                    //	                ArrayList<String> policies = (ArrayList<String>) ((Object)nextLine[6]);
                    //	                ArrayList<Integer> policies_viol = (ArrayList<Integer>) ((Object)nextLine[7]);
                    //	                ArrayList<Integer> policies_comp = (ArrayList<Integer>) ((Object)nextLine[8]);
                    for (int bb = 0; bb < violations.length; bb++) {
                        if (!violations[bb].contentEquals("")) {
                            viol += Float.parseFloat(violations[bb]) / violations.length;
                            compl += Float.parseFloat(compliances[bb]) / violations.length;
                        }
                    }
                    for (int aa = 0; aa < policies.length; aa++) {
                        if (!policies[aa].contentEquals("")) {
                            if (polScore.containsKey(policies[aa])) {
                                polScore.put(policies[aa], polScore.get(policies[aa]) + Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], polStdev.get(policies[aa]) + Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], polNbre.get(policies[aa]) + 1);
                                polViol.put(policies[aa], polViol.get(policies[aa]) + Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], polCompl.get(policies[aa]) + Float.parseFloat(policies_comp[aa]));
                            } else {
                                polScore.put(policies[aa], Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], 1);
                                polViol.put(policies[aa], Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], Float.parseFloat(policies_comp[aa]));
                            }

                            //	            	    System.out.println(policies_viol[aa]);
                        }
                    }
                }
            }
            for (String policy: polScore.keySet()) {
                polScore.put(policy, polScore.get(policy) / polNbre.get(policy));
                polStdev.put(policy, polStdev.get(policy) / polNbre.get(policy));
                polCompl.put(policy, polCompl.get(policy) / polNbre.get(policy));
                polViol.put(policy, polViol.get(policy) / polNbre.get(policy));
                polHeur.put(policy, (1 + polScore.get(policy)) / (1 + polStdev.get(policy)) + (1 + polCompl.get(policy)) / (1 + polViol.get(policy)));
            }

            //    	    Float min_result = 1000f;
            //    	    Float avg_result = 0f;
            //    	    String min_setup = "";
            //    	    //finding the point with the maximum prediction
            //    	    for(String policy:polHeur.keySet()) {
            //    	    	if (polHeur.get(policy) < min_result) {
            //    	    		min_result = polHeur.get(policy);
            //    	    		min_setup = policy;
            //    	    	}
            //    	    	avg_result += polHeur.get(policy);
            //    	    }
            //    	    
            //    	    avg_result /= polHeur.size();

            //            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            //            String[] nextLine;
            //            int counter = 0;
            //            Float score = 0.0f, stdev = 0.0f;
            //            while ((nextLine = reader.readNext()) != null) {
            //            	if(Float.parseFloat(nextLine[0]) != 0.0) {
            //            		counter++;
            //            		score += Float.parseFloat(nextLine[0]);
            //            		stdev += Float.parseFloat(nextLine[1]);
            //            	}
            //            }

            reader.close();
            File onefile = new File("results.csv");
            onefile.delete();

            score /= counter;
            stdev /= counter;
            viol /= counter;
            compl /= counter;
            heur = (1 + score) / (1 + stdev) + (1 + compl) / (1 + viol);
            ArrayList < Float > longdatum = new ArrayList < Float > ();

            for (int index = 0; index < datum.size(); index++) {
                longdatum.add(datum.get(index));
            }

            longdatum.add(score);
            longdatum.add(stdev);
            longdatum.add(viol);
            longdatum.add(compl);
            longdatum.add(heur);
            String[] content = new String[longdatum.size()];

            for (int index = 0; index < longdatum.size(); index++) {
                content[index] = Float.toString(longdatum.get(index));
            }

            String filename;
            if (cem) {
                filename = "history_rbf_cem.csv";
            } else {
                filename = "history_rbf.csv";
            }
            File file = new File(filename);
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(content);
            writer.close();

            if (score > maxavgscore) {
                maxavgscore = score;
                max_datum = datum;
            }
            data.add(datum);
            avgscore.add(score);
            scorestdev.add(stdev);
            avgviol.add(viol);
            avgcompl.add(compl);
            avgheur.add(heur);

            double[][] x = new double[data.size()][datum.size()];
            for (int ji = 0; ji < data.size(); ji++) {
                for (int el = 0; el < datum.size(); el++) {
                    x[ji][el] = data.get(ji).get(el);
                }
            }
            double[] y = new double[avgscore.size()];
            for (int l = 0; l < avgscore.size(); l++) {
                y[l] = avgscore.get(l);
            }

            if (i < 2 * melite) {
                setPoliciesValues();
            } else {
                RBFNetwork < double[] > rbf = new RBFNetwork < double[] > (x, y, new EuclideanDistance(), new GaussianRadialBasis(0.2), centers, true);
                if (cem) {
                    double[][] elites = getMElites(x, y, melite);
                    double[][] proposals = getSamples(elites, 1000);

                    double[] results = rbf.predict(proposals);
                    double max_result = 0;
                    double[] max_setup = proposals[0];
                    //finding the point with the maximum prediction
                    for (int pl = 0; pl < proposals.length; pl++) {
                        if (results[pl] > max_result) {
                            max_result = results[pl];
                            max_setup = proposals[pl];
                        }
                    }

                    if (rand.nextDouble() <= (1 / i)) {
                        setPoliciesValues(); //epsilon-greedy strategy
                    } else {
                        int numero = 0;
                        for (Policy policy: setOfPolicies) {
                            policy.reward = (float)(max_setup[2 * numero]);
                            policy.punishment = (float)(max_setup[2 * numero + 1]);
                            numero++;
                        }
                    }
                } else {
                    //placeholder to generate 10000 data points
                    double[][] proposals = new double[1000][datum.size()];
                    //generating the 30 data points
                    for (int ligne = 0; ligne < proposals.length; ligne++) {
                        for (int colonne = 0; colonne < datum.size(); colonne++) {
                            if (colonne % 2 == 0) {
                                proposals[ligne][colonne] = rand.nextDouble() * 20;
                            } else {
                                proposals[ligne][colonne] = rand.nextDouble() + 0.0001;
                            }
                        }
                    }
                    //using surrogate model to get predictions
                    double[] results = rbf.predict(proposals);
                    double max_result = 0;
                    double[] max_setup = proposals[0];
                    //finding the point with the maximum prediction
                    for (int pl = 0; pl < proposals.length; pl++) {
                        if (results[pl] > max_result) {
                            max_result = results[pl];
                            max_setup = proposals[pl];
                        }
                    }
                    //choose to explore the maximum selected or to explore
                    if (rand.nextDouble() <= (1 / i)) {
                        setPoliciesValues(); //epsilon-greedy strategy
                    } else {
                        int numero = 0;
                        for (Policy policy: setOfPolicies) {
                            policy.reward = (float)(max_setup[2 * numero]);
                            policy.punishment = (float)(max_setup[2 * numero + 1]);
                            numero++;
                        }
                    }
                }
            }
        }
        return new Pair < ArrayList < Float > , Float > (max_datum, maxavgscore);
    }


    public static Pair < ArrayList < Float > , Float > optimiseSVR(boolean cem, int melite) throws Exception {
        ArrayList < Float > max_datum = new ArrayList < Float > ();
        ArrayList < Float > avgscore = new ArrayList < Float > ();
        ArrayList < Float > scorestdev = new ArrayList < Float > ();
        ArrayList < Float > avgviol = new ArrayList < Float > ();
        ArrayList < Float > avgcompl = new ArrayList < Float > ();
        ArrayList < Float > avgheur = new ArrayList < Float > ();
        Float maxavgscore = 0.0f;
        ArrayList < ArrayList < Float >> data = new ArrayList < ArrayList < Float >> ();
        //ArrayList<ArrayList<Float>> longdata = new ArrayList<ArrayList<Float>>();
        int totalactions = 3;

        for (int i = 0; i <= 100; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(30);
            for (int runid = 1; runid <= 30; runid++) {
                int numPlayers = rand.nextInt(10) + 1;
                Map < String, Player > playerMap = new HashMap < String, Player > ();
                for (int k = 0; k < numPlayers; k++) {
                    String playerName = "agent" + Integer.toString(k);
                    List < Action > playerActions = getPlayersActions(setOfActions, totalactions);
                    List < Policy > playerPolicies = applicablePolicies(playerActions);
                    playerMap.put(playerName, new Player(playerActions, playerPolicies, playerName, "", 1, 1, runid));
                    playerMap.get(playerName).setName(playerName);
                    //		        	Util.insertMapping(1, 3, runid, playerName, playerActions.size(), playerPolicies.size());
                }
                Map < String, GameEngine > geMap = new HashMap < String, GameEngine > ();
                geMap.put("gameengine", new GameEngine("gameengine", 1 * 100 + 1, 1, 1, runid));

                Game game = new Game(geMap, playerMap);
                executor.submit(game);
            }
            executor.awaitTermination(14, TimeUnit.SECONDS);
            executor.shutdown();

            ArrayList < Float > datum = new ArrayList < Float > ();
            for (Policy policy: setOfPolicies) {
                datum.add(policy.reward);
                datum.add(policy.punishment);
            }

            Map < String, Float > polScore = new HashMap < String, Float > ();
            Map < String, Float > polStdev = new HashMap < String, Float > ();
            Map < String, Float > polCompl = new HashMap < String, Float > ();
            Map < String, Float > polViol = new HashMap < String, Float > ();
            Map < String, Float > polHeur = new HashMap < String, Float > ();
            Map < String, Integer > polNbre = new HashMap < String, Integer > ();

            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            String[] nextLine;
            int counter = 0;
            Float score = 0.0f, stdev = 0.0f, compl = 0.0f, viol = 0.0f, heur = 0.0f;
            while ((nextLine = reader.readNext()) != null) {
                if (Float.parseFloat(nextLine[0]) != 0.0) {
                    counter++;
                    score += Float.parseFloat(nextLine[0]);
                    stdev += Float.parseFloat(nextLine[1]);
                    String[] policies = nextLine[6].replace("[", "").replace("]", "").split(",");
                    String[] policies_viol = nextLine[7].replace("[", "").replace("]", "").split(",");
                    String[] policies_comp = nextLine[8].replace("[", "").replace("]", "").split(",");
                    String[] violations = nextLine[4].replace("[", "").replace("]", "").split(",");
                    String[] compliances = nextLine[5].replace("[", "").replace("]", "").split(",");

                    //	                ArrayList<String> policies = (ArrayList<String>) ((Object)nextLine[6]);
                    //	                ArrayList<Integer> policies_viol = (ArrayList<Integer>) ((Object)nextLine[7]);
                    //	                ArrayList<Integer> policies_comp = (ArrayList<Integer>) ((Object)nextLine[8]);
                    for (int bb = 0; bb < violations.length; bb++) {
                        if (!violations[bb].contentEquals("")) {
                            viol += Float.parseFloat(violations[bb]) / violations.length;
                            compl += Float.parseFloat(compliances[bb]) / violations.length;
                        }
                    }
                    for (int aa = 0; aa < policies.length; aa++) {
                        if (!policies[aa].contentEquals("")) {
                            if (polScore.containsKey(policies[aa])) {
                                polScore.put(policies[aa], polScore.get(policies[aa]) + Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], polStdev.get(policies[aa]) + Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], polNbre.get(policies[aa]) + 1);
                                polViol.put(policies[aa], polViol.get(policies[aa]) + Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], polCompl.get(policies[aa]) + Float.parseFloat(policies_comp[aa]));
                            } else {
                                polScore.put(policies[aa], Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], 1);
                                polViol.put(policies[aa], Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], Float.parseFloat(policies_comp[aa]));
                            }

                            //	            	    System.out.println(policies_viol[aa]);
                        }
                    }
                }
            }
            for (String policy: polScore.keySet()) {
                polScore.put(policy, polScore.get(policy) / polNbre.get(policy));
                polStdev.put(policy, polStdev.get(policy) / polNbre.get(policy));
                polCompl.put(policy, polCompl.get(policy) / polNbre.get(policy));
                polViol.put(policy, polViol.get(policy) / polNbre.get(policy));
                polHeur.put(policy, (1 + polScore.get(policy)) / (1 + polStdev.get(policy)) + (1 + polCompl.get(policy)) / (1 + polViol.get(policy)));
            }

            //    	    Float min_result = 1000f;
            //    	    Float avg_result = 0f;
            //    	    String min_setup = "";
            //    	    //finding the point with the maximum prediction
            //    	    for(String policy:polHeur.keySet()) {
            //    	    	if (polHeur.get(policy) < min_result) {
            //    	    		min_result = polHeur.get(policy);
            //    	    		min_setup = policy;
            //    	    	}
            //    	    	avg_result += polHeur.get(policy);
            //    	    }
            //    	    
            //    	    avg_result /= polHeur.size();

            //            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            //            String[] nextLine;
            //            int counter = 0;
            //            Float score = 0.0f, stdev = 0.0f;
            //            while ((nextLine = reader.readNext()) != null) {
            //            	if(Float.parseFloat(nextLine[0]) != 0.0) {
            //            		counter++;
            //            		score += Float.parseFloat(nextLine[0]);
            //            		stdev += Float.parseFloat(nextLine[1]);
            //            	}
            //            }

            reader.close();
            File onefile = new File("results.csv");
            onefile.delete();

            score /= counter;
            stdev /= counter;
            viol /= counter;
            compl /= counter;
            heur = (1 + score) / (1 + stdev) + (1 + compl) / (1 + viol);
            ArrayList < Float > longdatum = new ArrayList < Float > ();

            for (int index = 0; index < datum.size(); index++) {
                longdatum.add(datum.get(index));
            }

            longdatum.add(score);
            longdatum.add(stdev);
            longdatum.add(viol);
            longdatum.add(compl);
            longdatum.add(heur);
            String[] content = new String[longdatum.size()];

            for (int index = 0; index < longdatum.size(); index++) {
                content[index] = Float.toString(longdatum.get(index));
            }

            String filename;
            if (cem) {
                filename = "history_svr_cem.csv";
            } else {
                filename = "history_svr.csv";
            }
            File file = new File(filename);
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(content);
            writer.close();

            if (score > maxavgscore) {
                maxavgscore = score;
                max_datum = datum;
            }
            data.add(datum);
            avgscore.add(score);
            scorestdev.add(stdev);
            avgviol.add(viol);
            avgcompl.add(compl);
            avgheur.add(heur);

            double[][] x = new double[data.size()][datum.size()];
            for (int ji = 0; ji < data.size(); ji++) {
                for (int el = 0; el < datum.size(); el++) {
                    x[ji][el] = data.get(ji).get(el);
                }
            }
            double[] y = new double[avgscore.size()];
            for (int l = 0; l < avgscore.size(); l++) {
                y[l] = avgscore.get(l);
            }

            if (i < 2 * melite) {
                setPoliciesValues();
            } else {
                SVR < double[] > svr = new SVR < double[] > (x, y, new GaussianKernel(0.005), 3.5, 5.0);
                if (cem) {
                    double[][] elites = getMElites(x, y, melite);
                    double[][] proposals = getSamples(elites, 1000);

                    double[] results = svr.predict(proposals);
                    double max_result = 0;
                    double[] max_setup = proposals[0];
                    //finding the point with the maximum prediction
                    for (int pl = 0; pl < proposals.length; pl++) {
                        if (results[pl] > max_result) {
                            max_result = results[pl];
                            max_setup = proposals[pl];
                        }
                    }

                    if (rand.nextDouble() <= (1 / i)) {
                        setPoliciesValues(); //epsilon-greedy strategy
                    } else {
                        int numero = 0;
                        for (Policy policy: setOfPolicies) {
                            policy.reward = (float)(max_setup[2 * numero]);
                            policy.punishment = (float)(max_setup[2 * numero + 1]);
                            numero++;
                        }
                    }
                } else {
                    //placeholder to generate 10000 data points
                    double[][] proposals = new double[1000][datum.size()];
                    //generating the 30 data points
                    for (int ligne = 0; ligne < proposals.length; ligne++) {
                        for (int colonne = 0; colonne < datum.size(); colonne++) {
                            if (colonne % 2 == 0) {
                                proposals[ligne][colonne] = rand.nextDouble() * 20;
                            } else {
                                proposals[ligne][colonne] = rand.nextDouble() + 0.0001;
                            }
                        }
                    }
                    //using surrogate model to get predictions
                    double[] results = svr.predict(proposals);
                    double max_result = 0;
                    double[] max_setup = proposals[0];
                    //finding the point with the maximum prediction
                    for (int pl = 0; pl < proposals.length; pl++) {
                        if (results[pl] > max_result) {
                            max_result = results[pl];
                            max_setup = proposals[pl];
                        }
                    }
                    //choose to explore the maximum selected or to explore
                    if (rand.nextDouble() <= (1 / i)) {
                        setPoliciesValues(); //epsilon-greedy strategy
                    } else {
                        int numero = 0;
                        for (Policy policy: setOfPolicies) {
                            policy.reward = (float)(max_setup[2 * numero]);
                            policy.punishment = (float)(max_setup[2 * numero + 1]);
                            numero++;
                        }
                    }
                }
            }
        }
        return new Pair < ArrayList < Float > , Float > (max_datum, maxavgscore);
    }

    public static Pair < ArrayList < Float > , Float > optimiseRND(boolean cem, int melite) throws Exception {

        ArrayList < Float > max_datum = new ArrayList < Float > ();
        ArrayList < Float > avgscore = new ArrayList < Float > ();
        ArrayList < Float > scorestdev = new ArrayList < Float > ();
        ArrayList < Float > avgviol = new ArrayList < Float > ();
        ArrayList < Float > avgcompl = new ArrayList < Float > ();
        ArrayList < Float > avgheur = new ArrayList < Float > ();
        Float maxavgscore = 0.0f;
        ArrayList < ArrayList < Float >> data = new ArrayList < ArrayList < Float >> ();

        int totalactions = 3;

        for (int i = 0; i <= 100; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(100);
            for (int runid = 1; runid <= 30; runid++) {
                int numPlayers = rand.nextInt(10) + 1;
                Map < String, Player > playerMap = new HashMap < String, Player > ();
                for (int k = 0; k < numPlayers; k++) {
                    String playerName = "agent" + Integer.toString(k);
                    List < Action > playerActions = getPlayersActions(setOfActions, totalactions);
                    List < Policy > playerPolicies = applicablePolicies(playerActions);
                    playerMap.put(playerName, new Player(playerActions, playerPolicies, playerName, "", 1, 1, runid));
                    playerMap.get(playerName).setName(playerName);
                    //		        	Util.insertMapping(1, 3, runid, playerName, playerActions.size(), playerPolicies.size());
                }
                Map < String, GameEngine > geMap = new HashMap < String, GameEngine > ();
                geMap.put("gameengine", new GameEngine("gameengine", 1 * 100 + 1, 1, 1, runid));

                Game game = new Game(geMap, playerMap);
                executor.submit(game);
            }
            executor.awaitTermination(14, TimeUnit.SECONDS);
            executor.shutdown();

            ArrayList < Float > datum = new ArrayList < Float > ();
            for (Policy policy: setOfPolicies) {
                datum.add(policy.reward);
                datum.add(policy.punishment);
            }

            Map < String, Float > polScore = new HashMap < String, Float > ();
            Map < String, Float > polStdev = new HashMap < String, Float > ();
            Map < String, Float > polCompl = new HashMap < String, Float > ();
            Map < String, Float > polViol = new HashMap < String, Float > ();
            Map < String, Float > polHeur = new HashMap < String, Float > ();
            Map < String, Integer > polNbre = new HashMap < String, Integer > ();

            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            String[] nextLine;
            int counter = 0;
            Float score = 0.0f, stdev = 0.0f, compl = 0.0f, viol = 0.0f, heur = 0.0f;
            while ((nextLine = reader.readNext()) != null) {
                if (Float.parseFloat(nextLine[0]) != 0.0) {
                    counter++;
                    score += Float.parseFloat(nextLine[0]);
                    stdev += Float.parseFloat(nextLine[1]);
                    String[] policies = nextLine[6].replace("[", "").replace("]", "").split(",");
                    String[] policies_viol = nextLine[7].replace("[", "").replace("]", "").split(",");
                    String[] policies_comp = nextLine[8].replace("[", "").replace("]", "").split(",");
                    String[] violations = nextLine[4].replace("[", "").replace("]", "").split(",");
                    String[] compliances = nextLine[5].replace("[", "").replace("]", "").split(",");

                    //	                ArrayList<String> policies = (ArrayList<String>) ((Object)nextLine[6]);
                    //	                ArrayList<Integer> policies_viol = (ArrayList<Integer>) ((Object)nextLine[7]);
                    //	                ArrayList<Integer> policies_comp = (ArrayList<Integer>) ((Object)nextLine[8]);
                    for (int bb = 0; bb < violations.length; bb++) {
                        if (!violations[bb].contentEquals("")) {
                            viol += Float.parseFloat(violations[bb]) / violations.length;
                            compl += Float.parseFloat(compliances[bb]) / violations.length;
                        }
                    }
                    for (int aa = 0; aa < policies.length; aa++) {
                        if (!policies[aa].contentEquals("")) {
                            if (polScore.containsKey(policies[aa])) {
                                polScore.put(policies[aa], polScore.get(policies[aa]) + Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], polStdev.get(policies[aa]) + Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], polNbre.get(policies[aa]) + 1);
                                polViol.put(policies[aa], polViol.get(policies[aa]) + Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], polCompl.get(policies[aa]) + Float.parseFloat(policies_comp[aa]));
                            } else {
                                polScore.put(policies[aa], Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], 1);
                                polViol.put(policies[aa], Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], Float.parseFloat(policies_comp[aa]));
                            }

                            //	            	    System.out.println(policies_viol[aa]);
                        }
                    }
                }
            }
            for (String policy: polScore.keySet()) {
                polScore.put(policy, polScore.get(policy) / polNbre.get(policy));
                polStdev.put(policy, polStdev.get(policy) / polNbre.get(policy));
                polCompl.put(policy, polCompl.get(policy) / polNbre.get(policy));
                polViol.put(policy, polViol.get(policy) / polNbre.get(policy));
                polHeur.put(policy, (1 + polScore.get(policy)) / (1 + polStdev.get(policy)) + (1 + polCompl.get(policy)) / (1 + polViol.get(policy)));
            }

            //    	    Float min_result = 1000f;
            //    	    Float avg_result = 0f;
            //    	    String min_setup = "";
            //    	    //finding the point with the maximum prediction
            //    	    for(String policy:polHeur.keySet()) {
            //    	    	if (polHeur.get(policy) < min_result) {
            //    	    		min_result = polHeur.get(policy);
            //    	    		min_setup = policy;
            //    	    	}
            //    	    	avg_result += polHeur.get(policy);
            //    	    }
            //    	    
            //    	    avg_result /= polHeur.size();

            //            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            //            String[] nextLine;
            //            int counter = 0;
            //            Float score = 0.0f, stdev = 0.0f;
            //            while ((nextLine = reader.readNext()) != null) {
            //            	if(Float.parseFloat(nextLine[0]) != 0.0) {
            //            		counter++;
            //            		score += Float.parseFloat(nextLine[0]);
            //            		stdev += Float.parseFloat(nextLine[1]);
            //            	}
            //            }

            reader.close();
            File onefile = new File("results.csv");
            onefile.delete();

            score /= counter;
            stdev /= counter;
            viol /= counter;
            compl /= counter;
            heur = (1 + score) / (1 + stdev) + (1 + compl) / (1 + viol);
            ArrayList < Float > longdatum = new ArrayList < Float > ();

            for (int index = 0; index < datum.size(); index++) {
                longdatum.add(datum.get(index));
            }

            longdatum.add(score);
            longdatum.add(stdev);
            longdatum.add(viol);
            longdatum.add(compl);
            longdatum.add(heur);
            String[] content = new String[longdatum.size()];

            for (int index = 0; index < longdatum.size(); index++) {
                content[index] = Float.toString(longdatum.get(index));
            }
            String filename;
            if (cem) {
                filename = "history_rnd_cem.csv";
            } else {
                filename = "history_rnd.csv";
            }
            File file = new File(filename);
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(content);
            writer.close();

            if (score > maxavgscore) {
                maxavgscore = score;
                max_datum = datum;
            }
            data.add(datum);
            avgscore.add(score);
            scorestdev.add(stdev);
            avgviol.add(viol);
            avgcompl.add(compl);
            avgheur.add(heur);

            double[][] x = new double[data.size()][datum.size()];
            for (int ji = 0; ji < data.size(); ji++) {
                for (int el = 0; el < datum.size(); el++) {
                    x[ji][el] = data.get(ji).get(el);
                }
            }
            double[] y = new double[avgscore.size()];
            for (int l = 0; l < avgscore.size(); l++) {
                y[l] = avgscore.get(l);
            }

            if (i < 2 * melite) {
                setPoliciesValues();
            } else {
                if (cem) {
                    double[][] elites = getMElites(x, y, melite);
                    double[] proposals = getSamples(elites, 1)[0];

                    if (rand.nextDouble() <= (1 / i)) {
                        setPoliciesValues(); //epsilon-greedy strategy

                    } else {
                        int numero = 0;
                        for (Policy policy: setOfPolicies) {
                            policy.reward = (float)(proposals[2 * numero]);
                            policy.punishment = (float)(proposals[2 * numero + 1]);
                            numero++;
                        }
                    }
                } else {
                    setPoliciesValues();
                }
            }
        }
        return new Pair < ArrayList < Float > , Float > (max_datum, maxavgscore);
    }

    public static double[][] getMElites(double[][] xsample, double[] ysample, int m) {
        double[] ysamplecopy = ysample.clone();
        Arrays.sort(ysamplecopy);
        int longueur;
        if (m > ysample.length) {
            longueur = ysample.length;
        } else {
            longueur = m;
        }

        double[][] resultat = new double[longueur][];

        for (int i = ysamplecopy.length - longueur; i < ysamplecopy.length; i++) {
            for (int j = 0; j < ysample.length; j++) {
                if (ysample[j] == ysamplecopy[i]) {
                    resultat[i - (ysamplecopy.length - longueur)] = xsample[j];
                }
            }
        }
        return resultat;
    }

    public static double[][] getSamples(double[][] xsample, int number) {
        double[][] resultat = new double[number][];

        double[] mean = new double[xsample[0].length];
        double[][] cov = new double[mean.length][mean.length];
        for (int j = 0; j < mean.length; j++) {
            mean[j] = 0;
            for (int i = 0; i < xsample.length; i++) {
                mean[j] += xsample[i][j] / xsample.length;
            }
        }

        for (int j = 0; j < cov.length; j++) {
            for (int i = 0; i < cov.length; i++) {
                double total = 0;
                for (int k = 0; k < xsample.length; k++) {
                    total += (xsample[k][i] - mean[i]) * (xsample[k][j] - mean[j]);
                }
                cov[i][j] = total / xsample.length;
            }
        }

        double[] ncov = new double[cov.length];

        for (int n = 0; n < cov.length; n++) {
            ncov[n] = cov[n][n];
        }

        //MultivariateNormalDistribution mnd = new MultivariateNormalDistribution(mean, new Covariance(xsample).getCovarianceMatrix().getData());
        //return mnd.sample(number);
        //MultivariateGaussianDistribution mgdd = new MultivariateGaussianDistribution(xsample);
        MultivariateGaussianDistribution mgd = new MultivariateGaussianDistribution(mean, ncov);
        for (int i = 0; i < number; i++) {
            double[] prop = mgd.rand();
            for (int p = 0; p < prop.length; p++) {
                if (p % 2 == 0) {
                    if (prop[p] < 0) {
                        prop[p] = 0.0;
                    } else if (prop[p] > 20) {
                        prop[p] = 20.0;
                    }
                } else {
                    if (prop[p] < 0) {
                        prop[p] = 0.0;
                    } else if (prop[p] > 1) {
                        prop[p] = 1;
                    }
                }
            }
            resultat[i] = prop;
        }
        return resultat;
    }

    public static Pair < ArrayList < Float > , Float > optimisePOL(boolean cem, int melite) throws Exception {

        ArrayList < Float > max_datum = new ArrayList < Float > ();
        ArrayList < Float > avgscore = new ArrayList < Float > ();
        ArrayList < Float > scorestdev = new ArrayList < Float > ();
        Float maxavgscore = 0.0f;
        ArrayList < ArrayList < Float >> data = new ArrayList < ArrayList < Float >> ();

        int totalactions = 3;

        for (int i = 0; i <= 100; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(100);
            for (int runid = 1; runid <= 30; runid++) {
                int numPlayers = rand.nextInt(10) + 1;
                Map < String, Player > playerMap = new HashMap < String, Player > ();
                for (int k = 0; k < numPlayers; k++) {
                    String playerName = "agent" + Integer.toString(k);
                    List < Action > playerActions = getPlayersActions(setOfActions, totalactions);
                    List < Policy > playerPolicies = applicablePolicies(playerActions);
                    playerMap.put(playerName, new Player(playerActions, playerPolicies, playerName, "", 1, 1, runid));
                    playerMap.get(playerName).setName(playerName);
                    //		        	Util.insertMapping(1, 3, runid, playerName, playerActions.size(), playerPolicies.size());
                }
                Map < String, GameEngine > geMap = new HashMap < String, GameEngine > ();
                geMap.put("gameengine", new GameEngine("gameengine", 1 * 100 + 1, 1, 1, runid));

                Game game = new Game(geMap, playerMap);
                executor.submit(game);
            }
            executor.awaitTermination(14, TimeUnit.SECONDS);
            executor.shutdown();

            ArrayList < Float > datum = new ArrayList < Float > ();
            for (Policy policy: setOfPolicies) {
                datum.add(policy.reward);
                datum.add(policy.punishment);
            }

            Map < String, Float > polScore = new HashMap < String, Float > ();
            Map < String, Float > polStdev = new HashMap < String, Float > ();
            Map < String, Float > polCompl = new HashMap < String, Float > ();
            Map < String, Float > polViol = new HashMap < String, Float > ();
            Map < String, Float > polHeur = new HashMap < String, Float > ();
            Map < String, Integer > polNbre = new HashMap < String, Integer > ();

            CSVReader reader = new CSVReader(new FileReader("results.csv"));
            String[] nextLine;
            int counter = 0;
            Float score = 0.0f, stdev = 0.0f, compl = 0.0f, viol = 0.0f, heur = 0.0f;
            while ((nextLine = reader.readNext()) != null) {
                if (Float.parseFloat(nextLine[0]) != 0.0) {
                    counter++;
                    score += Float.parseFloat(nextLine[0]);
                    stdev += Float.parseFloat(nextLine[1]);
                    String[] policies = nextLine[6].replace("[", "").replace("]", "").split(",");
                    String[] policies_viol = nextLine[7].replace("[", "").replace("]", "").split(",");
                    String[] policies_comp = nextLine[8].replace("[", "").replace("]", "").split(",");
                    String[] violations = nextLine[4].replace("[", "").replace("]", "").split(",");
                    String[] compliances = nextLine[5].replace("[", "").replace("]", "").split(",");

                    //	              ArrayList<String> policies = (ArrayList<String>) ((Object)nextLine[6]);
                    //	              ArrayList<Integer> policies_viol = (ArrayList<Integer>) ((Object)nextLine[7]);
                    //	              ArrayList<Integer> policies_comp = (ArrayList<Integer>) ((Object)nextLine[8]);
                    for (int bb = 0; bb < violations.length; bb++) {
                        if (!violations[bb].contentEquals("")) {
                            viol += Float.parseFloat(violations[bb]) / violations.length;
                            compl += Float.parseFloat(compliances[bb]) / violations.length;
                        }
                    }

                    for (int aa = 0; aa < policies.length; aa++) {
                        if (!policies[aa].contentEquals("")) {
                            if (polScore.containsKey(policies[aa])) {
                                polScore.put(policies[aa], polScore.get(policies[aa]) + Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], polStdev.get(policies[aa]) + Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], polNbre.get(policies[aa]) + 1);
                                polViol.put(policies[aa], polViol.get(policies[aa]) + Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], polCompl.get(policies[aa]) + Float.parseFloat(policies_comp[aa]));
                            } else {
                                polScore.put(policies[aa], Float.parseFloat(nextLine[0]));
                                polStdev.put(policies[aa], Float.parseFloat(nextLine[1]));
                                polNbre.put(policies[aa], 1);
                                polViol.put(policies[aa], Float.parseFloat(policies_viol[aa]));
                                polCompl.put(policies[aa], Float.parseFloat(policies_comp[aa]));
                            }

                            //	            	  System.out.println(policies_viol[aa]);
                        }
                    }
                }
            }

            for (String policy: polScore.keySet()) {
                polScore.put(policy, polScore.get(policy) / polNbre.get(policy));
                polStdev.put(policy, polStdev.get(policy) / polNbre.get(policy));
                polCompl.put(policy, polCompl.get(policy) / polNbre.get(policy));
                polViol.put(policy, polViol.get(policy) / polNbre.get(policy));
                polHeur.put(policy, (1 + polScore.get(policy)) / (1 + polStdev.get(policy)) + (1 + polCompl.get(policy)) / (1 + polViol.get(policy)));
            }

            Float min_result = 1000f;
            Float avg_result = 0f;
            String min_setup = "";
            //finding the point with the maximum prediction
            for (String policy: polHeur.keySet()) {
                if (polHeur.get(policy) < min_result) {
                    min_result = polHeur.get(policy);
                    min_setup = policy;
                }
                avg_result += polHeur.get(policy);
            }

            avg_result /= polHeur.size();

            for (Policy policy: setOfPolicies) {
                if (policy.actionName.actionName.getName().contentEquals(min_setup)) {
                    Float nwpunishment = (float)(policy.punishment * (1 + Math.exp(-(avg_result - min_result) * (avg_result - min_result))));
                    if (nwpunishment > 1) {
                        policy.punishment = 1.0f;
                    } else {
                        policy.punishment = nwpunishment;
                    }

                    Float nwreward = (float)(policy.reward * (1 + Math.exp(-(avg_result - min_result) * (avg_result - min_result))));
                    if (nwreward > 20) {
                        policy.reward = 20.0f;
                    } else {
                        policy.reward = nwreward;
                    }
                }
            }

            reader.close();
            File onefile = new File("results.csv");
            onefile.delete();

            score /= counter;
            stdev /= counter;
            viol /= counter;
            compl /= counter;
            heur = (1 + score) / (1 + stdev) + (1 + compl) / (1 + viol);

            ArrayList < Float > longdatum = new ArrayList < Float > ();

            for (int index = 0; index < datum.size(); index++) {
                longdatum.add(datum.get(index));
            }

            longdatum.add(score);
            longdatum.add(stdev);
            longdatum.add(viol);
            longdatum.add(compl);
            longdatum.add(heur);

            String[] content = new String[longdatum.size()];

            for (int index = 0; index < longdatum.size(); index++) {
                content[index] = Float.toString(longdatum.get(index));
            }
            String filename;
            if (cem) {
                filename = "history_pol_cem.csv";
            } else {
                filename = "history_pol.csv";
            }
            File file = new File(filename);
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(content);
            writer.close();

            if (score > maxavgscore) {
                maxavgscore = score;
                max_datum = datum;
            }
            data.add(datum);
            avgscore.add(score);
            scorestdev.add(stdev);

            double[][] x = new double[data.size()][datum.size()];
            for (int ji = 0; ji < data.size(); ji++) {
                for (int el = 0; el < datum.size(); el++) {
                    x[ji][el] = data.get(ji).get(el);
                }
            }
            double[] y = new double[avgscore.size()];
            for (int l = 0; l < avgscore.size(); l++) {
                y[l] = avgscore.get(l);
            }

        }
        return new Pair < ArrayList < Float > , Float > (max_datum, maxavgscore);
    }
}