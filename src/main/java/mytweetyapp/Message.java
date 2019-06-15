package mytweetyapp;

/**
* Taken from the book Mastering RabbitMQ
*/

import mytweetyapp.JSONMessage;
import com.google.gson.Gson;
/**
* @author Emrah Ayanoglu
* Following code represents the simple message model
*
*/
public class Message implements JSONMessage {
	
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
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public void setHeader(String header) {
		this.header= header;
	}
	
	public void setContent(String content) {
		this.content= content;
	}
	
	public void setTicks(int ticks) {
		this.ticks= ticks;
	}
	
	public String toString() {
		return String.format("Message No: %d From: %s To: %s " +
		"Header: %s Content: %s Ticks: %o",
		msgNo, from, to, header, content.toString(), ticks);
	}
}


