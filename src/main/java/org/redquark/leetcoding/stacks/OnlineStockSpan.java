package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class OnlineStockSpan {

    static class StockSpanner {

        private final Deque<int[]> stack;

        public StockSpanner() {
            this.stack = new ArrayDeque<>();
        }

        public int next(int price) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                span += stack.peek()[1];
                stack.pop();
            }
            stack.push(new int[]{price, span});
            return span;
        }
    }

    public static void main(String[] args) {
        final StockSpanner stockSpanner = new StockSpanner();

        System.out.println(stockSpanner.next(100)); // return 1
        System.out.println(stockSpanner.next(80));  // return 1
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(70));  // return 2
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(75));  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner.next(85));  // return 6
    }
}
