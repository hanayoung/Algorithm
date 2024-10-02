import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
  static int answer = 0;
  public static void main(String[] args) throws Exception{
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int N = Integer.parseInt(st.nextToken());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());


    f(0,N,r,c);
    System.out.println(answer);
  }

  static void f(int order, int n, int sr, int sc){
    if(n==0){
      answer = order;
      return;
    }else{
      int len = (int)Math.pow(2, n);
      if(len/2 > sr && len/2 > sc){
        f(order, n-1, sr, sc);
      }else if(len/2 > sr && len/2 <= sc){
        f(order+(len*len/4), n-1, sr, sc - (len/2));
      }else if(len/2 <= sr && len/2 > sc){
        f(order+(len*len/4*2), n-1, sr - (len/2), sc);
      }else if(len/2 <= sr && len/2 <= sc){
        f(order+(len*len/4*3), n-1, sr - (len/2), sc - (len/2));
      }
    }
  }
}
// 일단 계속 n을 1 빼기 -> n=0이 되면 1*1로 끝
// 1사분면, 2사분면, 3사분면, 4사분면 중 어디에 포함되어있는지 찾기
// 재귀로 넘길 때 한 사분면의 (0,0)의 방문 순서 같이 넘기기
// (0,0)의 방문 순서는 1사분면은 0, 2사분면은 (2^N*2^N)/4, 3사분면은 2^N/4*2, 4사분면은 2^N/4*3
// 기존 방문 순서 + 새로운 사분면의 방문 순서