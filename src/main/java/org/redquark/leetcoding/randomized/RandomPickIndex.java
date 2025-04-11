package org.redquark.leetcoding.randomized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomPickIndex {

    // Map to store the elements and list of indices
    // where it occurs
    private final Map<Integer, List<Integer>> mappings;
    private final Random random;

    public RandomPickIndex(int[] nums) {
        this.mappings = new HashMap<>();
        this.random = new Random();
        // Add nums to the mappings
        for (int i = 0; i < nums.length; i++) {
            this.mappings.putIfAbsent(nums[i], new ArrayList<>());
            mappings.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        // Check if target is present in the array
        if (!this.mappings.containsKey(target)) {
            return -1;
        }
        // Get the indices at which current number exists
        final List<Integer> indices = this.mappings.get(target);
        return indices.get(this.random.nextInt(indices.size()));
    }

    public static void main(String[] args) {
        final int[] nums = new int[]{1, 2, 3, 3, 3};
        final RandomPickIndex randomPickIndex = new RandomPickIndex(nums);
        System.out.println(randomPickIndex.pick(3));
        System.out.println(randomPickIndex.pick(1));
        System.out.println(randomPickIndex.pick(3));
    }
}
