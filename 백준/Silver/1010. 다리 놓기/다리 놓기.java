import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static long[][] memo;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        memo = new long[30][30];

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            recur(m, n);
            sb.append(memo[m][n]).append("\n");
        }
        System.out.println(sb);
    }

    static void recur(int n, int r) {
        if (n == 0 || n < r) {
            return;
        }
        if (r == 0 || n == r) {
            memo[n][r] = 1; 
        }else if (memo[n][r] == 0) {
            recur(n - 1, r - 1);
            recur(n - 1, r);
            memo[n][r] = memo[n - 1][r - 1] + memo[n - 1][r];
        }
    }
}