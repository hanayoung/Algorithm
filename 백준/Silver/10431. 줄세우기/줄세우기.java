import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(st.nextToken());
       
        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < 20; i++) {
                int cur = Integer.parseInt(st.nextToken());
                boolean isLine = false;
                for (int idx = 0; idx < list.size(); idx++) {
                    if(list.get(idx) > cur) {
                        answer += list.size() - idx;
                        list.add(idx, cur);
                        isLine = true;
                        break;
                    }
                }
                if(isLine == false) list.add(cur);
            }
            sb.append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}