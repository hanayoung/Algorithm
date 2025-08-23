import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] arr;
    static final int INF = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        recur(N);

        System.out.println(arr[N]);
    }

    static int recur(int num) {
        if (num == 1) {
            return 0;
        }
        if (arr[num] != 0) {
            return arr[num];
        }

        int min = INF;

        if (num % 3 == 0) {
            min = Math.min(min, recur(num/3));
        }
        if (num % 2 == 0) {
            min = Math.min(min, recur(num/2));
        }
        min = Math.min(min, recur(num-1));

        return arr[num] = min+1;

    }
}
