package org.redquark.leetcoding.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {

    public int totalFruit(int[] fruits) {
        // Special case
        if (fruits == null || fruits.length == 0) {
            return 0;
        }
        final int n = fruits.length;
        // Left and right pointers of the sliding window
        int left = 0;
        int right = 0;
        // Set to store only two types of fruits
        final Map<Integer, Integer> types = new HashMap<>();
        // Maximum fruits collected
        int maxCount = 0;
        // Process the array
        while (right < n) {
            // Add current fruit to the map
            final int rightFruit = fruits[right];
            types.put(rightFruit, types.getOrDefault(rightFruit, 0) + 1);
            // At any point, if there are more than three fruits in the basket,
            // we start removing from the left
            if (types.size() > 2) {
                final int leftFruit = fruits[left];
                types.put(leftFruit, types.get(leftFruit) - 1);
                if (types.get(leftFruit) == 0) {
                    types.remove(leftFruit);
                }
                left++;
            }
            maxCount = Math.max(maxCount, right - left + 1);
            right++;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        final FruitIntoBaskets fruitIntoBaskets = new FruitIntoBaskets();

        int[] nums = new int[]{1, 2, 1};
        System.out.println(fruitIntoBaskets.totalFruit(nums));

        nums = new int[]{0, 1, 2, 2};
        System.out.println(fruitIntoBaskets.totalFruit(nums));

        nums = new int[]{1, 2, 3, 2, 2};
        System.out.println(fruitIntoBaskets.totalFruit(nums));
    }
}
