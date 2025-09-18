import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());

        dp = new int[n+1];

        dp[1] = 1;
        
        if(n >= 2) {
            dp[2] = 2;
            recur(n);
        }

        System.out.println(dp[n]);
    }

    static int recur(int num) {
        if(num < 1) return 0;
        if(dp[num] != 0) return dp[num];

        return dp[num] = (recur(num-1) + recur(num-2)) % 10007;
    }
}