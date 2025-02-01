package org.redquark.leetcoding.arrays;

public class SpecialArrayI {

    public boolean isArraySpecial(int[] nums) {
        // Special case
        if (nums == null || nums.length < 2) {
            return true;
        }
        // Pointers to keep track of adjacent elements
        int i = 0;
        int j = 1;
        // Process elements in the array
        while (j < nums.length) {
            // Sum of two odd numbers or two even numbers will
            // always be even
            if ((nums[i] + nums[j]) % 2 == 0) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        final SpecialArrayI specialArrayI = new SpecialArrayI();

        int[] nums = new int[]{1};
        System.out.println(specialArrayI.isArraySpecial(nums));

        nums = new int[]{2, 1, 4};
        System.out.println(specialArrayI.isArraySpecial(nums));

        nums = new int[]{4, 3, 1, 6};
        System.out.println(specialArrayI.isArraySpecial(nums));
    }
}
