import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Truck> q = new LinkedList<>();
        int sumWeight = 0;
        int passIdx = 0;
        boolean allPassed = false;
        
        while(allPassed == false) {
            answer++;
            List<Truck> tmp = new ArrayList<>();
            while(q.size() > 0) {
                Truck t = q.poll();
                if(t.second < bridge_length-1) {
                    t.second++;
                    tmp.add(t);
                } else {
                    sumWeight -= t.weight;
                }
            }
            q.addAll(tmp);
            
            if(passIdx < truck_weights.length && sumWeight + truck_weights[passIdx] <= weight && q.size() + 1 <=  bridge_length) {
                q.add(new Truck(truck_weights[passIdx], 0));
                sumWeight += truck_weights[passIdx];
                passIdx++;

            }
            if(passIdx == truck_weights.length && q.size() == 0) allPassed = true;
        }
        return answer;
    }
    
    public class Truck {
        int weight;
        int second;
        
        public Truck(int weight, int second) {
            this.weight = weight;
            this.second = second;
        }
    }
}

// queue에 (트럭 무게, 경과 초수)을 넣고
// 다음 트럭 무게와 지금 다리에 있는 트럭 무게 합이 weight를 안 넘고, bridge_length보다 개수가 적으면 추가
// 
