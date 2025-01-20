package org.redquark.leetcoding.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Find prefix sum of the array
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        // Map to store the frequencies of a certain sum
        final Map<Integer, Integer> sumFrequencies = new HashMap<>();
        // Total number of subarrays that have sum equals to k
        int count = 0;
        for (int num : nums) {
            if (num == k) {
                count++;
            }
            if (sumFrequencies.containsKey(num - k)) {
                count += sumFrequencies.get(num - k);
            }
            sumFrequencies.put(num, sumFrequencies.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        final SubarraySumEqualsK subarraySumEqualsK = new SubarraySumEqualsK();

        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        System.out.println(subarraySumEqualsK.subarraySum(nums, k));

        nums = new int[]{1, 2, 3};
        k = 3;
        System.out.println(subarraySumEqualsK.subarraySum(nums, k));
    }
}
