
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        HashSet<Integer> hashSet = new HashSet();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            hashSet.add(Integer.valueOf(st.nextToken()));
        }
        st = new StringTokenizer(bf.readLine());

        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            if (hashSet.contains(Integer.valueOf(st.nextToken()))) {
                sb.append(1).append("\n"); 
            }else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);

    }
}
