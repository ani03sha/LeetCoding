package org.redquark.leetcoding.arrays;

import java.util.HashMap;
import java.util.Map;

public class KDiffPairsInAnArray {

    public int findPairs(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Map to keep track of frequencies of elements in the array
        final Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }
        // Count of k-diff pairs
        int pairCount = 0;
        // Process elements in the map
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            final int key = entry.getKey();
            final int value = entry.getValue();
            // The value we are trying to find
            final int target = key + k;
            // When k is zero, then only k-diff pair will be between
            // same elements and only once because we need unique pairs
            if (k == 0 && value > 1) {
                pairCount++;
            }
            // If k is non-zero, and we find target in the array, then
            // we can increment our count by 1
            else if (k != 0 && frequencies.containsKey(target)) {
                pairCount++;
            }
        }
        return pairCount;
    }

    public static void main(String[] args) {
        final KDiffPairsInAnArray kDiffPairsInAnArray = new KDiffPairsInAnArray();

        int[] nums = new int[]{3, 1, 4, 1, 5};
        int k = 2;
        System.out.println(kDiffPairsInAnArray.findPairs(nums, k));

        nums = new int[]{1, 2, 3, 4, 5};
        k = 1;
        System.out.println(kDiffPairsInAnArray.findPairs(nums, k));

        nums = new int[]{1, 3, 1, 5, 4};
        k = 0;
        System.out.println(kDiffPairsInAnArray.findPairs(nums, k));
    }
}
