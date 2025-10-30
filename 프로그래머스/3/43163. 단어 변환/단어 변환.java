import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    static int answer;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        // 리스트에 words를 담고 쓴 단어는 제거하기, 단어들을 탐색하며 하나만 달라서 바꿀 수 있는 단어를 다음 단어로 넘기기
        List<String> list = new ArrayList<>(Arrays.asList(words));
        dfs(list, 0, target, begin);
        answer = answer == Integer.MAX_VALUE ? 0 : answer;
        return answer;
    }
    public void dfs(List<String> list, int change, String target, String str) {
        if(target.equals(str)) {
            answer = Math.min(answer, change);
            return;
        }
        for(int n = 0; n < list.size(); n++) {
            int cnt = 0;
            String word = list.get(n);
            for(int i = 0; i < word.length(); i++) {
                if(str.charAt(i) != word.charAt(i)) {
                    cnt++;
                }
                if(cnt >= 2) break; // 2개 이상 다르면 못 바꾸는 단어
            }
            if(cnt >= 2) continue; // 2개 이상 다르면 못 바꾸는 단어
            list.remove(word); // word로 바꿨으니 제거
            dfs(list, change+1, target, word);
            list.add(word); // 다시 복귀
        }
    }
}