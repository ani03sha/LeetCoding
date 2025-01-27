package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        // Special case
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        // Make sure always nums1 is of bigger length
        if (nums1.length < nums2.length) {
            return intersection(nums2, nums1);
        }
        // Set to store all elements in the bigger array
        final Set<Integer> allElements = new HashSet<>();
        for (int num : nums1) {
            allElements.add(num);
        }
        // Set to store only common elements
        final Set<Integer> commonElements = new HashSet<>();
        // Process all elements in nums2
        for (int num : nums2) {
            if (allElements.contains(num)) {
                commonElements.add(num);
            }
        }
        // Convert set to array
        int[] result = new int[commonElements.size()];
        int index = 0;
        for (int commonElement : commonElements) {
            result[index] = commonElement;
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        final IntersectionOfTwoArrays intersectionOfTwoArrays = new IntersectionOfTwoArrays();

        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        System.out.println(Arrays.toString(intersectionOfTwoArrays.intersection(nums1, nums2)));

        nums1 = new int[]{4, 9, 5};
        nums2 = new int[]{9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersectionOfTwoArrays.intersection(nums1, nums2)));
    }
}
