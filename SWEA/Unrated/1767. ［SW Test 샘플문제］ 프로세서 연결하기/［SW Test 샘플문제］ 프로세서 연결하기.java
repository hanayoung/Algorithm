
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution{
  static int N;
  static int[][] maps;
  static ArrayList<Point> cores;
  static boolean[][] visited;
  static int[] dx = {-1,1,0,0};
  static int[] dy = {0,0,-1,1};
  static int maxCnt = 0;
  static int minLen = Integer.MAX_VALUE;

  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      cores = new ArrayList<>();

      st = new StringTokenizer(bf.readLine());
      N = Integer.parseInt(st.nextToken());
      maps = new int[N][N];
      visited = new boolean[N][N];
      maxCnt = 0;
      minLen = Integer.MAX_VALUE;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(bf.readLine());
        for (int j = 0; j < N; j++) {
          maps[i][j] = Integer.parseInt(st.nextToken());
          if(maps[i][j] == 1) cores.add(new Point(i,j)); 
        }  
      }
      f(0,0,0);
      System.out.println("#"+tc+" "+minLen);

    }
  }


  static void f(int k, int count, int length){ 
    if(k == cores.size()) { // 모든 코어 살펴봄
      if(maxCnt < count) {
        maxCnt = count;
        minLen = length;
      } else if(maxCnt == count && minLen > length){
        minLen = length;
      }
    }else {
      Point core = cores.get(k);
      visited[core.x][core.y] = true;
      for (int i = 0; i < 4; i++) {
        if(isConnectable(core.x, core.y, i) == true) {
          int conLen = setLine(core.x, core.y, true, i);
          f(k+1, count+1, length + conLen);
          setLine(core.x, core.y, false, i);
        }
      }

      f(k+1, count, length);
    }
  }

  static boolean isConnectable(int x, int y, int idx){ // 끝 지점까지 갈 수 있는지 확인
    int nx = x;
    int ny = y;
    boolean flag = false;
    while(true){
      nx += dx[idx];
      ny += dy[idx];
      if(checkRange(nx, ny) == true){
        if(visited[nx][ny] == true || maps[nx][ny] == 1) {
          flag = false;
          break;
        }
      }else{
        flag = true;
        break;
      }
    }
    return flag;
  }

  static boolean checkRange(int x, int y) { // 범위 확인, true일 경우 정상 범위
    if(x < 0 || y < 0 || x >= N || y >= N) return false;
    else return true;
  }

  static int setLine(int x, int y, boolean flag, int idx){ // 연결된 전선들 true처리하거나 다시 false처리하기
    int nx = x;
    int ny = y;
    int cnt = 0;
    while(true){
      nx += dx[idx];
      ny += dy[idx];
      if(checkRange(nx, ny) == true) {
        visited[nx][ny] = flag;
        cnt += 1;
      }else{
        break;
      }
    }
    return cnt;
  }
}