import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0,0};

        HashSet<String> occur = new HashSet<>();
        occur.add(words[0]);
        Character before = words[0].charAt(words[0].length()-1);
        // n은 사람 수, words는 나타난 단어 수, 탈락하는 사람의 번호와 몇 번째 차례인지 출력
        // 끝나는 번째 수를 찾아서, (i%3 +1)이 탈락하는 사람의 번호, (i/3 +1)이 차례
        for (int i = 1; i < words.length; i++) {
            if(occur.contains(words[i])) {
                answer[0] = i%n +1;
                answer[1] = i/n +1;
                break;
            } else {
                if(words[i].startsWith(Character.toString(before))){
                    occur.add(words[i]);
                    before = words[i].charAt(words[i].length()-1);
                } else {
                    answer[0] = i%n +1;
                    answer[1] = i/n +1;
                    break;
                }
                
            }
        }
        return answer;
    }
}