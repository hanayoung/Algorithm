import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(bf.readLine());

        for(int i = 0; i < N; i++) {
            pq.add(Integer.valueOf(st.nextToken()));
            if(pq.size() > K) {
                pq.poll();
            }
        }

        System.out.println(pq.peek());
    }
}
