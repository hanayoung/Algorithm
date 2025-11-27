class Solution {
    public int solution(String s) {
        int answer = 2000;
        StringBuilder sb = new StringBuilder();
        String prev = "";
        int index = 0;
        int cnt = 1;
        boolean check = false;
        for(int unit = 1; unit < s.length(); unit++) {
            sb.setLength(0);
            index = unit;
            prev = s.substring(0, unit);
            cnt = 1;
            check = false;
            while(index < s.length()) {
                check = true;
                int end = index+unit;
                if(end >= s.length()) end = s.length();
                String subStr = s.substring(index, end);
                if(prev.equals(subStr)) {
                    cnt++;
                } else {
                    if(cnt > 1) {
                        sb.append(cnt+prev);
                    } else {
                        sb.append(prev);
                    }
                    prev = subStr;
                    cnt = 1;
                } 
                index += unit;
            }
            if(cnt > 1) {
                sb.append(cnt+prev);
            } else sb.append(prev);

            answer = Math.min(answer, sb.length());
        }
        if(sb.length() == 0) answer = s.length();
        return answer;
    }
}