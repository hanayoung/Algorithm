import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int[] cnt = new int[3];
        int[] player1 = {1,2,3,4,5};
        int[] player2 = {2,1,2,3,2,4,2,5};
        int[] player3 = {3,3,1,1,2,2,4,4,5,5};
        
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == player1[i%player1.length]) cnt[0]++;
            if(answers[i] == player2[i%player2.length]) cnt[1]++;
            if(answers[i] == player3[i%player3.length]) cnt[2]++;
        }
        int score = 0;

        for(int i = 0; i < cnt.length; i++) {
           if(score < cnt[i]) {
               score = cnt[i];
               answer.clear();
               answer.add(i+1);
           } else if(score == cnt[i]) answer.add(i+1);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}