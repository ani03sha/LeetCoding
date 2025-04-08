package org.redquark.leetcoding.design;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverageFromDataStream {

    static class MovingAverage {

        private final Deque<Integer> deque;
        private final int size;
        private int runningSum;

        public MovingAverage(int size) {
            this.deque = new ArrayDeque<>();
            this.size = size;
        }

        public double next(int val) {
            // Add the element to the deque
            this.deque.offer(val);
            if (this.deque.size() <= this.size) {
                runningSum += val;
                return runningSum * 1.0 / this.deque.size();
            } else {
                // Remove element from the left
                runningSum -= this.deque.removeFirst();
                runningSum += val;
                return runningSum * 1.0 / this.size;
            }
        }
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // return 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // return 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3)); // return 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5)); // return 6.0 = (10 + 3 + 5) / 3


    }
}
