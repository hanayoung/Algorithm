import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Object[] arr = new Object[N+1];
        int[][] dp = new int[N+1][K+1];

        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()); 
            arr[i] = new Object(w, v);
        }

        for(int i = 1; i < N+1; i++) {
            for(int j = 1; j < K+1; j++) {
                if(j < arr[i].weight) { // i번째 물건을 담을 수 없음
                    dp[i][j] = dp[i-1][j];
                } else {
                    // i-1 번째 물건까지 넣었을 때랑
                    // i번째 물건 무게와 i-1 번째 물건 무게 합친 게 j보다 작거나 같으면 추가적으로 담을 수 있지 않나?
                    // 더 크면 무게 비교해서 더 큰 걸로 담기
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i].weight]+arr[i].value);
                }
            }
        }

        System.out.println(dp[N][K]);

    }

    static class Object {
        int weight;
        int value;

        public Object(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}