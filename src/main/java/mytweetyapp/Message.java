package mytweetyapp;

import java.io.*;
import com.google.gson.Gson;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int msgNo = 0;
	public String from;
	public String to;
	public String header;
	public String content;
	public int ticks;
	private static Gson gson = new Gson();
	
	public String toJSON() {
		return gson.toJson(this);
	}
	
	public static Message fromJSON(String msg) {
		Gson gson = new Gson();
		return (Message) gson.fromJson(msg, Message.class);
	}
	
	public String printMessage() {
		return String.format("Message No: %d From: %s To: %s " +
		"Header: %s Content: %s Ticks: %o",
		msgNo, from, to, header, content, ticks);
	}

}