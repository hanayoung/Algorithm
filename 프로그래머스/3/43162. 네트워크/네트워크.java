import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j && computers[i][j] == 1) {
                    map.computeIfAbsent(i, k -> new ArrayList<Integer>()).add(j);
                    map.computeIfAbsent(j, k -> new ArrayList<Integer>()).add(i);
                }
            }
        }
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i] == false) {
                answer++;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while(q.size() > 0) {
                    Integer cur = q.poll();
                    List<Integer> list = map.getOrDefault(cur, Collections.emptyList());
                    for(int next: list) {
                        if(visited[next] == false) {
                            q.add(next);
                            visited[next] = true;
                        }
                    }
                }
            }
        }
        return answer;
    }
}