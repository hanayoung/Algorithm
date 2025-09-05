
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            st = new StringTokenizer(bf.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int count = 0;
        for(int i = 0; i < N; i++) {
            while(K-sum >= coins[i]) {
                sum += coins[i];
                count++;
            }
        }
        System.out.println(count);
    }
}
