package org.redquark.leetcoding.arrays;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        final int n = nums.length;
        // Make sure that the elements are in their correct position
        // in the array. For e.g., 3 should be at nums[2], 6 should be
        // at nums[5] and so on
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                // Swap the elements
                final int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // Traverse the array again to see if any positive element is
        // out of order
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // If all the elements are in their places, then it is the (n + 1)th
        // element that is missing
        return n + 1;
    }

    public static void main(String[] args) {
        final FirstMissingPositive firstMissingPositive = new FirstMissingPositive();

        int[] nums = new int[]{1, 2, 0};
        System.out.println(firstMissingPositive.firstMissingPositive(nums));

        nums = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositive.firstMissingPositive(nums));

        nums = new int[]{7, 8, 9, 11, 12};
        System.out.println(firstMissingPositive.firstMissingPositive(nums));
    }
}
