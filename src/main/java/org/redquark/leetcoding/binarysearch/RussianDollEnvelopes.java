package org.redquark.leetcoding.binarysearch;

import java.util.Arrays;

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        // Special case
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // Sort the array based on width in ascending order,
        // if widths are same, sort it based on the height
        // in the descending order
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        // Perform LIS on the second dimension of the envelopes
        final int[] lookup = new int[envelopes.length];
        // Total number of envelopes that can be inserted
        int length = 0;
        for (int[] envelope : envelopes) {
            // Find the correct position of the current envelope's
            // height to be inserted
            int index = Arrays.binarySearch(lookup, 0, length, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            lookup[index] = envelope[1];
            // If the list is already sorted, increase the count
            if (index == length) {
                length++;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        final RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();

        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(russianDollEnvelopes.maxEnvelopes(envelopes));

        envelopes = new int[][]{{1, 1}, {1, 1}, {1, 1}};
        System.out.println(russianDollEnvelopes.maxEnvelopes(envelopes));
    }
}
