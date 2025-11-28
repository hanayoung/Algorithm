import java.util.*;

class Solution {
    static int answer = 0;
    static Map<String, Boolean> used;
    public int solution(String[][] relation) {
        used = new HashMap<>();
        int[] indexArr = new int[relation[0].length];
        for(int i = 0; i < relation[0].length; i++) {
            indexArr[i] = i;
        }
        for(int i = 1; i <= relation[0].length; i++) {
            int[] current = new int[i];
            for(int j = 0; j < i; j++) {
                current[j] = -1;
            }
            recur(indexArr, current, 0, 0, i, relation);
        }
        return answer;
    }
    
    public void recur(int[] indexArr, int[] current, int depth, int r, int max, String[][] relations) {
        if(max == depth) {
            for(int i = 2; i <= max; i++) {
                if(comb(new int[i], 0, 0, current) == false) return;
            }
            Map<String, Boolean> map = new HashMap<>();
            for(String[] relation: relations) {
                String key = "";
                for(int pick: current) {
                    key += relation[pick];
                }
                map.put(key, true);
            }
            if(relations.length == map.size()) {
                answer++;
                String key = "";
                for(int pick: current) {
                    key += pick;
                }
                used.put(key, true);
            }
            return;
        }
        
        for(int i = r; i < indexArr.length; i++) {
            int index = indexArr[i];
            if(used.getOrDefault(index+"", false) == true) continue;
            current[depth] = index;
            recur(indexArr, current, depth+1, i+1, max, relations);
        }
    }
    
    public boolean comb(int[] current, int depth, int r, int[] arr) {
        if(depth == current.length) {
            String str = "";
            for(int i = 0; i < current.length; i++) {
                str += current[i];
            }
            if(used.getOrDefault(str, false) == true) return false;
            else return true;
        }
        for(int i = r; i < arr.length; i++) {
            current[depth] = arr[i];
            if(comb(current, depth+1, i+1, arr) == false) return false;
        }
        return true;
    }
}
// map에 넣어보기, 개수가 같으면 ㅇㅋ 아닐까?
// 이미 후보키가 된 애는 다음 거 묶을 때 제외하기
