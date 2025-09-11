
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] switches = new int[N+1];

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        int stuNum = Integer.parseInt(st.nextToken());

        for (int i = 0; i < stuNum; i++) {
            st = new StringTokenizer(bf.readLine());
            int gender = Integer.parseInt(st.nextToken()); // 1이면 남학생, 2이면 여학생
            int num = Integer.parseInt(st.nextToken()); // 스위치 번호

            switch (gender) {
                case 1: // 남학생 : 자기 숫자의 배수에 해당하는 스위치 조작
                    for (int j = 1; j <= N; j++) {
                        if (j % num == 0) {
                            switches[j] = 1 - switches[j];
                        }
                    }
                break;
                case 2:// 여학생 : 자기 숫자 기준으로 양옆 달라질 때까지 구간 찾아서 스위치 상태 모두 바꿈
                    int idx = 1;
                    while (true) {
                        if (idx < num && num + idx <= N && switches[num - idx] == switches[num + idx]) {
                            switches[num - idx] = 1 - switches[num - idx];
                            switches[num + idx] = 1 - switches[num + idx];
                            idx++;
                        } else {
                            break;
                        }
                    }
                    switches[num] = 1 - switches[num];
                break;
                default:
            }
        }

        for (int i = 1; i <= N; i++) {
            if(i != 1 && i % 20 == 1) sb.append("\n");
            sb.append(switches[i]).append(" ");
        }
        System.out.println(sb);

    }
}
