class Solution {
    static int n;
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        n = numbers.length;
        int[] op = new int[n];
        recur(0, op, numbers, target);
        return answer;
    }
    
    public void recur(int r, int[] op, int[] numbers, int target) {
        if(r == n) {
            int sum = 0;
            for(int i = 0; i < n; i++) {
                sum += op[i]*numbers[i];
            }
            if(sum == target) answer++;
            return;
        }
        op[r] = 1;
        recur(r+1, op, numbers, target);
        op[r] = -1;
        recur(r+1, op, numbers, target);
    }
}