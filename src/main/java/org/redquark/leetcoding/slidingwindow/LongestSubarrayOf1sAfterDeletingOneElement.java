package org.redquark.leetcoding.slidingwindow;

public class LongestSubarrayOf1sAfterDeletingOneElement {

    public int longestSubarray(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // Start and end pointers of the window
        int left = 0;
        int right = 0;
        // Count of zeroes in the window
        int zeroCount = 0;
        // Longest Length
        int longestLength = 0;
        // Process array
        while (right < n) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            // At most one zero is allowed
            while (zeroCount > 1) {
                // Squeeze the window until there's at most one zero remaining
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            // Update the longest length, if required
            longestLength = Math.max(longestLength, right - left);
            right++;
        }
        return longestLength;
    }

    public static void main(String[] args) {
        final LongestSubarrayOf1sAfterDeletingOneElement longestSubarrayOf1sAfterDeletingOneElement = new LongestSubarrayOf1sAfterDeletingOneElement();

        int[] nums = new int[]{1, 1, 0, 1};
        System.out.println(longestSubarrayOf1sAfterDeletingOneElement.longestSubarray(nums));

        nums = new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println(longestSubarrayOf1sAfterDeletingOneElement.longestSubarray(nums));

        nums = new int[]{1, 1, 1};
        System.out.println(longestSubarrayOf1sAfterDeletingOneElement.longestSubarray(nums));
    }
}
