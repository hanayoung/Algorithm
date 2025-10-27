import java.util.TreeMap;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for(String op: operations) {
            String[] com = op.split(" ");
            switch(com[0]) {
                case "I":
                    treeMap.put(Integer.valueOf(com[1]), treeMap.getOrDefault(Integer.valueOf(com[1]), 0) + 1);
                    break;
                case "D":
                    if(treeMap.size() > 0){
                        switch(com[1]) {
                            case "-1":
                                int min = treeMap.firstKey();
                                if(treeMap.get(min) > 1) treeMap.put(min, treeMap.get(min) -1);
                                else treeMap.remove(min);
                                break;
                            case "1":
                                int max = treeMap.lastKey();
                                if(treeMap.get(max) > 1) treeMap.put(max, treeMap.get(max) -1);
                                else treeMap.remove(max);
                                break;
                        }
                    }
                    break;
            }
        }
    
        if(treeMap.size() > 0) {
            answer[0] = treeMap.lastKey();
            answer[1] = treeMap.firstKey();
        }
        
        return answer;
    }
}
