import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> playerMap = new HashMap<>();
        for(String player: participant) {
            playerMap.put(player, playerMap.getOrDefault(player, 0) +1);
        }

        for(String player: completion) {
            if(playerMap.get(player) - 1 == 0) {
                playerMap.remove(player);
            } else playerMap.put(player, playerMap.get(player) - 1);
        }

        for(String name: playerMap.keySet()) {
            answer = name;
        }
        return answer;
    }
}