import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        int N = Integer.parseInt(st.nextToken());

        arr = new int[N+1];

        recur(N);

        System.out.println(arr[N]);
    }

    static void recur(int num) {
        if(num <= 0) return;
        if(num <= 3 && num > 1) {
            arr[num] = 1;
            return;
        } else if(num == 1) {
            arr[num] = 0;
            return;
        }
        if(arr[num] == 0) {
            recur(num/3);
            recur(num/2);
            recur(num-1);

            int min = Integer.MAX_VALUE;

            if(num/3 != 0 && num % 3 == 0) min = Math.min(arr[num/3], min);
            if(num/2 != 0 && num % 2 == 0) min = Math.min(arr[num/2], min);
            if(num-1 != 0) min = Math.min(arr[num-1], min);
            
            arr[num] = min + 1;
        }

    }
}