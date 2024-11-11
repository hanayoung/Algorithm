import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static Point start;
    static int[][] maps;
    static int answer;
    static int[] dx = {-1, -1, 1, 1}; // 상좌, 상우, 하우, 하좌
    static int[] dy = {-1, 1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());

            maps = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;

            for (int i = 0; i < N-2; i++) {
                for (int j = 1; j < N-1; j++) {
                    HashSet<Integer> hashSet = new HashSet<>();
                    start = new Point(i, j);
                    boolean[][][][] visited = new boolean[N][N][4][5];
                    visited[i][j][0][0] = true;
       
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                        if(maps[nx][ny] != maps[i][j]) {
                            hashSet.add(maps[nx][ny]); // 넣고
                            visited[nx][ny][k][0] = true;
                            dfs(nx, ny, k, 0, hashSet, visited);
                            hashSet.remove(maps[nx][ny]); // 빼고
                            visited[nx][ny][k][0] = false;
                        }
                    }
                }
            }
            if(answer == 0) answer = -1;
            System.out.println("#"+tc+" "+answer);
        }
    }

    static void dfs(int x, int y, int dir, int cnt, HashSet<Integer> hashSet, boolean [][][][] visited) {
        if (x == start.x && y == start.y) {
            answer = Math.max(answer,hashSet.size());
            return;
        }

        if(cnt == 3) {
            // dir과 같은 방향으로만 이동가능
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if(hashSet.contains(maps[nx][ny]) == false && visited[nx][ny][dir][cnt] == false) { // 이미 먹은 디저트가 아니어야 함
                    hashSet.add(maps[nx][ny]);
                    visited[nx][ny][dir][cnt] = true;
                    dfs(nx, ny, dir, cnt, hashSet,visited);
                    hashSet.remove(maps[nx][ny]); // 여기로 올 일이 있을까? 싶긴한데 일단 제거
                    visited[nx][ny][dir][cnt] = false;
                }
            }
        } else if(cnt < 3){
            for (int k = 0; k < 4; k++) {
                if(k != (dir+2)%4){ // 반대 방향은 아니어야 함 다시 돌아가는 건 안되니까 .. -> 근데 이건 밑에 hashset에서 걸러질 거 같긴 함
                    int nx = x + dx[k];
                    int ny = y + dy[k];
    
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
    
                    if(hashSet.contains(maps[nx][ny]) == false) {  // 이미 먹은 디저트가 아니어야 함
                        hashSet.add(maps[nx][ny]); // 넣고
                        visited[nx][ny][dir][cnt] = true;
                        if(k == dir) dfs(nx, ny, k, cnt, hashSet, visited);
                        else dfs(nx, ny, k, cnt+1, hashSet, visited);
                        hashSet.remove(maps[nx][ny]); // 빼고
                        visited[nx][ny][dir][cnt] = false;
                    }
                }
            }
        }
    }
}