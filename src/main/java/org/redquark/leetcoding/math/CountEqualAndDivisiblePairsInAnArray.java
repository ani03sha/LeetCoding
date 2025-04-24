package org.redquark.leetcoding.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountEqualAndDivisiblePairsInAnArray {

    /**
     * This is a brute force solution but it is accepted
     */
    public int countPairsBruteForce(int[] nums, int k) {
        final int n = nums.length;
        int count = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countPairsOptimal(int[] nums, int k) {
        final int n = nums.length;
        // Map to store same elements and their indices
        final Map<Integer, List<Integer>> mappings = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mappings.computeIfAbsent(nums[i], _ -> new ArrayList<>()).add(i);
        }
        // List to store the divisors of k
        final List<Integer> kDivisors = new ArrayList<>();
        for (int i = 1; i * i <= k; i++) {
            if (k % i == 0) {
                kDivisors.add(i);
                if (i != k / i) {
                    kDivisors.add(k / i);
                }
            }
        }
        // Count of pairs
        int pairs = 0;
        // Process entries in the map
        for (Map.Entry<Integer, List<Integer>> entry : mappings.entrySet()) {
            // Map to store count of divisors of k for index
            final Map<Integer, Integer> kDivisorCounts = new HashMap<>();
            final List<Integer> indices = entry.getValue();
            for (int index : indices) {
                // Get GCD of index and k
                final int gcd = getGCD(index, k);
                int need = k / gcd;
                pairs += kDivisorCounts.getOrDefault(need, 0);
                // Update map for kDivisors
                for (int kDivisor : kDivisors) {
                    if (index % kDivisor == 0) {
                        kDivisorCounts.put(kDivisor, kDivisorCounts.getOrDefault(kDivisor, 0) + 1);
                    }
                }
            }
        }
        return pairs;
    }

    private int getGCD(int a, int b) {
        return b == 0 ? a : getGCD(b, a % b);
    }

    public static void main(String[] args) {
        final CountEqualAndDivisiblePairsInAnArray countEqualAndDivisiblePairsInAnArray = new CountEqualAndDivisiblePairsInAnArray();

        int[] nums = new int[]{3,1,2,2,2,1,3};
        int k = 2;
        System.out.println(countEqualAndDivisiblePairsInAnArray.countPairsBruteForce(nums, k));
        System.out.println(countEqualAndDivisiblePairsInAnArray.countPairsOptimal(nums, k));

        nums = new int[]{1,2,3,4};
        k = 1;
        System.out.println(countEqualAndDivisiblePairsInAnArray.countPairsBruteForce(nums, k));
        System.out.println(countEqualAndDivisiblePairsInAnArray.countPairsOptimal(nums, k));
    }
}
