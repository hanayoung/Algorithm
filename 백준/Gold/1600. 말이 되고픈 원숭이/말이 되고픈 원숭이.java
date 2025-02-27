import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static int K;
    static int[][] maps;
    static int[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[] hx = {-2,2,-2,2,-1,1,-1,1};
    static int[] hy = {-1,1,1,-1,-2,2,2,-2};

    public static void main(String[] args) throws Exception {
    
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        K = Integer.parseInt(st.nextToken()); // 말처럼 이동횟수, 0일 수도 있음

        st = new StringTokenizer(bf.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        

        maps = new int[N][M];
        visited = new int[N][M][K+1];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = bfs();

        // System.out.println("visited "+Arrays.deepToString(visited));
        System.out.println(answer);
        
    }

    static int bfs(){
        int answer = -1;
        Queue<int []> queue = new LinkedList<>();
        int[] start = {0,0,0}; // 0번째 x, 1번째 y, 2번째 말 능력 
        queue.add(start);
        visited[0][0][0] = 0;
        
        while(queue.size() != 0){
            int[] p = queue.poll();
            int capacity = p[2];
            if(p[0] == N - 1 && p[1] == M - 1){
                queue.clear();
                answer = visited[N-1][M-1][p[2]];
                return answer;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = p[0] + dx[i];
                int nextY = p[1] + dy[i];
                if(!checkRange(nextX, nextY)) continue;
                
                if(visited[nextX][nextY][capacity] == 0 && maps[nextX][nextY] == 0){
                    int[] nextNode = {nextX, nextY, capacity};
                    queue.add(nextNode);
                    visited[nextX][nextY][capacity] = visited[p[0]][p[1]][capacity] + 1;
                    // System.out.println("p[2]<K && 추가 x  "+Arrays.toString(nextNode)+" 이동 수 "+visited[nextX][nextY][capacity]+" 직전 "+p[0]+" "+p[1]);
                }
            }
            if(p[2] < K){
                for (int i = 0; i < 8; i++) {
                    int nextX = p[0] + hx[i];
                    int nextY = p[1] + hy[i];
                    if(!checkRange(nextX, nextY)) continue;
                    
                    if(visited[nextX][nextY][capacity+1] == 0 && maps[nextX][nextY] == 0){
                        int[] nextNode = {nextX, nextY, capacity+1};
                        queue.add(nextNode);
                        
                        visited[nextX][nextY][capacity+1] = visited[p[0]][p[1]][capacity] + 1;
                        // System.out.println("p[2]<K  "+Arrays.toString(nextNode)+" 이동 수 "+visited[nextX][nextY][capacity+1]+" 직전 "+p[0]+" "+p[1]);
                    }
                }
            }
        }
        return answer;
    }

    static boolean checkRange(int nextX, int nextY){
        if(nextX > -1 && nextX < N && nextY > -1 && nextY < M) return true;
        else return false;
    }
}
