package org.redquark.leetcoding.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        // Special case
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // Sort the array based on the first element in ascending order, but if the first elements are equal,
        // it sorts the second element in descending order
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });
        // Now, we need to perform LIS using binary search on height
        final List<Integer> lis = new ArrayList<>();
        for (int[] envelope : envelopes) {
            final int height = envelope[1];
            // Find the correct position of this height the list
            int index = Collections.binarySearch(lis, height);
            // If the element is not present in the list
            if (index < 0) {
                index = -(index + 1);
            }
            // Insert the height at its correct position
            if (lis.size() == index) {
                lis.add(height);
            } else {
                lis.set(index, height);
            }
        }
        return lis.size();
    }

    public static void main(String[] args) {
        final RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();

        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(russianDollEnvelopes.maxEnvelopes(envelopes));

        envelopes = new int[][]{{1, 1}, {1, 1}, {1, 1}};
        System.out.println(russianDollEnvelopes.maxEnvelopes(envelopes));
    }
}
