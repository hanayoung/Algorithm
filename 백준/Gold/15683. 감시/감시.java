import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
    static ArrayList<CCTV> cctvList;
    static int[] selDir;
    static boolean[][] visited;
    static int N;
    static int M;
    static int[][] maps;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer;

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];

        visited = new boolean[N][M];
        cctvList = new ArrayList<>();
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if(maps[i][j] < 6 && maps[i][j] > 0){
                    visited[i][j] = true;
                    cctvList.add(new CCTV(i, j, maps[i][j]));
                }else if(maps[i][j] == 6) visited[i][j] = true;
            }
        }

        selDir = new int[cctvList.size()];
        comb(0);

        System.out.println(N*M - answer);
    }

    static void comb(int r) {
        if(r == cctvList.size()){
            watch();
            return;
        }
        for (int i = 0; i < 4; i++) { // 상, 하, 좌, 우
            selDir[r] = i;
            comb(r+1);
        }
    }

    static void watch() {
        boolean[][] tmp = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tmp[i] = visited[i].clone();
        } // visited deep 카피

        for (int i = 0; i < cctvList.size(); i++) {
            if(cctvList.get(i).type == 1) {
                int nx = cctvList.get(i).x;
                int ny = cctvList.get(i).y;
                while(true) {
                    nx += dx[selDir[i]];
                    ny += dy[selDir[i]];
                    if(ny >= M || nx >= N || nx < 0 || ny < 0) {
                        break;
                    }else if(maps[nx][ny] == 6) break;
                    else{
                        tmp[nx][ny] = true;
                    }
                }
            } else if(cctvList.get(i).type == 2) {
                int nx = cctvList.get(i).x;
                int ny = cctvList.get(i).y;
                if(selDir[i] >= 0 && selDir[i] < 2) { // 상, 하
                    while(true) {
                        nx += 1;
                        if(nx >= N) break;
                        else if(maps[nx][ny] == 6) break;
                        tmp[nx][ny] = true;
                    }
                    nx = cctvList.get(i).x;
                    while (true) { 
                        nx -= 1;
                        if(nx < 0) break;
                        else if(maps[nx][ny] == 6) break;
                        tmp[nx][ny] = true;
                    }
                }else{ // 좌, 우
                    while(true) {
                        ny += 1;
                        if(ny >= M) break;
                        else if(maps[nx][ny] == 6) break;
                        tmp[nx][ny] = true;
                    }
                    ny = cctvList.get(i).y;
                    while (true) { 
                        ny -= 1;
                        if(ny < 0) break;
                        else if(maps[nx][ny] == 6) break;
                        tmp[nx][ny] = true;
                    }
                }
            } else if(cctvList.get(i).type == 3) {
                // 상우 , 하우, 하좌, 상좌
                int[][] hx = {{-1,0}, {1,0}, {1,0}, {-1,0}};
                int[][] hy = {{0,1}, {0,1}, {0,-1}, {0,-1}};

                for (int j = 0; j < hx[0].length; j++) {
                    int nx = cctvList.get(i).x;
                    int ny = cctvList.get(i).y;
    
                    while (true) { 
                        nx += hx[selDir[i]][j];
                        ny += hy[selDir[i]][j];
    
                        if(nx >= N || nx < 0 || ny < 0 || ny >= M) break;
                        else if(maps[nx][ny] == 6) break;
                        tmp[nx][ny] = true;
                    }
                }
            } else if(cctvList.get(i).type == 4) {
                // 좌상우, 상우하, 우하좌, 하좌상
                int[][] hx = {{0,-1,0}, {-1,0,1}, {0,1,0}, {1,0,-1}};
                int[][] hy = {{-1,0,1}, {0,1,0}, {1,0,-1}, {0,-1,0}};

                for(int j = 0; j < hx[0].length; j++){
                    int nx = cctvList.get(i).x;
                    int ny = cctvList.get(i).y;
    
                    while (true) { 
                        nx += hx[selDir[i]][j];
                        ny += hy[selDir[i]][j];
    
                        if(nx >= N || nx < 0 || ny < 0 || ny >= M) break;
                        else if(maps[nx][ny] == 6) break;
                        tmp[nx][ny] = true;
                    }
                }
            } else if(cctvList.get(i).type == 5) {
                for(int j = 0; j < 4; j++){
                    int nx = cctvList.get(i).x;
                    int ny = cctvList.get(i).y;
    
                    while (true) { 
                        nx += dx[j];
                        ny += dy[j];
    
                        if(nx >= N || nx < 0 || ny < 0 || ny >= M) break;
                        else if(maps[nx][ny] == 6) break;
                        tmp[nx][ny] = true;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(tmp[i][j] == true) max += 1;
            }
        }
        answer = Math.max(max, answer);
    }
    // cctv1 리스트 돌면서 사방면 for문 돌기 
    // 1, 3, 4는 방향 4가지/ 2는 2가지 / 5는 1가지

    static class CCTV{
        int x;
        int y;
        int type;

        public CCTV(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}