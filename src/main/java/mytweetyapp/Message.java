package mytweetyapp;

public class Message {
	public int msgNo = 0;
	public String from;
	public String to;
	public String header;
	public Object content;
	public int ticks;
	
	public String toString() {
		return String.format("Message No: %d From: %s To: %s " +
		"Header: %s Content: %s Ticks: %o",
		msgNo, from, to, header, content.toString(), ticks);
	}

}