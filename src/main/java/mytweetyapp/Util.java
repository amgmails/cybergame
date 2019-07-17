package mytweetyapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.opencsv.*;
import java.io.*;

public class Util {
	
	public static Connection connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:/home/maixent/Documents/Msc Thesis/cybergame/src/main/java/mytweetyapp/game.db";
        String url = "jdbc:sqlite:./src/main/java/mytweetyapp/game.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
	public static void insertSetup(int setupid, String policy, float reward, float punishment) {
        String sql = "INSERT INTO setup(setupid, policy, reward, punishment) VALUES(?,?,?,?)";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	System.out.println("starting setting values before insertion into SETUP TABLE");
            pstmt.setInt(1, setupid);
            pstmt.setString(2, policy);
            pstmt.setFloat(3, reward);
            pstmt.setFloat(4, punishment);
            System.out.println("preparing to insert into SETUP TABLE");
            pstmt.executeUpdate();
            System.out.println("values inserted successfully into SETUP TABLE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/*
	 * inspired by https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
	 */
	
	public static void writeSetup(String filePath) 
	{ 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath); 
	    try { 
	        // create FileWriter object with file as parameter 
	        FileWriter outputfile = new FileWriter(file); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // adding header to csv 
	        String[] header = { "Name", "Class", "Marks" }; 
	        writer.writeNext(header); 
	  
	        // add data to csv 
	        String[] data1 = { "Aman", "10", "620" }; 
	        writer.writeNext(data1); 
	        String[] data2 = { "Suraj", "10", "630" }; 
	        writer.writeNext(data2); 
	  
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
	} 
    
    public static void insertMapping(int setupid, int sessionid, String player, int numactions, int numpolicies) {
        String sql = "INSERT INTO playermapping(setupid, sessionid, player, numactions, numpolicies) VALUES(?,?,?,?,?)";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	System.out.println("starting setting values before insertion into MAPPING TABLE");
            pstmt.setInt(1, setupid);
            pstmt.setInt(2, sessionid);
            pstmt.setString(3, player);
            pstmt.setFloat(4, numactions);
            pstmt.setFloat(5, numpolicies);
            System.out.println("preparing to insert into MAPPING TABLE");
            pstmt.executeUpdate();
            System.out.println("values inserted successfully into MAPPING TABLE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insertSession(int setupid, int sessionid, int runid, String player, float score) {
        String sql = "INSERT INTO session(setupid, sessionid, runid, player, score) VALUES(?,?,?,?,?)";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	System.out.println("starting setting values before insertion into SESSION TABLE");
            pstmt.setInt(1, setupid);
            pstmt.setInt(2, sessionid);
            pstmt.setInt(3, runid);
            pstmt.setString(4, player);
            pstmt.setFloat(5, score);
            System.out.println("preparing to insert into SESSION TABLE");
            pstmt.executeUpdate();
            System.out.println("values inserted successfully into SESSION TABLE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
