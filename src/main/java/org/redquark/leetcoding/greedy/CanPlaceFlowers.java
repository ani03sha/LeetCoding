package org.redquark.leetcoding.greedy;

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int k) {
        // Special case
        if (flowerbed == null || flowerbed.length < k) {
            return false;
        }
        final int n = flowerbed.length;
        // Count of empty spaces
        int emptySpaces = 0;
        // Process the array greedily
        for (int i = 0; i < n; i++) {
            // If current plot is empty
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == n - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                emptySpaces++;
            }
        }
        return emptySpaces >= k;
    }

    public static void main(String[] args) {
        final CanPlaceFlowers canPlaceFlowers = new CanPlaceFlowers();

        int[] flowerbed = new int[]{1, 0, 0, 0, 1};
        int k = 1;
        System.out.println(canPlaceFlowers.canPlaceFlowers(flowerbed, k));

        flowerbed = new int[]{1, 0, 0, 0, 1};
        k = 2;
        System.out.println(canPlaceFlowers.canPlaceFlowers(flowerbed, k));
    }
}
