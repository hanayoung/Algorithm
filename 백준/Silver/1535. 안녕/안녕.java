import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][2];

        int[][] dp = new int[N + 1][100];
        st = new StringTokenizer(bf.readLine());

        for (int i = 1; i < N + 1; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < 100; j++) {
                if (j < arr[i][0]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr[i][0]] + arr[i][1]);
                }
            }
        }

        System.out.println(dp[N][99]);

    }
}
