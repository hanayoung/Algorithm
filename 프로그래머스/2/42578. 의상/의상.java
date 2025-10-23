import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> clothesMap = new HashMap<>();
        for(String[] cloth: clothes) {
            clothesMap.put(cloth[1], clothesMap.getOrDefault(cloth[1], 0)+1);
        }
        for(Integer size: clothesMap.values()) {
            answer *= (size + 1);
        }
        return answer-1;
    }
}