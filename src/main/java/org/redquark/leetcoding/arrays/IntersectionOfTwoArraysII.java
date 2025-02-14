package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        // Make sure nums1 always has the bigger length
        if (nums1.length < nums2.length) {
            return intersect(nums2, nums1);
        }
        // Map to store the frequencies of elements in nums2
        final Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums1) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }
        // List to store common elements
        final List<Integer> commonElements = new ArrayList<>();
        // Traverse through nums2 and find the common elements
        for (int num : nums2) {
            if (frequencies.containsKey(num)) {
                commonElements.add(num);
                frequencies.put(num, frequencies.get(num) - 1);
                if (frequencies.get(num) == 0) {
                    frequencies.remove(num);
                }
            }
        }
        final int[] result = new int[commonElements.size()];
        int index = 0;
        for (int commonElement : commonElements) {
            result[index] = commonElement;
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        final IntersectionOfTwoArraysII intersectionOfTwoArraysII = new IntersectionOfTwoArraysII();

        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        System.out.println(Arrays.toString(intersectionOfTwoArraysII.intersect(nums1, nums2)));

        nums1 = new int[]{4, 9, 5};
        nums2 = new int[]{9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersectionOfTwoArraysII.intersect(nums1, nums2)));
    }
}
