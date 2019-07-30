package mytweetyapp;

import java.util.Map;

public class Game extends Thread{
	Map<String, GameEngine> geMap;
	Map<String, Player> playerMap;
	Float averageScore = 0.0f;
	
	public Game(Map<String, GameEngine> geMap, Map<String, Player> playerMap) {
		this.geMap = geMap;
		this.playerMap = playerMap;
	}
	
	public synchronized void run() {
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
