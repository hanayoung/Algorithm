import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int PLAYER_CNT = 3;
        int[] cnt = new int[PLAYER_CNT];
        int[][] PATTERNS = {
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
        };
        
        for(int i = 0; i < answers.length; i++) {
            for(int j = 0; j < PLAYER_CNT; j++) {
                if(answers[i] == PATTERNS[j][i%PATTERNS[j].length]) cnt[j]++;
            }
        }
        int score = 0;
        for(int i = 0; i < PLAYER_CNT; i++) {
           if(score < cnt[i]) {
               score = cnt[i];
               answer.clear();
               answer.add(i+1);
           } else if(score == cnt[i]) answer.add(i+1);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}