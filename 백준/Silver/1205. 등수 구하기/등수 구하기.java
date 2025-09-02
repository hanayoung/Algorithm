
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int newScore = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int answer = N - 1;

        if (N == 0) {
            answer = 1;
        } else {
            st = new StringTokenizer(bf.readLine());

            Integer[] scores = new Integer[N];

            for (int i = 0; i < N; i++) {
                scores[i] = Integer.valueOf(st.nextToken());
            }

            Arrays.sort(scores, Collections.reverseOrder());

            if (N >= P && newScore <= scores[P - 1]) {
                answer = -1;
            } else {
                int left = 0, right = N - 1;

                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (newScore < scores[mid]) {
                        left = mid + 1;
                        answer = mid + 1;
                    } else if (newScore > scores[mid]) {
                        right = mid - 1;
                    } else {
                        answer = mid;
                        break;
                    }
                }

                while (answer > 0 && scores[answer - 1] == newScore) {
                    answer--;
                }

                answer++;
            }

        }

        System.out.println(answer);
    }
}
