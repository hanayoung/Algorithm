import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());

        int[] lights = new int[M];

        for(int i = 0; i < M; i++) {
            lights[i] = Integer.parseInt(st.nextToken());
        }
        
        int left = 1;
        int right = N;

        int answer = N;

        while(left<=right) {
            int mid = (left+right)/2;
            boolean allBright = true;
            int last = lights[0] + mid > N ? N : lights[0] + mid;
            for(int i = 0; i < M; i++) {
                if(i == 0) {
                    if(lights[i] - mid > 0) {
                        allBright = false;
                        break;
                    }
                } else if(i > 0) {
                    if(last < lights[i] - mid) {
                        allBright = false;
                        break;
                    }
                    last = lights[i] + mid > N ? N : lights[i] + mid;
                }
                if(i == M - 1) {
                    if(last < N) {
                        allBright = false;
                        break;
                    } 
                }
            }

            if(allBright == true) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}