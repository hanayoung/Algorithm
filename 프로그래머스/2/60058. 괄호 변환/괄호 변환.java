class Solution {
    public String solution(String p) {
        String answer = "";
        answer = recur(p, answer);

        return answer;
    }
    
    public String recur(String p, String answer) {

        if(p.length() == 0) return answer;
        int left = 0, right = 0, index = 0;
        int sum = 0;
        boolean isRight = true;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '(') {
                left++;
                sum -= 1;
            }
            else {
                right++;
                sum += 1;
            }
            if(sum > 0) isRight = false;
            if(left == right) {
                index = i;
                break;
            }
        }
        if(sum != 0) isRight = false;
        if(isRight == false) {
            answer += '(';
            answer = recur(p.substring(index+1), answer);
            answer += ')';
            String str = p.substring(1, index);
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '(') answer += ')';
                else answer += '(';
            }
        } else {
            answer = recur(p.substring(index+1), answer+p.substring(0, index+1));
        }
        return answer;
    }
}

// u와 v로 분리. u는 균형잡힌 괄호 문자열로 더 이상 분리 x
// u가 올바른 괄호 문자열이라면, v로 이어서 진행
// u가 올바르지 않다면, '(' + 문자열 v에 대한 진행 후의 문자열 + ')'+ u의 첫 번째 마지막 제거 후 다 뒤집어서 붙이기
// 균형잡힌 건 ( ) 개수가 같은 경우, 올바른 건 짝이 맞을 경우
// 우선 균형잡힌 가장 짧은 u를 찾기
//