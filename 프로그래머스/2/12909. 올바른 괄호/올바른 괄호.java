import java.util.Stack;

class Solution {
  public boolean solution(String s) {
      Stack<Character> stack = new Stack<>();
      boolean answer = true;
      for(int i = 0; i < s.length(); i++) {
          if(s.charAt(i) == ')' && stack.size() > 0) { // 닫는 괄호
              Character prev = stack.pop();
              if(prev == ')') {
                  answer = false;
                  break;
              } 
          } else { // 여는 괄호
              stack.add(s.charAt(i));
          }
      }
      if(stack.size() > 0) answer = false;
      return answer;
  }
}