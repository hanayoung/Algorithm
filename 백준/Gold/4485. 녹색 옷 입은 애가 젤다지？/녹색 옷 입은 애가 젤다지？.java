import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int tc = 0;
        while (true) { 
         // 0이 나오면 종료 
         tc += 1;
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int N = Integer.parseInt(st.nextToken());

            if(N == 0) break; // 끝
            else{
                int[][] maps = new int[N][N];
                for (int i = 0; i < N; i++) {
                    st = new StringTokenizer(bf.readLine());
                    for (int j = 0; j < N; j++) {
                        maps[i][j] = Integer.parseInt(st.nextToken());
                    }
                }
                PriorityQueue<Rupee> pQueue = new PriorityQueue<>();
                pQueue.add(new Rupee(0, 0, maps[0][0]));
                boolean[][] visited = new boolean[N][N];
                int[] dx = {-1,1,0,0};
                int[] dy = {0,0,-1,1};

                while(pQueue.size() != 0){
                    Rupee r = pQueue.poll();
                    if(r.x == N-1 && r.y == N-1){
                        System.out.println("Problem "+tc+": "+r.cnt);
                        pQueue.clear();
                        break;
                    }
                    for (int k = 0; k < 4; k++) {
                        int nx = r.x+dx[k];
                        int ny = r.y+dy[k];
                        if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                        if(visited[nx][ny] == false){
                            visited[nx][ny] = true;
                            pQueue.add(new Rupee(nx, ny, r.cnt+maps[nx][ny]));
                        }
                    }
                }
            }
        }
    }
    static class Rupee implements Comparable<Rupee>{
        int x;
        int y;
        int cnt;

        public Rupee(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Rupee o) {
            if(this.cnt < o.cnt) return -1;
            else return 1;
        }
    }
}

// bfs로 탐색?
// PriorityQueue를 이용해서 다음 이동할 곳 결정하기
// 코인 수가 적은 곳부터 이동하기
