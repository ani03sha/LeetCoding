package org.redquark.leetcoding.binarysearch;

import java.util.Arrays;

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        // Special case
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // Sort the envelopes by their widths in the following way
        // 1. If the widths of two envelopes are equal, sort them in decreasing
        //    order of their heights
        // 2. If the widths of two envelopes are different, sort them in a
        //    straightforward way
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        // LIS length
        int lis = 0;
        // Lookup table to store the LIS
        final int[] lookup = new int[envelopes.length];
        // Process all envelopes
        for (int[] envelope : envelopes) {
            // Find the index of current envelope in LIS
            int index = Arrays.binarySearch(lookup, 0, lis, envelope[1]);
            // If this element is greater than everything else in
            // the LIS array
            if (index < 0) {
                index = -(index + 1);
            }
            // Set the element at the index
            lookup[index] = envelope[1];
            if (index == lis) {
                lis++;
            }
        }
        return lis;
    }

    public static void main(String[] args) {
        final RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();

        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(russianDollEnvelopes.maxEnvelopes(envelopes));

        envelopes = new int[][]{{1, 1}, {1, 1}, {1, 1}};
        System.out.println(russianDollEnvelopes.maxEnvelopes(envelopes));
    }
}
