import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        LocalDate date = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        
        Map<String, Integer> termDate = new HashMap<>();
        for(int i = 0; i < terms.length; i++) {
            String[] arr = terms[i].split(" ");
            int month = Integer.parseInt(arr[1]);
            termDate.put(arr[0], month);
        }
        for(int i = 0; i < privacies.length; i++) {
            String[] arr = privacies[i].split(" ");
            LocalDate start = LocalDate.parse(arr[0], DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            int month = termDate.get(arr[1]);
            if(start.plusMonths(month).isAfter(date) == false) {
                answer.add(i+1);
            }
            
            
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}