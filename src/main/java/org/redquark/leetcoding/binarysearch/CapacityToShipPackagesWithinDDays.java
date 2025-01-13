package org.redquark.leetcoding.binarysearch;

public class CapacityToShipPackagesWithinDDays {

    public int shipWithinDays(int[] weights, int days) {
        // Special case
        if (weights == null || weights.length == 0) {
            return 0;
        }
        // Left and right bounds will be max weight and sum of all weights
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        // Process weights array from both ends
        while (left < right) {
            final int middle = left + (right - left) / 2;
            // Time taken to carry all weights for currentCapacity
            int time = 1;
            // Current weight
            int currentWeight = 0;
            for (int weight : weights) {
                if (currentWeight + weight > middle) {
                    time++;
                    currentWeight = 0;
                }
                currentWeight += weight;
            }
            if (time > days) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        final CapacityToShipPackagesWithinDDays capacityToShipPackagesWithinDDays = new CapacityToShipPackagesWithinDDays();

        int[] weights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        System.out.println(capacityToShipPackagesWithinDDays.shipWithinDays(weights, days));

        weights = new int[]{3, 2, 2, 4, 1, 4};
        days = 3;
        System.out.println(capacityToShipPackagesWithinDDays.shipWithinDays(weights, days));

        weights = new int[]{1, 2, 3, 1, 1};
        days = 4;
        System.out.println(capacityToShipPackagesWithinDDays.shipWithinDays(weights, days));
    }
}
