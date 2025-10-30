import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Solution {
    static Map<Integer, Boolean> map;
    public int solution(String numbers) {
        int answer = 0;
        List<Character> list = new ArrayList<>();
        map = new HashMap<>();
        
        for(int i = 0; i < numbers.length(); i++) {
            list.add(numbers.charAt(i));
        }
        
        for(int i = 1; i <= numbers.length(); i++) {
            boolean[] visited = new boolean[list.size()];
            makeNum(list, 0, "", visited, i);
        }
        for(Boolean value: map.values()) {
            if(value == true) answer++;
        }
        return answer;
    }
    
    public void makeNum(List<Character> list, int r, String str, boolean[] visited, int targetLen) {
        if(r == targetLen) {
            // 다 만들었음
            int num = Integer.valueOf(str);
            if(num <= 1) return;
            if(map.get(num) == null) {
                map.put(num, isPrime(num));
            }
            return;
        }
        for(int i = 0; i < list.size(); i++) {
            if(visited[i] == true) continue;
            visited[i] = true;
            makeNum(list, r+1, str+list.get(i),visited, targetLen);
            visited[i] = false;
        }
    }
    
    public boolean isPrime(int num) {
        for(int j = 2; j < num; j++) {
            if(num % j == 0) {
                return false;
            }
        }
        return true;
    }
}