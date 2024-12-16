import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int[] longElements = new int[elements.length*2 -1];
        for (int i = 0; i < elements.length; i++) {
            longElements[i] = elements[i];
            if(i != elements.length-1) {
                longElements[elements.length+i] = elements[i];
            }
        }
        int[][] results = new int[elements.length][3];
        HashSet<Integer> sum = new HashSet<>();
        for (int i = 0; i < elements.length; i++) {
            results[i][0] = elements[i];
            results[i][1] = 1;
            results[i][2] = i;
            sum.add(results[i][0]);
            for (int j = 1; j < elements.length; j++) {
                results[i][0] += longElements[i+j];
                results[i][1] = j+1;
                results[i][2] = i+j;
                sum.add(results[i][0]);
            }
        }
        answer = sum.size();
        return answer;
    }
}