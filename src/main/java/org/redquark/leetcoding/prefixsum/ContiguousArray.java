package org.redquark.leetcoding.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        // Special case
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // Variable to keep track of 1s and 0s
        int count = 0;
        // Maximum length
        int maxLength = 0;
        // Map to store each sum and the index at which that sum occurred
        final Map<Integer, Integer> sumIndexMap = new HashMap<>();
        sumIndexMap.put(0, -1);
        // Process the array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count--;
            } else {
                count++;
            }
            if (sumIndexMap.containsKey(count)) {
                maxLength = Math.max(maxLength, i - sumIndexMap.get(count));
            } else {
                sumIndexMap.put(count, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        final ContiguousArray contiguousArray = new ContiguousArray();

        int[] nums = new int[]{0, 1};
        System.out.println(contiguousArray.findMaxLength(nums));

        nums = new int[]{0, 1, 0};
        System.out.println(contiguousArray.findMaxLength(nums));
    }
}
