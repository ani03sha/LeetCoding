package org.redquark.leetcoding.binarysearch;

public class SingleElementInASortedArray {

    public int singleNonDuplicate(int[] nums) {
        // Left and right pointers for binary search
        int left = 0;
        int right = nums.length - 1;
        // Perform binary search
        while (left < right) {
            final int middle = left + (right - left) / 2;
            // Check if the middle is even or odd
            if ((middle % 2 == 0 && nums[middle] == nums[middle + 1])
                    || (middle % 2 == 1 && nums[middle - 1] == nums[middle])) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        final SingleElementInASortedArray singleElementInASortedArray = new SingleElementInASortedArray();

        int[] nums = new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println(singleElementInASortedArray.singleNonDuplicate(nums)); // 2

        nums = new int[]{3, 3, 7, 7, 10, 11, 11};
        System.out.println(singleElementInASortedArray.singleNonDuplicate(nums)); // 10
    }
}
