
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        // PriorityQueue<People> pQueue = new PriorityQueue<>();
        People[] people = new People[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            // pQueue.add(new People(i, height, weight));
            people[i] = new People(i, height, weight);
        }

        int[] ranks = new int[N];


        // int assigned = 0;
        // while (pQueue.size() > 0) {
        //     People target = pQueue.poll();
        //     people[assigned] = target;
        //     assigned += 1;
        //     // System.out.printf("weight : %d  height %d index %d\n\n", target.weight, target.height, target.index);
        // }

        for (int i = 0; i < N; i++) {
            int order = 1;
            boolean isAssigned = false;
            for (int j = 0; j < N; j++) {
                // if (people[i].height > people[j].height && people[i].weight > people[j].weight) {
                //     ranks[people[i].index] = order;
                //     isAssigned = true;
                //     System.out.printf("isAssigned ranks[index] : %d index: %d j : %d order: %d i : %d\n\n", ranks[people[i].index], people[i].index, j, order,i);
                //     // break;
                //     // 종료
                // } else 
                if (people[i].height < people[j].height && people[i].weight < people[j].weight) {
                    // 큰 덩치
                    order += 1;
                } 
            }
            ranks[people[i].index] = order;
            // System.out.printf("ranks[index] : %d index: %d order: %d i : %d\n\n", ranks[people[i].index], people[i].index, order,i);
            // if (isAssigned == false) {
            //     ranks[people[i].index] = order;
            //     System.out.printf("isNotAssigned ranks[index] : %d index: %d j : %d order: %d i : %d\n\n", ranks[people[i].index], people[i].index, N, order,i);
            // }
        }
        for (int i = 0; i < ranks.length; i++) {
            sb.append(ranks[i]).append(" ");
        }
        System.out.println(sb);
    }

    static class People implements Comparable<People> {

        int index;
        int height;
        int weight;

        People(int index, int height, int weight) {
            this.index = index;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(People o) {
            if (this.weight > o.weight && this.height > o.height) {
                return -1;
            } else if (this.weight > o.weight || this.height > o.height) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}