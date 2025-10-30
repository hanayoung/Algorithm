import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        Map<Integer, List<Integer>> tree = new HashMap<>();
        int[] connected = new int[n];
        for(int[] wire: wires) {
            tree.computeIfAbsent(wire[0], k -> new ArrayList<Integer>()).add(wire[1]);
            tree.computeIfAbsent(wire[1], k -> new ArrayList<Integer>()).add(wire[0]);
        }
        for(int[] wire: wires) {
            // wire을 끊기 [1,3]을 끊어버리면?
            Map<Integer, Boolean> set1 = new HashMap<>();
            Map<Integer, Boolean> set2 = new HashMap<>();    
            set1.put(wire[0], true);
            set2.put(wire[1], true);
            
            Queue<Integer> q = new LinkedList<>();
            q.add(wire[0]);
            while(q.size() > 0) {
                int node = q.poll();
                if(node != wire[1]) {
                    for(int next: tree.get(node)) {
                        if(set1.get(next) == null) {
                            set1.put(next, true);
                            q.add(next);
                        }
                    }
                }
            }
            
            q.add(wire[1]);
            while(q.size() > 0) {
                int node = q.poll();
                if(node != wire[0]) {
                    for(int next: tree.get(node)) {
                        if(set2.get(next) == null) {
                            set2.put(next, true);
                            q.add(next);
                        }
                    }
                }
            }
            
            answer = Math.min(answer, Math.abs(set2.size() - set1.size()));
            if(answer == 0) System.out.println(wire[0]+" "+wire[1]+" "+set1.size()+" "+set2.size());
        }
//         for(Map.Entry<Integer, List<Integer> entry: tree.entrySet()) {
//             int cnt = 0;
//             int key = entry.getKey();
//             List<Integer> values = entry.getValue();
//             if(values != null) {
//                 Queue<Integer> q = new LinkedList<>();
//                 q.addAll(values);
//                 cnt = values.size();
//                 while(q.size() > 0) {
//                     int node = q.poll();
                    
//                 }
//             }

//         }
        
        // 노드 하나씩 선택해서 연결된 개수 파악
        // 각 노드에 연결된 것들도 넣되, 어딘가에다가 연결된 노드 개수 저장해둬야 계속 세는 걸 방지
        
        return answer;
    }
}