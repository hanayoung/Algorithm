import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] maps = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            String str = st.nextToken();
            for (int j = 0; j < str.length(); j++) {
                maps[i][j] = str.charAt(j);
            }
        }

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        int max = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(maps[i][j] == 'L'){
                    // 탐색 시작
                    int[][] visited = new int[N][M];
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i,j));
                    visited[i][j] = 1;
                    while(queue.size() != 0){
                        Point p = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = p.x + dx[k];
                            int ny = p.y + dy[k];

                            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                            if(maps[nx][ny] == 'L' && visited[nx][ny] == 0){
                                visited[nx][ny] = visited[p.x][p.y]+1;
                                queue.add(new Point(nx,ny));
                                if(max < visited[nx][ny]) max = visited[nx][ny];
                            }
                        }
                    }
                }
            }
        }

        System.out.println(max-1);
    }
}