package org.redquark.leetcoding.slidingwindow;

public class MaxConsecutiveOnesIII {

    public int longestOnes(int[] nums, int k) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        // One more special case
        if (n < k) {
            return n;
        }
        // Left and right pointers of the sliding window
        int left = 0;
        int right = 0;
        // Longest length
        int longestLength = 0;
        // Current length of a valid window
        int currentLength = 0;
        // Process the binary array
        while (right < n) {
            // If the current element is one
            if (nums[right] == 1) {
                currentLength++;
                right++;
            }
            // If the current element is zero, but we have margin
            // to flip it
            else if (nums[right] == 0 && k > 0) {
                currentLength++;
                k--;
                right++;
            }
            // If we encounter zero, but we don't have a margin to flip it
            else {
                // Update the longestLength
                longestLength = Math.max(longestLength, right - left);
                // Move a left pointer to the first zero
                while (nums[left] == 1) {
                    left++;
                }
                left++;
                right++;
                currentLength = right - left;
            }
        }
        return Math.max(currentLength, longestLength);
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
