package org.redquark.leetcoding.binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // List to store the longest increasing sequence
        final List<Integer> lis = new ArrayList<>();
        // Process through all the elements in the array
        for (int num : nums) {
            // Find the correct index of the current element in the list
            int index = Collections.binarySearch(lis, num);
            // If the element is not found, we will adjust the index
            if (index < 0) {
                index = -(index + 1);
            }
            // If the index is equal to the size of the list, it means we
            // need to add this element at the end of the list
            if (lis.size() == index) {
                lis.add(num);
            }
            // Else, we will add the number to its correct position
            else {
                lis.set(index, num);
            }
        }
        return lis.size();
    }

    public static void main(String[] args) {
        final LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();

        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));

        nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));

        nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS(nums));
    }
}
