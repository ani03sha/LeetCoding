package org.redquark.leetcoding.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class CountSubarraysWithMedianK {
    public int countSubarrays(int[] nums, int k) {
        final int n = nums.length;
        // Find the index of k in the array
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                index = i;
                break;
            }
        }
        // Map to store balances of elements smaller or greater
        // than k
        final Map<Integer, Integer> counts = new HashMap<>();
        int balance = 0;
        // Check towards right of k
        for (int i = index; i < n; i++) {
            balance += Integer.compare(nums[i], k);
            counts.put(balance, counts.getOrDefault(balance, 0) + 1);
        }
        // Total number of subarrays in which k is median
        int subarrayCount = 0;
        balance = 0;
        // Check towards the left of k
        for (int i = index; i >= 0; i--) {
            balance += Integer.compare(nums[i], k);
            subarrayCount += counts.getOrDefault(-balance, 0) + counts.getOrDefault(-balance + 1, 0);
        }
        return subarrayCount;
    }

    public static void main(String[] args) {
        final CountSubarraysWithMedianK countSubarraysWithMedianK = new CountSubarraysWithMedianK();

        int[] nums = {3, 2, 1, 4, 5};
        int k = 4;
        System.out.println(countSubarraysWithMedianK.countSubarrays(nums, k)); // 3

        nums = new int[]{2, 3, 1};
        k = 3;
        System.out.println(countSubarraysWithMedianK.countSubarrays(nums, k)); // 1

    }
}
