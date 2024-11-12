import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution{
  static int[] sel;
  static boolean[] checked;
  static int N;
  static int answer;
  static int[] weights;
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int T = Integer.parseInt(st.nextToken());

    for (int tc = 1; tc <= T; tc++) {
        st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        weights = new int[N]; 
        sel = new int[N];
        checked = new boolean[N];
        answer = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
          weights[i] = Integer.parseInt(st.nextToken());
        }
        perm(0);
        System.out.println("#"+tc+" "+answer);
    }
  }

  static void perm(int r){ // 순서 고르기
    if(r == N) {
      // 순서 다 골랐당
      dfs(0, 1,weights[sel[0]],0); // 시작은 무조건 왼쪽부터 고르기
      return;
    }
    for (int i = 0; i < N; i++) {
      if(checked[i] == false) {
        sel[r] = i;
        checked[i] = true;
        perm(r+1);
        checked[i] = false;
      }
    }
  }

  // left가 왼쪽 저울의 무게, right가 오른쪽 저울의 무게
  static void dfs(int dir, int order, int left, int right){ // 왼쪽에 올릴지 오른쪽에 올릴지 dir 0이 왼쪽, 1이 오른쪽 , order은 sel의 order
    if(order == N) { // 다 올렸을 경우
      answer += 1;
      return;
    }
    int next = weights[sel[order]];
    if(left >= right+next){ // 왼쪽이 오른쪽보다 무조건 크거나 같아야함
        dfs(1, order+1, left, right+next);
        dfs(0, order+1, left+next,right);
    } else{
        if(next+left >= right){
            dfs(0, order+1, left+next, right);
        }
    }
  }
}