package org.redquark.leetcoding.math;

public class MissingNumber {

    public int missingNumber(int[] nums) {
        final int n = nums.length;
        // Sum of all elements in the array
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (n * (n + 1) / 2) - sum;
    }

    public static void main(String[] args) {
        final MissingNumber missingNumber = new MissingNumber();

        int[] nums = new int[]{3, 0, 1};
        System.out.println(missingNumber.missingNumber(nums));

        nums = new int[]{0, 1};
        System.out.println(missingNumber.missingNumber(nums));

        nums = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumber.missingNumber(nums));
    }
}
