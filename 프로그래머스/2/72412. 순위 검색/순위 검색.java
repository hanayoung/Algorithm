import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Map<String, List<Integer>> map = new HashMap<>();
        // key별 가장 높은 점수를 저장해놔야하나? 
        // Map<String, Set<Integer>> keyMap = new HashMap<>();
        
        for(String str: info) {
            String[] arr = str.split(" ");
            String key = "";
            for(int i = 0; i < arr.length-1; i++) {
                key += arr[i].charAt(0);
            }
            // keyMap.computeIfAbsent(key, k -> new HashSet<Integer>()).add(Integer.valueOf(arr[arr.length-1]) /1000);
            // key += Integer.valueOf(arr[arr.length-1]) /1000;
            
            // 50점이면 ? 1000으로 넣어야하는데 이걸 어케 하지 일일이 나눠? 좀,,짜치는데
            // 1050점이면 ? 나누기 1000하면 몫이 1이니까 이걸로 하자
            // jbjc0, jbjc1, ... 이런식인고지 
            map.computeIfAbsent(key, k -> new ArrayList<Integer>()).add(Integer.valueOf(arr[arr.length-1]));
        }
        for(List<Integer> list: map.values()) {
            Collections.sort(list);
        }
        String[][] name = {{"c","j","p"},{"b","f"},{"j","s"},{"c","p"}};
        Queue<String> keys = new LinkedList<>();
        List<String> tmp = new ArrayList<>();
        
        for(int k = 0; k < query.length; k++) {
            String q = query[k];
            String[] arr = q.split(" and | (?=\\d+$)");

            for(int i = 0; i < arr.length-1; i++) {
                if("-".equals(arr[i])) {
                    if(keys.size() == 0) {
                        for(String first: name[i]) {
                            tmp.add(first);
                        }
                    } else {
                        while(keys.size() > 0) {
                            String key = keys.poll();
                            for(String first: name[i]) {
                                tmp.add(key+first);
                            }
                        }
                    }
                } else {
                    if(keys.size() == 0) {
                        tmp.add(Character.toString(arr[i].charAt(0)));
                    } else {
                        while(keys.size() > 0) {
                            String key = keys.poll();
                            tmp.add(key+arr[i].charAt(0));
                        }
                    }
                }
                keys.addAll(tmp);
                tmp.clear();
            }
            
            int sum = 0;
            while(keys.size() > 0) {
                String key = keys.poll();
                int score = Integer.valueOf(arr[arr.length-1]);
                // int compute = score/1000;
                List<Integer> values = map.get(key);
                if(values != null) {
                    int left = 0;
                    int right = values.size();
                    int index = values.size();
                    while(left < right) {
                        int mid = (left+right)/2;
                        if(values.get(mid) < score) {
                            left = mid + 1;
                        } else {
                            right = mid;
                            index = mid;
                        }
                    }
                    if(values.size() > index) sum += values.size() - index;
                }
                
                // Set<Integer> set = keyMap.get(key);
                // if(set != null) {
                //     for(int i: set) {
                //         if(i > compute) sum += map.get(key+i).size();
                //     }
                // }
            }
            
            answer[k] = sum;
        }
        return answer;
    }
}