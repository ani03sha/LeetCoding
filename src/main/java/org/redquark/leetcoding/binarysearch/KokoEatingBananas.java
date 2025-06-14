package org.redquark.leetcoding.binarysearch;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        // Special case
        if (piles == null || piles.length == 0) {
            return 0;
        }
        // Find maximum bananas in all piles
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        // Now, Koko can eat a minimum of 1 banana and maximum of "max" bananas
        int left = 1;
        int right = max;
        // Minimum rate at which Koko needs to eat
        int minRate = max;
        // Process bananas in the piles
        while (left <= right) {
            final int currentRate = left + (right - left) / 2;
            // Time taken to eat all bananas
            int time = 0;
            for (int pile : piles) {
                time += Math.ceil(1.0 * pile / currentRate);
            }
            if (time <= h) {
                minRate = currentRate;
                right = currentRate - 1;
            } else {
                left = currentRate + 1;
            }
        }
        return minRate;
    }

    public static void main(String[] args) {
        final KokoEatingBananas kokoEatingBananas = new KokoEatingBananas();

        int[] piles = new int[]{3, 6, 7, 11};
        int h = 8;
        System.out.println(kokoEatingBananas.minEatingSpeed(piles, h));

        piles = new int[]{30, 11, 23, 4, 20};
        h = 5;
        System.out.println(kokoEatingBananas.minEatingSpeed(piles, h));

        piles = new int[]{30, 11, 23, 4, 20};
        h = 6;
        System.out.println(kokoEatingBananas.minEatingSpeed(piles, h));
    }
}
