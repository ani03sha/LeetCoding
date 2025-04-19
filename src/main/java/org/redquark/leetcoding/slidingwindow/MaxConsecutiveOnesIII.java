package org.redquark.leetcoding.slidingwindow;

public class MaxConsecutiveOnesIII {

    public int longestOnes(int[] nums, int k) {
        // Count of maximum consecutive ones
        int maxOnesCount = 0;
        // Left and right pointers for the sliding window
        int left = 0;
        int right = 0;
        // Process all elements in the array
        while (right < nums.length) {
            if (nums[right] == 0) {
                k--;
            }
            while (k < 0) {
                if (nums[left] == 0) {
                    k++;
                }
                left++;
            }
            maxOnesCount = Math.max(maxOnesCount, right - left + 1);
            right++;
        }
        return maxOnesCount;
    }

    public static void main(String[] args) {
        final MaxConsecutiveOnesIII maxConsecutiveOnesIII = new MaxConsecutiveOnesIII();

        int[] nums = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println(maxConsecutiveOnesIII.longestOnes(nums, k));

        nums = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        k = 3;
        System.out.println(maxConsecutiveOnesIII.longestOnes(nums, k));
    }
}
