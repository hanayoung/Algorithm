import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> q = new LinkedList<>();
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < priorities.length; i++) {
            q.add(new Process(i, priorities[i]));
            pQueue.add(priorities[i]);
        }
        int order = 1;
        Queue<Process> tmp = new LinkedList<>();
        while(answer == 0) {
            Process p = q.poll();
            int most = pQueue.peek();
            if(most > p.priority) {
                // 계속 꺼내야함
                tmp.add(p);
            } else {
                pQueue.poll();
                if(tmp.size() > 0) {
                    q.addAll(tmp);
                    tmp.clear();
                }
                if(location == p.id) answer = order;
                order++;
            }
        }
        return answer;
    }
    public class Process{
        int id;
        int priority;
        
        public Process(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }
}