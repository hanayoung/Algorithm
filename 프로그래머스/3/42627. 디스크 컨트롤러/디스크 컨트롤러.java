import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<Process> pQueue = new PriorityQueue<>();
        int time = 0;
        Process[] pJobs = new Process[jobs.length];
        for(int i = 0; i < jobs.length; i++) {
            pJobs[i] = new Process(i, jobs[i][0], jobs[i][1]);
        }
        Arrays.sort(pJobs, new Comparator<Process>(){
            @Override
            public int compare(Process o1, Process o2) {
                if(o1.dt <= o2.dt) return -1;
                else return 1;
            }
        }); // 요청 시점이 빠른 것부터 정렬
        
        int idx = 0;
        while(idx < pJobs.length) {
            int next = 0;
            while(idx+next < pJobs.length && time >= pJobs[idx+next].dt) { // 현재 time까지 요청된 작업들 담기
                pQueue.add(new Process(pJobs[idx+next].id, pJobs[idx+next].dt, pJobs[idx+next].duration));
                next++;
            }
            if(next > 0) idx += next -1; // 큐에 넣은 값들 인덱스 이동
            if(pQueue.size() == 0) time++; // 아무것도 없으면
            else {
                while(pQueue.size() > 0) {
                    Process cur = pQueue.poll();
                    time += cur.duration; // 작업 하나 끝냄
                    answer += time - cur.dt;
                    if(idx+1 < jobs.length && pJobs[idx+1].dt <= time) break; // 들어올 수 있는 다음 작업이 있으면 탈출
                }
                idx++;
            }

        }
        
        return answer/jobs.length;
    }
    
    public class Process implements Comparable<Process>{
        int id;
        int dt;
        int duration;
        
        public Process(int id, int dt, int duration) {
            this.id = id;
            this.dt = dt;
            this.duration = duration;
        }
        
        @Override
        public int compareTo(Process o) {
            if(this.duration < o.duration) return -1;
            else if(this.duration == o.duration) {
                if(this.dt < o.dt) return -1;
                else if(this.dt == o.dt) {
                    if(this.id < o.id) return -1;
                }
            }
            return 1;
        }
    }
}