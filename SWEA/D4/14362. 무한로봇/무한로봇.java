import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
      st = new StringTokenizer(bf.readLine());
      String str = st.nextToken();
      char[] commands = new char[str.length()];
      boolean allRL = true;
      for (int i = 0; i < str.length(); i++) {
          commands[i] = str.charAt(i);
          if(commands[i] == 'S') allRL = false;
      }

      if(allRL == true){
        System.out.println("#"+tc+" 0");
      } else{
        int[] dx = {0,-1,0,1};
        int[] dy = {1,0,-1,0};
  
        int nx = 0;
        int ny = 0;
        int ndir = 0;
        int dist = 0;
        int idx = 0;
        boolean[] visited = new boolean[4];
        while (idx < 4) { 
          for (int i = 0; i < commands.length; i++) {
              switch(commands[i]) {
                case 'S' :
                nx += dx[ndir];
                ny += dy[ndir];
                dist = Math.max(dist, (int)(Math.pow(nx, 2)) + (int)(Math.pow(ny, 2)));
                break;
    
                case 'L':
                ndir = (ndir + 3) % 4;
                break;
    
                case 'R':
                ndir = (ndir + 1) % 4;
                break;
              }
          }
          if(nx == 0 && ny == 0) break; // 수렴하는 경우
          visited[ndir] = true;
          idx += 1;
        }
      
        int checks = 0;
        if(nx == 0 && ny == 0){
          System.out.println("#"+tc+" "+dist);
        }else{
          for (int i = 0; i < 4; i++) {
            if(visited[i] == true) checks += 1;
          }
  
          if(checks % 2 == 0){
            System.out.println("#"+tc+" "+dist);
          }else{
            System.out.println("#"+tc+" oo");
          }
        }
      }
      }
  }
}