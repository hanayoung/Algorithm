import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0; i < enemy.length; i++) {
            if(n >= enemy[i]) {
                n -= enemy[i];
                pQueue.add(enemy[i]);
            } else {
                if(k > 0) { // 무적권이 남아있으면
                    k--;
                    pQueue.add(enemy[i]);
                    n += pQueue.poll();
                    n -= enemy[i];
                } else {
                    answer = i;
                    break;
                }
            }
        }
        return answer;
    }
}

// 우선적으로 싸우다가 병사 수가 적보다 작아지면
// pQueue에 넣은 적의 수 중 제일 큰 걸 꺼내서 병사 수 복구하고, k--

