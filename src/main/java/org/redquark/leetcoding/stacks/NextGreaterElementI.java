package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class NextGreaterElementI {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Stack to store the indices of next greater element
        final Deque<Integer> stack = new ArrayDeque<>();
        // Map to store the next greater element of current element in nums2
        final Map<Integer, Integer> nextGreaterMapping = new HashMap<>();
        // Process all elements in the nums2
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                nextGreaterMapping.put(nums2[stack.pop()], nums2[i]);
            }
            stack.push(i);
        }
        // Prepare output
        final int[] output = new int[nums1.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = nextGreaterMapping.getOrDefault(nums1[i], -1);
        }
        return output;
    }

    public static void main(String[] args) {
        final NextGreaterElementI nextGreaterElementI = new NextGreaterElementI();

        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElementI.nextGreaterElement(nums1, nums2)));

        nums1 = new int[]{2, 4};
        nums2 = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(nextGreaterElementI.nextGreaterElement(nums1, nums2)));
    }
}
