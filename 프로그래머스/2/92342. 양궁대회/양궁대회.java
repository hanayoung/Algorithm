import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    static List<Integer> answer;
    static int max;
    public int[] solution(int n, int[] info) {
        answer = new ArrayList<Integer>();
        max = 0;
        recur(0, n, 0, new int[11], info);
        if(answer.size() == 0) answer.add(-1);
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    public void recur(int depth, int n, int next, int[] current, int[] info) {
        if(depth == n || next == 10) {
            if(depth != n && next == 10) {
                current[10] += n - depth;
            }
            int score = compareScore(info, current);
            if(score > 0) {
                if(max < score) {
                    max = score;
                    answer = Arrays.stream(current).boxed().collect(Collectors.toList());
                } else if(max == score) {
                    for(int i = 10; i >= 0; i--) {
                        if(answer.get(i) < current[i]) {
                            answer = Arrays.stream(current).boxed().collect(Collectors.toList());
                            break;
                        } else if(answer.get(i) > current[i]) break;
                    }
                }
            }
            if(depth != n && next == 10) current[10] -= n - depth;
            return;
        }

        for(int i = next; i < 10; i++) {
            if(info[i] > 0 && n - depth >= info[i]+1) {
                current[i] += info[i]+1;
                recur(depth+info[i]+1, n, i+1, current, info);
                current[i] -= info[i]+1;
            }
            if(info[i] == 0) {
                current[i] = 1;
                recur(depth+1, n, i+1, current, info);
                current[i] = 0;
            }
            recur(depth, n, i+1, current, info);
        }
    }
    
    public int compareScore(int[] apeach, int[] ryan) {
        int aScore = 0;
        int rScore = 0;
        for(int i = 0; i < 10; i++) {
            if(apeach[i] == ryan[i] && apeach[i] == 0) continue;
            if(apeach[i] >= ryan[i]) aScore += 10-i;
            else rScore += 10-i;
        }
        
        if(aScore - rScore >= 0) return -1;
        else return rScore - aScore;
    }
}
// 각 과녁에 맞춘 어피치 거 = info
// 라이언이 n발을 쏴서 가장 크게 이기는 경우 구하는 문제
// 같은 개수를 맞추면 어피치가 점수를 가져감. --> 더 많이 맞춰야지만 라이언이 점수를 가져감
// n은 10, info 크기는 11이지만 0은 빼고 10
// 가장 큰 점수 차이로 우승하는 방법을 고르되, 같으면 가장 낮은 점수를 더 많이 맞힌 경우를 return하기
// 어차피 한 과녁에 몇 발을 쏘든 점수는 같으므로, 어피치가 쏜 것보다 크게 쏘거나, 아예 안 쏘거나, 아니면 한 발만 쏘거나