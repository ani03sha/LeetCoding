package org.redquark.leetcoding.binarysearch;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Make sure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // Lengths of both arrays
        final int x = nums1.length;
        final int y = nums2.length;
        // Left and right pointers
        int left = 0;
        int right = x;
        // Perform binary search on the smaller array
        while (left <= right) {
            // Find indices where nums1 and nums2 will be partitioned
            int partitionX = left + (right - left) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;
            // Elements at which both arrays will be partitioned
            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];
            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE : nums2[partitionY];
            // Check if we have found the right partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // For even lengths of combined array
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY) * 1.0;
                }
            }
            // If we are too far on the right side
            else if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }
        // Should never reach here
        throw new IllegalArgumentException("Arrays are not sorted!");
    }

    public static void main(String[] args) {
        final MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();

        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println(medianOfTwoSortedArrays.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println(medianOfTwoSortedArrays.findMedianSortedArrays(nums1, nums2));
    }
}
