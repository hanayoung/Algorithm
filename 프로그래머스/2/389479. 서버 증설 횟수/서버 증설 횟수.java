import java.util.Queue;
import java.util.LinkedList;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Integer> server = new LinkedList<>();
        int size = 1;
        for(int i = 0; i < players.length; i++) {
            // i로 현재 시간 파악하기
            while(server.size() > 0 && server.peek() < i) {
                size--;
                server.poll();
            }
            if(players[i] < size*m) continue;
            while(players[i] >= size*m) {
                size++;
                server.add(i+k-1);
                answer++;
            }
        }
        return answer;
    }
}

// m명이 늘어날 때마다 서버 한 대 추가
// 각 서버는 k시간이 지나면 반납함
// n*m <= 이용자 수 < (n+1)*m --> n대의 서버
// 각 시간대별 이용자 수를 돌면서 확인하고
// 이용자수가 (현재 서버 수)*m 보다 크면 서버 증설하고, 증설한 서버에 만료시간을 넣기
// 서버를 담는 걸 Queue<int> 타입으로 생성해서, 만료시간을 갖고 들어가는거지
// 그리고 while(peek) 를 확인해서 만료된 여부도 확인해서 제거하고