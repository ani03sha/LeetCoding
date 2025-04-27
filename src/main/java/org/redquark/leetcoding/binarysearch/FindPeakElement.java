package org.redquark.leetcoding.binarysearch;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        // Left and right pointers for binary search
        int left = 0;
        int right = nums.length - 1;
        // Perform binary search
        while (left <= right) {
            final int middle = left + (right - left) / 2;
            // If middle is either at the boundary of array or it is greater
            // than its neighboring elements, we return it
            if ((middle == nums.length - 1 || nums[middle + 1] < nums[middle]) && (middle == 0 || nums[middle - 1] < nums[middle])) {
                return middle;
            } else if (nums[middle + 1] > nums[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final FindPeakElement findPeakElement = new FindPeakElement();

        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(findPeakElement.findPeakElement(nums));

        nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement.findPeakElement(nums));
    }
}
