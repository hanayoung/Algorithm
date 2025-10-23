import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(bf.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int start = 0, max = Integer.MIN_VALUE;

        while(start <= N - K) {
            int sum = 0;
            for(int i = start; i < start + K; i++) {
                sum += arr[i];
            }
            max = Math.max(sum, max);
            start++;
        }

        System.out.println(max);
    }
}