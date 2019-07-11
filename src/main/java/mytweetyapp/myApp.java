package mytweetyapp;

import java.util.HashMap;
import java.util.Map;

public class myApp {
	public static void eat(String[] args) {
		Map<String, Receiver> receiverMap = new HashMap<String, Receiver>();
        
        for (int k=0; k<1; k++){
        	String name = "receiver_" + k;
        	receiverMap.put(name, new Receiver());
        	receiverMap.get(name).setName(name);
        	receiverMap.get(name).start();
        }
//        Receiver receiver = new Receiver();
//        receiver.start();
        
        Sender sender = new Sender(receiverMap);
        sender.setName("sender");
        sender.start();
        
	}
}
