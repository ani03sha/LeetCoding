package org.redquark.leetcoding.binarysearch;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        // Left and right pointers for the binary search
        int left = 0;
        int right = nums.length - 1;
        // Perform binary search
        while (left < right) {
            final int middle = left + (right - left) / 2;
            // If the peak lies to the right of the middle element
            if (nums[middle] < nums[middle + 1]) {
                left = middle + 1;
            }
            // If the peak exists to the left of the middle
            else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        final FindPeakElement findPeakElement = new FindPeakElement();

        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(findPeakElement.findPeakElement(nums));

        nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement.findPeakElement(nums));
    }
}
