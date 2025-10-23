import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Map<Integer, Integer> monMap = new HashMap<>();
        for(int num: nums) {
            monMap.put(num, monMap.getOrDefault(num, 0) +1);
        }
        answer = monMap.size() < nums.length/2 ? monMap.size() : nums.length/2;
        return answer;
    }
}