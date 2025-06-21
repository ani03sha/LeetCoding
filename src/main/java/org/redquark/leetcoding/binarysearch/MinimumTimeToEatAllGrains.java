package org.redquark.leetcoding.binarysearch;

import java.util.Arrays;

public class MinimumTimeToEatAllGrains {

    public int minimumTime(int[] hens, int[] grains) {
        // Sort both the arrays
        Arrays.sort(hens);
        Arrays.sort(grains);
        // Left and right pointers for binary search
        int left = 0;
        int right = 1_500_000_000; // A large enough value to ensure we cover all possible times
        // Perform binary search to find the minimum time
        while (left <= right) {
            final int allowedTime = left + (right - left) / 2;
            if (check(hens, grains, allowedTime)) {
                right = allowedTime - 1;
            } else {
                left = allowedTime + 1;
            }
        }
        return left;
    }

    public boolean check(int[] hens, int[] grains, int time) {
        // Lengths of hens and grains arrays
        int n = hens.length;
        int m = grains.length;
        // Pointers for hens and grains
        int i = 0;
        int j = 0;
        // The index "i" is a pointer for current leftmost hen in the "hens" array.
        // The index "j" is a pointer for the current leftmost uneaten grain in the "grains" array.
        // Iterate until we reach the end of either hens or grains
        while (i < n && j < m) {
            // Current hen at index "i" will travel from [henLeft, henRight].
            int henLeft, henRight;
            // If there is a grain to the of current hen, it will have to eat this grain.
            if (grains[j] < hens[i]) {
                // Time left after going to the left for this grain
                int timeLeft = time - (hens[i] - grains[j]);
                // If the grain is too far, timeLeft will be negative,
                // it is not possible to eat this grain in given time.
                if (timeLeft < 0) {
                    return false;
                }
                henLeft = grains[j];
                // Hen can first go left, or go right and come back to left.
                henRight = Math.max(henLeft + timeLeft, hens[i] + timeLeft / 2);
            }
            // If there is no grain to left, just go right.
            else {
                henLeft = hens[i];
                henRight = hens[i] + time;
            }
            // Consume all grains in the range [henLeft, henRight].
            while (j < m && grains[j] >= henLeft && grains[j] <= henRight) {
                j++;
            }
            i++;
        }
        // Return true if all grains have been consumed.
        return j == m;
    }

    public static void main(String[] args) {
        final MinimumTimeToEatAllGrains minimumTimeToEatAllGrains = new MinimumTimeToEatAllGrains();

        int[] hens = {3, 6, 7};
        int[] grains = {2, 4, 7, 9};
        System.out.println(minimumTimeToEatAllGrains.minimumTime(hens, grains)); // 2

        hens = new int[]{4, 6, 109, 111, 213, 215};
        grains = new int[]{5, 110, 214};
        System.out.println(minimumTimeToEatAllGrains.minimumTime(hens, grains)); // 1
    }
}
