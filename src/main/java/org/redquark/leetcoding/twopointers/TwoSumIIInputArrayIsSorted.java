package org.redquark.leetcoding.twopointers;

import java.util.Arrays;

public class TwoSumIIInputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        // Left and right pointers
        int left = 0;
        int right = numbers.length - 1;
        // Process array from both ends
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        throw new IllegalArgumentException("Should never reach here");
    }

    public static void main(String[] args) {
        final TwoSumIIInputArrayIsSorted twoSumIIInputArrayIsSorted = new TwoSumIIInputArrayIsSorted();

        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSumIIInputArrayIsSorted.twoSum(nums, target)));

        nums = new int[]{2, 3, 4};
        target = 6;
        System.out.println(Arrays.toString(twoSumIIInputArrayIsSorted.twoSum(nums, target)));

        nums = new int[]{-1, 0};
        target = -1;
        System.out.println(Arrays.toString(twoSumIIInputArrayIsSorted.twoSum(nums, target)));
    }
}
