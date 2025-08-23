import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];

        arr[3] = 1;
        if(N >= 5) arr[5] = 1;

        recur(N);

        System.out.println(arr[N] == 0 ? -1 : arr[N]);

    }

    static void recur(int weight) {
        if (weight <= 5) {
            return;
        }
        if (arr[weight] == 0) {
            recur(weight - 3);
            recur(weight - 5);
            if (arr[weight - 3] == 0 && arr[weight - 5] != 0) {
                arr[weight] = arr[weight - 5] + 1;
            } else if (arr[weight - 3] != 0 && arr[weight - 5] == 0) {
                arr[weight] = arr[weight - 3] + 1;
            } else if (arr[weight - 3] != 0 && arr[weight - 5] != 0) {
                arr[weight] = Math.min(arr[weight - 3], arr[weight - 5]) + 1;
            }
        }
    }
}