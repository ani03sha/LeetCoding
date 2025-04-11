package org.redquark.leetcoding.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        // Variable to store cumulative prefix mod
        int prefixModulo = 0;
        // Map to store (sum % k) and their indices
        final Map<Integer, Integer> mappings = new HashMap<>();
        // Since 0 is also a valid answer
        mappings.put(0, -1);
        // Process the array
        for (int i = 0; i < nums.length; i++) {
            prefixModulo = (prefixModulo + nums[i]) % k;
            // Check if we have seen this modulo
            if (mappings.containsKey(prefixModulo)) {
                // Check if the subarray is of size at least 2
                if (i - mappings.get(prefixModulo) > 1) {
                    return true;
                }
            } else {
                // Add modulo and index
                mappings.put(prefixModulo, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final ContinuousSubarraySum continuousSubarraySum = new ContinuousSubarraySum();

        int[] nums = new int[]{23, 2, 4, 6, 7};
        int k = 6;
        System.out.println(continuousSubarraySum.checkSubarraySum(nums, k));

        nums = new int[]{23, 2, 6, 4, 7};
        k = 6;
        System.out.println(continuousSubarraySum.checkSubarraySum(nums, k));

        nums = new int[]{23, 2, 6, 4, 7};
        k = 13;
        System.out.println(continuousSubarraySum.checkSubarraySum(nums, k));
    }
}
