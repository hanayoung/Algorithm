import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Map<Integer, Integer> cntMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int cit: citations) {
            Integer value = cntMap.get(cit);
            if(value == null) {
                list.add(cit);
                cntMap.put(cit, 1);
            } else cntMap.put(cit, value+1);
        }
        
        Collections.sort(list);
        int min = list.get(0);
        int max = list.get(list.size() -1);
        int left = 0, right = list.get(list.size() - 1);
        while(left < right) {
            int mid = (left+right)/2;
            int cntR = 0;
            int cntL = 0;
            for(int i = mid; i <= max; i++) {
                cntR += cntMap.getOrDefault(i, 0);
            }
            for(int i = min; i < mid; i++) {
                cntL += cntMap.getOrDefault(i, 0);
            }
    
            if(cntR >= mid && cntL <= mid) {
                answer = Math.max(answer, mid);
                left++;
            } else {
                right--;
            }
        }
        // citations를 정렬하고
        // 0 1 1 3 4 5 6 6 7 9 
        // 뭐 이런 식으로 되면 n/2번째 숫자랑 
        return answer;
    }
}