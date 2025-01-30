package org.redquark.leetcoding.arrays;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Set to store all elements in the array
        final Set<Integer> elements = new HashSet<>();
        for (int num : nums) {
            elements.add(num);
        }
        // Longest consecutive sequence length
        int lcsLength = 0;
        // Process all elements in the array
        for (int element : elements) {
            // Current element
            int currentElement = element;
            // If previous element to current element is present in the array,
            // it means current element is a part of an ongoing sequence.
            // If not, it means it is the first element of a sequence
            if (!elements.contains(currentElement - 1)) {
                // Current length
                int currentLength = 1;
                while (elements.contains(currentElement + 1)) {
                    currentElement++;
                    currentLength++;
                }
                // Update longest length, if required
                lcsLength = Math.max(lcsLength, currentLength);
            }
        }
        return lcsLength;
    }

    public static void main(String[] args) {
        final LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();

        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutiveSequence.longestConsecutive(nums));

        nums = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutiveSequence.longestConsecutive(nums));
    }
}
