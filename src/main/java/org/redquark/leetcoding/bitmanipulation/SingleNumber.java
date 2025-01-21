package org.redquark.leetcoding.bitmanipulation;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }
        return xor;
    }

    public static void main(String[] args) {
        final SingleNumber singleNumber = new SingleNumber();

        int[] nums = new int[]{2, 2, 1};
        System.out.println(singleNumber.singleNumber(nums));

        nums = new int[]{4, 1, 2, 1, 2};
        System.out.println(singleNumber.singleNumber(nums));

        nums = new int[]{1};
        System.out.println(singleNumber.singleNumber(nums));
    }
}
