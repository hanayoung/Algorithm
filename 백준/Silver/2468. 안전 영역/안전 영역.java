import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] maps = new int[N][N];
 
        int maxHeight = 0; // 가장 높은 지역의 높이
        int minHeight = 100;
        int answer = 1;

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,1,-1};

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, maps[i][j]);
                minHeight = Math.min(minHeight, maps[i][j]);
            }
        }

        for(int height = minHeight; height < maxHeight; height++) {
            boolean[][] visited = new boolean[N][N];
            int area = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(maps[i][j] - height > 0 && visited[i][j] == false) {
                        Queue<Point> q = new LinkedList<>();
                        q.add(new Point(i,j));
                        visited[i][j] = true;
                        area++;
                        while(q.size() > 0) {
                            Point cur = q.poll();

                            for(int l = 0; l < 4; l++) {
                                int nx = cur.x + dx[l];
                                int ny = cur.y + dy[l];

                                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                                if(visited[nx][ny] == false && maps[nx][ny] - height > 0) {
                                    q.add(new Point(nx, ny));
                                    visited[nx][ny] = true;
                                }
                            }
                        }
                    }
                }
            }
            answer = Math.max(area, answer);
        }
        System.out.println(answer);

        
    }
}