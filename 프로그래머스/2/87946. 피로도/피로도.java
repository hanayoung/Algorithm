class Solution {
    static int answer;
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        int[] orders = new int[dungeons.length];
        boolean[] visited = new boolean[dungeons.length];
        makeOrder(dungeons, 0, orders, visited, k);
        return answer;
    }
    
    public void makeOrder(int[][] dungeons, int r, int[] orders, boolean[] visited, int k) {
        if(answer == dungeons.length) return;
        if(r == dungeons.length) {
            for(int i = 0; i < orders.length; i++) {
                if(k < dungeons[orders[i]][0]) {
                    answer = Math.max(i, answer);
                    return;
                }
                k -= dungeons[orders[i]][1];
            }
            answer = dungeons.length;
            return;
        }
        for(int i = 0; i < dungeons.length; i++) {
            if(visited[i] == true) continue;
            visited[i] = true;
            orders[r] = i;
            makeOrder(dungeons, r+1, orders, visited, k);
            visited[i] = false;
        }
    }
}