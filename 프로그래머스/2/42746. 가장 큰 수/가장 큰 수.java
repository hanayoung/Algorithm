import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strings = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
        }
        });
        for(String str: strings) {
            if(answer.equals("0") && "0".equals(str)) continue;
            answer += str;
        }
        
        return answer;
    }
}