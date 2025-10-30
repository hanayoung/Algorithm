class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int[] max = {1, 1};
        for(int i = 0; i < sizes.length; i++) {
            if(max[0] < sizes[i][0] || max[1] < sizes[i][1] || max[0] < sizes[i][1] || max[1] < sizes[i][0]) {
            int[] tmpWidth = new int[2];
            tmpWidth[0] = Math.max(max[0], sizes[i][0]);
            tmpWidth[1] = Math.max(max[0], sizes[i][1]);
            int[] tmpHeight = new int[2];
            tmpHeight[0] = Math.max(max[1], sizes[i][1]);
            tmpHeight[1] = Math.max(max[1], sizes[i][0]);

            if(tmpWidth[0]*tmpHeight[0] > tmpWidth[1] * tmpHeight[1]) {
                max[0] = tmpWidth[1];
                max[1] = tmpHeight[1];
            } else {
                max[0] = tmpWidth[0];
                max[1] = tmpHeight[0];
            }
            }

            answer = max[0] * max[1];
        }
        return answer;
    }
}