package org.redquark.leetcoding.twopointers;

import java.util.Arrays;

public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers to keep track of elements in the arrays
        int i = m - 1;
        int j = n - 1;
        // Index of the final array
        int index = m + n - 1;
        // Process both arrays from right to left
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[index] = nums1[i];
                i--;
            } else {
                nums1[index] = nums2[j];
                j--;
            }
            index--;
        }
        // For remaining elements, if any
        while (i >= 0) {
            nums1[index] = nums1[i];
            i--;
            index--;
        }
        while (j >= 0) {
            nums1[index] = nums2[j];
            j--;
            index--;
        }
    }

    public static void main(String[] args) {
        final MergeSortedArray mergeSortedArray = new MergeSortedArray();

        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        mergeSortedArray.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));

        nums1 = new int[]{1};
        m = 1;
        nums2 = new int[]{};
        n = 0;
        mergeSortedArray.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
