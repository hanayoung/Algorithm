
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int[] op;
    static int[] arr;
    static int[] sel;
    static int N;
    static int min;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        // 1-2/3+4+5*6
        // 10개를 조합으로 ?
        // 
        op = new int[4]; // 덧셈, 뺄셈, 곱셈, 나눗셈
        arr = new int[N]; // 연산할 숫자

        sel = new int[N - 1]; // 연산자 순서
        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        comb(0);

        System.out.println(max);
        System.out.println(min);
    }

    static void comb(int n) {
        // op에서 하나씩 줄이면서 sel에 넣기
        // n이 지금까지 뽑힌 개수, 
        if (n == N - 1) {
            // 계산하기
            int sum = arr[0];
            for(int i = 0; i < N-1; i++) {
                switch(sel[i]) {
                    case 0: sum += arr[i+1];
                    break;
                    case 1: sum -= arr[i+1];
                    break;
                    case 2: sum *= arr[i+1];
                    break;
                    case 3: sum /= arr[i+1];
                    break;
                }
            }
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                sel[n] = i;
                op[i] -= 1;
                comb(n + 1);
                op[i] += 1;
            }
        }

    }
}
