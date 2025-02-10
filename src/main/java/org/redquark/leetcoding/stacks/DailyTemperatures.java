package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        // Special case
        if (temperatures == null || temperatures.length == 0) {
            return temperatures;
        }
        final int n = temperatures.length;
        // Array to store the final output
        final int[] answer = new int[n];
        // Stack to store the index of the next greater temperature
        final Deque<Integer> stack = new ArrayDeque<>();
        // Process all temperatures
        for (int i = 0; i < n; i++) {
            // Keep popping elements from the stack until the top is equal to or
            // greater than the current temperature
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                final int index = stack.pop();
                answer[index] = i - index;
            }
            // Add current index to the stack
            stack.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        final DailyTemperatures dailyTemperatures = new DailyTemperatures();

        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures(temperatures)));

        temperatures = new int[]{30, 40, 50, 60};
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures(temperatures)));

        temperatures = new int[]{30, 60, 90};
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures(temperatures)));
    }
}
