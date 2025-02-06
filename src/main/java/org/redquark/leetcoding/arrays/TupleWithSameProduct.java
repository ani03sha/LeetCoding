package org.redquark.leetcoding.arrays;

import java.util.HashMap;
import java.util.Map;

public class TupleWithSameProduct {

    public int tupleSameProduct(int[] nums) {
        final int n = nums.length;
        // Map to store the frequencies of the product of two numbers
        final Map<Integer, Integer> productFrequencies = new HashMap<>();
        // Total number of tuples
        int count = 0;
        // Process all pairs
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                final int product = nums[i] * nums[j];
                // Each matching pair (a, b) and (c, d) can be arranged
                // as (a, b, c, d), (b, a, c, d), (a, b, d, c), (b, a, c, d),
                // (b, a, d, c), (c, d, a, b), (d, c, a, b), (c, d, b, a)
                count += 8 * productFrequencies.getOrDefault(product, 0);
                // Update the product frequency
                productFrequencies.put(product, productFrequencies.getOrDefault(product, 0) + 1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final TupleWithSameProduct tupleWithSameProduct = new TupleWithSameProduct();

        int[] nums = new int[]{2, 3, 4, 6};
        System.out.println(tupleWithSameProduct.tupleSameProduct(nums));

        nums = new int[]{1, 2, 4, 5, 10};
        System.out.println(tupleWithSameProduct.tupleSameProduct(nums));
    }
}
