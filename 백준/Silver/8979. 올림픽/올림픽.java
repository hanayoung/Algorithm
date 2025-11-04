import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Medal> pQueue = new PriorityQueue<>();
        int order = 1;

        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int id = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            pQueue.add(new Medal(id, gold, silver, bronze));
        }

        while(pQueue.size() > 0) {
            Medal m = pQueue.poll();
            if(m.id == K) break;
            int same = 0;
            while(pQueue.size() > 0) {
                Medal next = pQueue.peek();
                if(next.gold == m.gold && next.silver == m.silver && next.bronze == m.bronze) {
                    pQueue.poll();
                    if(next.id == K) {
                        m = next;
                        break;
                    }
                    same++;
                }
                else break;
            }
            if(m.id == K) break;
            order += same;
            order++;
        }
        System.out.println(order);
    }

    static class Medal implements Comparable<Medal>{
        int id;
        int gold;
        int silver;
        int bronze;

        public Medal(int id, int gold, int silver, int bronze) {
            this.id = id;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Medal o) {
            if(this.gold > o.gold) return -1;
            else if(this.gold == o.gold) {
                if(this.silver > o.silver) return -1;
                else if(this.silver == o.silver) {
                    if(this.bronze > o.bronze) return -1;
                    else if(this.bronze == o.bronze) return 0;
                }
            }
            return 1;
        }
    }
}
