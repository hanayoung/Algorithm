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
    // System.setIn(new FileInputStream("input_1767.txt"));
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(bf.readLine());
      N = Integer.parseInt(st.nextToken());

      init();

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

  static void init(){
    maps = new int[N][N];
    visited = new boolean[N][N];
    maxCnt = 0;
    minLen = Integer.MAX_VALUE;
    cores = new ArrayList<>();
  }
}

// 전선은 직선으로만 연결할 수 있음
// 상하좌우를(사방향으로) 쭉 이동해봄
// 1로 막히거나, 방문되어있는 지점이라면 이동할 수 없음
// 한 곳으로 가보고 끝 지점(0이나 N-1)까지 갈 수 있다면? 연결된 것 -> while로 쭉쭉쭉 일단 이동? 
// 이걸 어케 한담?
// 연결될 수 있다면, 이제 다른 core 살펴보기
// 이동할 때는 다 방문처리하기
// 돌아오면 방문처리한 거 다시 false로 돌리기

// core의 위치들은 값을 받을 때 따로 저장해두기
// 해당 core들의 개수만큼 for문 돌면서 위의 일 수행하기
// 전선 길이는 어디다가 넣어두지?
// core 개수는 어디다가 넣어두지?
// 파라미터로 같이 넣어줄까? -> 그러면 파라미터로 다음 이동좌표, 전선 길이, core 개수
