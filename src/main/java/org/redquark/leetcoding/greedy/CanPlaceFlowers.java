package org.redquark.leetcoding.greedy;

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int k) {
        // Special case
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }
        final int n = flowerbed.length;
        // Process the flowerbed greedily
        for (int i = 0; i < n; i++) {
            // Flower can only be planted if the current spot and its left and
            // right adjacent spots are empty
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == n - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                k--;
            }
        }
        return k <= 0;
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
