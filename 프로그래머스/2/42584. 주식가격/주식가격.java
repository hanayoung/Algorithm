import java.util.Stack;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Stock> stack = new Stack<>();
        for(int i = 0; i < prices.length; i++) {
            while(stack.size() > 0 && stack.peek().price > prices[i]) {
                Stock top = stack.pop();
                answer[top.second] = i - top.second;
            }
            stack.push(new Stock(prices[i], i));
        }
        int endSecond = prices.length-1;
        while(stack.size() > 0) {
            Stock top = stack.pop();
            answer[top.second] = endSecond - top.second;
        }
        return answer;
    }
    public class Stock {
        int price;
        int second;
        
        public Stock(int price, int second) {
            this.price = price;
            this.second = second;
        }
    }
}