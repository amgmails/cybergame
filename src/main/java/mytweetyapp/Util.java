package mytweetyapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.opencsv.*;
import java.io.*;

public class Util {
	
	/*
	 * inspired from http://www.sqlitetutorial.net/sqlite-java/insert/
	 */
	
	public static Connection connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:/home/maixent/Documents/Msc Thesis/cybergame/src/main/java/mytweetyapp/game.db";
        String url = "jdbc:sqlite:./src/main/java/mytweetyapp/game.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            //System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
	public static void insertSetup(int setupid, String policy, float reward, float punishment) {
        String sql = "INSERT INTO setup(setupid, policy, reward, punishment) VALUES(?,?,?,?)";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	//System.out.println("starting setting values before insertion into SETUP TABLE");
            pstmt.setInt(1, setupid);
            pstmt.setString(2, policy);
            pstmt.setFloat(3, reward);
            pstmt.setFloat(4, punishment);
            //System.out.println("preparing to insert into SETUP TABLE");
            pstmt.executeUpdate();
            //System.out.println("values inserted successfully into SETUP TABLE");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/*
	 * inspired by https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
	 */
	
	public static void writeSetup(String filePath, int setupid, String policy, float reward, float punishment) { 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding data to csv 
	        String[] data = { Integer.toString(setupid), policy, Float.toString(reward), Float.toString(reward) };
	        writer.writeNext(data); 
  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	}
    
    public static void insertMapping(int setupid, int sessionid, int runid, String player, int numactions, int numpolicies) {
        String sql = "INSERT INTO playermapping(setupid, sessionid, runid, player, numactions, numpolicies) VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	//System.out.println("starting setting values before insertion into MAPPING TABLE");
            pstmt.setInt(1, setupid);
            pstmt.setInt(2, sessionid);
            pstmt.setInt(3, runid);
            pstmt.setString(4, player);
            pstmt.setFloat(5, numactions);
            pstmt.setFloat(6, numpolicies);
            //System.out.println("preparing to insert into MAPPING TABLE");
            pstmt.executeUpdate();
            //System.out.println("values inserted successfully into MAPPING TABLE");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
	public static void writeMapping(String filePath, int setupid, int sessionid, int runid, String player, int numactions, int numpolicies) { 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding data to csv 
	        String[] data = { Integer.toString(setupid), Integer.toString(sessionid), Integer.toString(runid), player, Integer.toString(numactions), Integer.toString(numpolicies) };
	        writer.writeNext(data); 
  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	}
    
    public static void insertSession(int setupid, int sessionid, int runid, int stepid, String player, float score) {
        String sql = "INSERT INTO session(setupid, sessionid, runid, stepid, player, score) VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	//System.out.println("starting setting values before insertion into SESSION TABLE");
            pstmt.setInt(1, setupid);
            pstmt.setInt(2, sessionid);
            pstmt.setInt(3, runid);
            pstmt.setInt(4, stepid);
            pstmt.setString(5, player);
            pstmt.setFloat(5, score);
            //System.out.println("preparing to insert into SESSION TABLE");
            pstmt.executeUpdate();
            //System.out.println("values inserted successfully into SESSION TABLE");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
	public static void writeSession(String filePath, int setupid, int sessionid, int runid, int stepid, String player, float score) { 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding data to csv 
	        String[] data = { Integer.toString(setupid), Integer.toString(sessionid), Integer.toString(runid), Integer.toString(stepid), player, Float.toString(score) };
	        writer.writeNext(data); 
  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	}
	
	public static void writeDataAtOnce(String filePath, List<String[]> data) 
	{ 

		// first create file object for file placed at location 
		// specified by filepath 
		File file = new File(filePath); 

		try { 
			// create FileWriter object with file as parameter 
			FileWriter outputfile = new FileWriter(file); 

			// create CSVWriter object filewriter object as parameter 
			CSVWriter writer = new CSVWriter(outputfile); 

			// create a List which contains String array 
			writer.writeAll(data); 

			// closing writer connection 
			writer.close(); 
		} 
		catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
	} 

}
