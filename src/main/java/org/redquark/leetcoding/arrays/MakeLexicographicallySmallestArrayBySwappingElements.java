package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MakeLexicographicallySmallestArrayBySwappingElements {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        final int n = nums.length;
        // Create a clone of the original array to sort it
        final int[] sorted = nums.clone();
        Arrays.sort(sorted);
        // Build groups
        final Map<Integer, Integer> valueToGroups = new HashMap<>(); // nums[i] -> group id
        final Map<Integer, PriorityQueue<Integer>> groupToValues = new HashMap<>(); // group id -> min heap of values
        // Group id counter
        int groupId = 0;
        valueToGroups.put(sorted[0], groupId);
        groupToValues.computeIfAbsent(groupId, _ -> new PriorityQueue<>()).offer(sorted[0]);
        // Process remaining elements
        for (int i = 1; i < n; i++) {
            int current = sorted[i];
            int previous = sorted[i - 1];
            // If the difference between current and previous is greater than the limit
            if (Math.abs(current - previous) > limit) {
                // Increment the group id
                groupId++;
            }
            valueToGroups.put(current, groupId);
            groupToValues.computeIfAbsent(groupId, _ -> new PriorityQueue<>()).offer(current);
        }
        // Reconstruct the final array
        final int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            // Get the group id for the current element
            int group = valueToGroups.get(nums[i]);
            // Get the minimum value from the min heap of that group
            result[i] = groupToValues.get(group).remove();
        }
        return result;
    }

    public static void main(String[] args) {
        final MakeLexicographicallySmallestArrayBySwappingElements makeLexicographicallySmallestArrayBySwappingElements =
                new MakeLexicographicallySmallestArrayBySwappingElements();

        int[] nums = {1, 5, 3, 9, 8};
        int limit = 2;
        int[] result = makeLexicographicallySmallestArrayBySwappingElements.lexicographicallySmallestArray(nums, limit);
        System.out.println("Lexicographically smallest array: " + Arrays.toString(result)); // Expected output: [1, 3, 5, 8, 9]

        nums = new int[]{1, 7, 6, 18, 2, 1};
        limit = 3;
        result = makeLexicographicallySmallestArrayBySwappingElements.lexicographicallySmallestArray(nums, limit);
        System.out.println("Lexicographically smallest array: " + Arrays.toString(result)); // Expected output: [1, 6, 7, 18, 1, 2]

        nums = new int[]{1, 7, 28, 19, 10};
        limit = 3;
        result = makeLexicographicallySmallestArrayBySwappingElements.lexicographicallySmallestArray(nums, limit);
        System.out.println("Lexicographically smallest array: " + Arrays.toString(result)); // Expected output: [1, 7, 28, 19, 10]


    }
}
