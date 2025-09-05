import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        long S = Long.parseLong(st.nextToken());

        long sum = 0, num = 0;
        while(sum <= S) {
            num++;
            sum += num;
        }

        if(sum > S) num--;

        System.out.println(num);
    }
}