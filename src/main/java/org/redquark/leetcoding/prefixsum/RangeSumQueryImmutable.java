package org.redquark.leetcoding.prefixsum;

public class RangeSumQueryImmutable {

    static class NumArray {

        private final int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            // Modify the array to store prefix sum
            for (int i = 1; i < nums.length; i++) {
                this.nums[i] += this.nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            if (left == 0) {
                return this.nums[right];
            }
            return this.nums[right] - this.nums[left - 1];
        }
    }

    public static void main(String[] args) {
        final int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        final NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
