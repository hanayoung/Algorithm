
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        int[] testcases = new int[T];
        int maxNum = 1;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            testcases[i] = num;
            maxNum = Math.max(maxNum, num);
        }
        dp = new int[maxNum + 1][2]; // [n][0] -> 0 호출 횟수, [n][1] -> 1 호출 횟수
        for (int i = 0; i < maxNum + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = dp[1][1] = 1;
        dp[0][1] = dp[1][0] = 0;

        fibo(maxNum);

        for (int tc : testcases) {
            sb.append(dp[tc][0]).append(" ").append(dp[tc][1]).append("\n");
        }

        System.out.println(sb);
    }

    static void fibo(int n) {
        if (n <= 1) {
            return;
        }
        if (dp[n][0] > -1 || dp[n][1] > -1) {
            return;
        }
        fibo(n - 2);
        fibo(n - 1);
        dp[n][0] = dp[n - 2][0] + dp[n - 1][0];
        dp[n][1] = dp[n - 2][1] + dp[n - 1][1];
        // 2는 dp[1][0] + dp[0][0] 더한값? = 1, dp[1][1]+dp[0][1] = 1
        // 3은 dp[2][0] + dp[1][0] = 1, dp[2][1] +dp[1][1] = 2

    }
}
