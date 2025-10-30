import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;

class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0));
        visited[0][0] = 1;
        
        while(q.size() > 0) {
            Point cur = q.poll();
            if(cur.x == n - 1 && cur.y == m - 1) {
                answer = visited[cur.x][cur.y];
                break;
            }
            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                
                if(visited[nx][ny] == 0 && maps[nx][ny] == 1) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = visited[cur.x][cur.y] + 1;
                }
            }
        }
        
        return answer;
    }
}