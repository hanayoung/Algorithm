import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();
        for(int[] command: commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];
            
            int[] tmp = Arrays.copyOfRange(array, i-1,j);
            
            Arrays.sort(tmp);
            answer.add(tmp[k-1]);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}