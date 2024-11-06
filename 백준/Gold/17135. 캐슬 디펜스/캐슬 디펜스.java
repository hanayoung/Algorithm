import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
    static int N;
    static int M;
    static int D;
    static int[] sel;
    static ArrayList<Point> tEnemies;
    static ArrayList<Point> enemies;
    static int answer;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        enemies = new ArrayList<>();
        sel = new int[3];
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) enemies.add(new Point(i,j));
            }
        }

        comb(0, 0); // 궁수 자리 뽑기

        System.out.println(answer);

    }

    static void comb(int idx, int r){
        if(r == 3){
            // 다 뽑았당 게임 시작!
            cloneEnemiesList();
            attack();
            return;
        }

        for (int i = idx; i < M; i++) {
            sel[r] = i;
            comb(i+1, r+1);
        }
    }

    static void attack(){
        int attacks = 0;
        while(tEnemies.size() > 0){
            PriorityQueue<Game> pQueue = new PriorityQueue<>();
            ArrayList<Point> attackedList = new ArrayList<>();
            for (Point enemy : tEnemies) {
                for (int i = 0; i < sel.length; i++) {
                    int dist = Math.abs(enemy.x - N) + Math.abs(enemy.y - sel[i]);
                    if(dist <= D){
                        pQueue.add(new Game(enemy, dist , i));
                    }
                }
            }
    
            boolean[] visited = new boolean[3];
            while(pQueue.size() != 0){
                Game g = pQueue.poll();
                if(visited[g.archer] == false){ // i번째의 궁수가 공격한 적 있는가?
                    attackedList.add(g.p); // 공격당한 적 추가
                    visited[g.archer] = true; // i번째의 궁수가 공격한 걸로 변경
                    boolean allAttack = true;
                    for (int i = 0; i < 3; i++) { // 3명의 궁수가 모두 공격했는지 확인
                        if(visited[i] == false) {
                            allAttack = false;
                            break;
                        }
                    }
                    if(allAttack == true) { // 3명의 궁수가 이미 다 공격했다면 더이상 볼 이유 x
                        pQueue.clear();
                        break;
                    }
                }
            }
            for(int i = 0; i < attackedList.size(); i++){
                if(tEnemies.remove(attackedList.get(i)) == true) attacks += 1;
            }
            moveEnemies();
        }
        answer = Math.max(answer, attacks);
    }

    static void moveEnemies(){
        ArrayList<Point> tmp = new ArrayList<>();
        for (int i = 0; i < tEnemies.size(); i++) {
            Point p = tEnemies.get(i);
            if(p.x + 1 < N) tmp.add(new Point(p.x+1, p.y));
        }
        tEnemies = tmp;
    }

    static void cloneEnemiesList(){
        tEnemies = new ArrayList<>();

        for (int i = 0; i < enemies.size(); i++) {
            tEnemies.add(enemies.get(i));
        }
    }

    static class Game implements Comparable<Game>{
        Point p;
        int dist;
        int archer;


        public Game(Point p, int dist, int archer){
            this.p = p;
            this.dist = dist;
            this.archer = archer;
        }

        @Override
        public int compareTo(Game o) {
            if(this.dist < o.dist) return -1;
            else if(this.dist == o.dist){
                if(this.p.y < o.p.y) return -1;
                else return 1;
            }
            else return 1;
        }
    }
}