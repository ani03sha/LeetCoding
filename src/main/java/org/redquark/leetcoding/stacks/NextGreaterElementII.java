package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return nums;
        }
        final int n = nums.length;
        // Stack to store the next greater element
        final Deque<Integer> stack = new ArrayDeque<>();
        // Array to store final result
        final int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, -1);
        // Process each element in the array
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                nextGreater[stack.pop() % n] = nums[i % n];
            }
            stack.push(i % n);
        }
        return nextGreater;
    }

    public static void main(String[] args) {
        final NextGreaterElementII nextGreaterElementII = new NextGreaterElementII();

        int[] nums = new int[]{1, 2, 1};
        System.out.println(Arrays.toString(nextGreaterElementII.nextGreaterElements(nums)));

        nums = new int[]{1, 2, 3, 4, 3};
        System.out.println(Arrays.toString(nextGreaterElementII.nextGreaterElements(nums)));
    }
}
