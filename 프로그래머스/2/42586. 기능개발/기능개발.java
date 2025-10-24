import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        Queue<Function> q = new LinkedList<>();
        
        boolean isDone = false;
        // progress를 speed과 함께 세트로 queue에 넣고
// speed 더한값을 리스트에 저장했다가 통으로 queue에 다시 삽입?
// queue에서 뽑을 때 100 이상이면 배포
        for(int i = 0; i < progresses.length; i++){
            q.add(new Function(progresses[i]+speeds[i], speeds[i]));
        }
        while(isDone == false) {
            List<Function> todays = new ArrayList<>();
            int release = 0;
            boolean canRelease = true;
            while(q.size() > 0) {
                Function f = q.poll();
                if(f.progress >= 100 && canRelease) {
                    release++;
                } else {
                    f.progress += f.speed;
                    todays.add(f);
                    canRelease = false;
                }
            }
            q.addAll(todays);
            if(release > 0) answer.add(release);
            isDone = q.size() == 0 ? true : false;
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
    public class Function {
        int progress;
        int speed;
        
        public Function(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }
    }
}