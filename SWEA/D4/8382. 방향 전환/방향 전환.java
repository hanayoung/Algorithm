import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(bf.readLine());
            Point start = new Point(Integer.parseInt(st.nextToken())+100, Integer.parseInt(st.nextToken())+100);
            Point end = new Point(Integer.parseInt(st.nextToken())+100, Integer.parseInt(st.nextToken())+100);
            
            int min = Integer.MAX_VALUE;
            PriorityQueue<Move> queue = new PriorityQueue<>();
            queue.add(new Move(start, true, 0));
            queue.add(new Move(start, false, 0));
            boolean[][][] visited = new boolean[201][201][2];
            visited[start.x][start.y][0] = true; // 0은 vertical, 1은 horizontal
            visited[start.x][start.y][1] = true;

            while(queue.size() != 0){
                Move m = queue.poll();
                if(end.equals(m.p)) {
                    min = Math.min(min, m.cnt);
                    queue.clear();
                    break;
                }
                if(m.isVertical == true){
                    if(m.p.y+1 >= 0 && m.p.y+1 <= 200 && visited[m.p.x][m.p.y+1][1] == false){
                        queue.add(new Move(new Point(m.p.x, m.p.y+1), false, m.cnt+1));
                        visited[m.p.x][m.p.y+1][1] = true;
                    }
                    if(m.p.y-1 >= 0 && m.p.y-1 <= 200 && visited[m.p.x][m.p.y-1][1] == false) {
                        queue.add(new Move(new Point(m.p.x, m.p.y-1), false, m.cnt+1));
                        visited[m.p.x][m.p.y-1][1] = true;
                    }
                }else{
                    if(m.p.x+1 >= 0 && m.p.x+1 <= 200 && visited[m.p.x+1][m.p.y][0] == false){
                        queue.add(new Move(new Point(m.p.x+1, m.p.y), true, m.cnt+1));
                        visited[m.p.x+1][m.p.y][0] = true;
                    }
                    if(m.p.x-1 >= 0 && m.p.x-1 <= 200 && visited[m.p.x-1][m.p.y][0] == false) {
                        queue.add(new Move(new Point(m.p.x-1, m.p.y), true, m.cnt+1));
                        visited[m.p.x-1][m.p.y][0] = true;
                    } 
                }
            }

            System.out.println("#"+tc+" "+min);
        }
    }
    static class Move implements Comparable<Move>{
        Point p;
        boolean isVertical; // 가로이동이었는지
        int cnt;

        public Move(Point p, boolean isVertical, int cnt){
            this.p = p;
            this.isVertical = isVertical;
            this.cnt = cnt;
        }
        

        @Override
        public int compareTo(Move o) {
            if(this.cnt < o.cnt) return  -1;
            else return 1;
        }
    }
}