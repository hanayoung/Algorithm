import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static int K;
    static int[][] maps;
    static boolean [][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static ArrayList<Point []> walls = new ArrayList<>();
    static ArrayList<Point> blanks = new ArrayList<>();
    static ArrayList<Point> viruses = new ArrayList<>();
    static Point[] sel = new Point[3];
    static int[][] tmpMaps;
    // static int virusesCnt = 0;
    public static void main(String[] args) throws Exception {
    
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 0은 빈칸, 1은 벽, 2는 바이러스

        maps = new int[N][M];
        visited = new boolean[N][M];
        
        int answer = 0;
        
        int originWallCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if(maps[i][j] == 0) blanks.add(new Point(i,j));
                else if(maps[i][j] == 2) viruses.add(new Point(i,j));
                else originWallCnt += 1;
            }
        }
        comb(0, 0); // 세울 벽 조합 구하기
        // for (int i = 0; i < walls.size(); i++) {
        //     System.out.println(Arrays.toString(walls.get(i)));
        // }

        tmpMaps = new int[N][M];




        for (int i = 0; i < walls.size(); i++) {
          visited = new boolean[N][M];
          for (int l = 0; l < N; l++) {
            tmpMaps[l] = maps[l].clone();
          }
            for (int j = 0; j < 3; j++) {
                Point p = walls.get(i)[j];
                tmpMaps[p.x][p.y] = 1;
            } // 벽 세우기

            int newViruses = 0;

            // if(virusesCnt != 0) System.out.println(virusesCnt);
            for (int j = 0; j < viruses.size(); j++) {
                Point p = viruses.get(j);
                dfs(p.x, p.y, 0);
                // newViruses += virusesCnt;
            }

            // 여기서 dfs 호출해야할 거 같은데 ... 바이러스 기준 출발해야하나 ? ....

            // 초기의 0 개수, 1 개수, 2 개수 세고
            // 추가되는 2 개수 카운트 하고
            // 초기의 0 개수에서 추가된 2 개수, 3(세운 벽) 빼면 될 듯?
            // changed를 더할 때 겹치는 부분이 있어서 다 빼면 음수가 되나?? ..
            int virusesCnt = 0;
            for (int l = 0; l < N; l++) {
              for (int m = 0; m < M; m++) {
                  if(tmpMaps[l][m] == 2) virusesCnt += 1;
              }
            }
            // System.out.println(virusesCnt);
            // answer = Math.max(N*M - viruses.size() - originWallCnt - 3 - newViruses, answer);
            answer = Math.max(N*M - originWallCnt - 3 - virusesCnt, answer);
            // System.out.println(answer);
            for (int j = 0; j < 3; j++) {
                Point p = walls.get(i)[j];
                tmpMaps[p.x][p.y] = 0;
            }
            // dfs 호출 이후에는 벽 세웠던 곳 다시 0으로 만들어주기
        }
        // System.out.println("visited "+Arrays.deepToString(visited));
        // System.out.println();
        System.out.println(answer);
        
    }

    static void dfs(int x, int y, int newViruses) {
        int changed = 0;
        // 돌아다니면서 갈 수 있는 지점은 다 2로 변경하기 (2에서부터 시작할거니까)
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(!checkRange(nextX, nextY)) continue;

            if(tmpMaps[nextX][nextY] != 1 && visited[nextX][nextY] == false) {
                visited[nextX][nextY] = true;
                // if(tmpMaps[nextX][nextY] == 0) changed += 1;
                tmpMaps[nextX][nextY] = 2;
                // virusesCnt = Math.max(newViruses + changed, virusesCnt);
                dfs(nextX, nextY, newViruses + changed);
            }  
        }

    }

    // 벽 세울 좌표들 조합으로 뽑을 것 
    static void comb(int idx, int r) {
        if(r == 3) {
            // 다 뽑음
            Point[] point  = sel.clone(); 
            walls.add(point);
            return;
        }   
        for (int i = idx; i < blanks.size(); i++){
            sel[r] = blanks.get(i);
            comb(i+1, r+1);
        }
    }

    static boolean checkRange(int nextX, int nextY){
        if(nextX > -1 && nextX < N && nextY > -1 && nextY < M) return true;
        else return false;
    }
}
