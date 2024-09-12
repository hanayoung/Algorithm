import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  static int prevHeight;
  static int length;
  static boolean canBuild;
  static boolean isLong;
  static boolean isLower;
  static int N;
  static int X;
  static int[][] maps;
  static int answer = 0;
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());
    int T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++){
      st = new StringTokenizer(bf.readLine());
      N = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());

      maps = new int[N][N];
      init();
      answer = 0;

      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(bf.readLine());
          for (int j = 0; j < N; j++) {
              maps[i][j] = Integer.parseInt(st.nextToken());
          }
      }

      for (int i = 0; i < N; i++) {
        init();
        for (int j = 0; j < N; j++) {
          if(j == 0) {
            prevHeight = maps[i][j];
            length = 1;
          }else{
            int diff = maps[i][j] - prevHeight;
          
            if(diff == 0) { // 길이가 같은 경우
              length += 1;
              if(isLower == true && length >= X) {
                isLong = true;
                canBuild = true;
              }
              if(length == N) {
                isLong = true;
                canBuild = true;
              }
            } else if(diff == 1){ // 1칸 높아진 경우
              if(isLower == true) {
                if(length >= 2*X) {
                  canBuild = true;
                  isLong = true;
                }else{
                  canBuild = false;
                  isLong = false;
                  break;
                }
              }else{
                if(length >= X) {
                  canBuild = true;
                  isLong = true;
                }else{
                  canBuild = false;
                  isLong = false;
                  break;
                }
              }
              isLower = false;
              length = 1;
              prevHeight = maps[i][j];
            } else if(diff == -1) { // 1칸 낮아진 경우
              if(length < X && isLower) {
                canBuild = false;
                isLong = false;
                break;
              }
              isLower = true;
              canBuild = true;
              length = 1;
              prevHeight = maps[i][j];
            }else {
              canBuild = false;
              isLong = false;
              break;
            }
          }
        }

        if(canBuild == true && isLong == true) {
          if(isLower == true && length < X) continue;
          answer += 1;
        }
      }

      for (int j = 0; j < N; j++) {
        init();
        for (int i = 0; i < N; i++) {
          if(i == 0) {
            prevHeight = maps[i][j];
            length = 1;
          }else{
            int diff = maps[i][j] - prevHeight;
          
            if(diff == 0) { // 길이가 같은 경우
              length += 1;
              if(isLower == true && length >= X) {
                isLong = true;
                canBuild = true;
              }
              if(length == N) {
                isLong = true;
                canBuild = true;
              }
            } else if(diff == 1){ // 1칸 높아진 경우
              if(isLower == true) {
                if(length >= 2*X) {
                  canBuild = true;
                  isLong = true;
                }else{
                  canBuild = false;
                  isLong = false;
                  break;
                }
              }else{
                if(length >= X) {
                  canBuild = true;
                  isLong = true;
                }else{
                  canBuild = false;
                  isLong = false;
                  break;
                }
              }
              isLower = false;
              length = 1;
              prevHeight = maps[i][j];
            } else if(diff == -1) { // 1칸 낮아진 경우
              if(length < X && isLower) {
                canBuild = false;
                isLong = false;
                break;
              }
              isLower = true;
              canBuild = true;
              length = 1;
              prevHeight = maps[i][j];
            }else {
              canBuild = false;
              isLong = false;
              break;
            }
          }
        }

        if(canBuild == true && isLong == true) {
          if(isLower == true && length < X) continue;
          answer += 1;
        }
      }
      System.out.println("#"+tc+" "+answer);

    }
  }

  static void init() {
    canBuild = false;
    isLong = false;
    isLower = false;
    prevHeight = 0;
    length = 0;
  }
}