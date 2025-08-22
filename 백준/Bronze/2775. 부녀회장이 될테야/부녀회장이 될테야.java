
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        Point[] testCases = new Point[T];
        int N = 0, K = 0;

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(bf.readLine());
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());

            testCases[tc] = new Point(k, n);

            N = Math.max(n, N);
            K = Math.max(k, K);
        }

        int[][] arr = new int[N + 1][K + 2];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < K + 2; j++) {
                if (i == 0) {
                    arr[i][j] = j;
                } else {
                    if (j == 1) {
                        arr[i][j] = arr[i - 1][j];
                    } else {
                        arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
                    }
                }
            }
        }

        for (int tc = 0; tc < T; tc++) {
            Point p = testCases[tc];
            sb.append(arr[p.x][p.y]).append("\n");
        }

        System.out.println(sb);

    }
}
