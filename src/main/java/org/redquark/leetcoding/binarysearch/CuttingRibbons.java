package org.redquark.leetcoding.binarysearch;

public class CuttingRibbons {

    public int maxLength(int[] ribbons, int k) {
        // Special case
        if (ribbons == null || ribbons.length == 0) {
            return 0;
        }
        int left = 1;
        // Max length among all ribbon because we cannot cut
        // a ribbon of negative length
        int right = 1;
        for (int ribbon : ribbons) {
            right = Math.max(right, ribbon);
        }
        // Total number of cut ribbons
        int result = 0;
        // Perform binary search
        while (left <= right) {
            int count = 0;
            final int middle = left + (right - left) / 2;
            // Now, try this ribbon length for all given ribbons
            for (int ribbon : ribbons) {
                count += ribbon / middle;
            }
            if (count >= k) {
                result = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final CuttingRibbons cuttingRibbons = new CuttingRibbons();

        int[] ribbons = new int[]{9, 7, 5};
        int k = 3;
        System.out.println(cuttingRibbons.maxLength(ribbons, k));

        ribbons = new int[]{7, 5, 9};
        k = 4;
        System.out.println(cuttingRibbons.maxLength(ribbons, k));

        ribbons = new int[]{5, 7, 9};
        k = 22;
        System.out.println(cuttingRibbons.maxLength(ribbons, k));
    }
}
