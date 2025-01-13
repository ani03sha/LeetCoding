package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        // List to store all permutation
        final List<List<Integer>> permutations = new ArrayList<>();
        // Special case
        if (nums == null || nums.length == 0) {
            return permutations;
        }
        // Perform backtracking
        backtrack(nums, new ArrayList<>(), permutations);
        return permutations;
    }

    private void backtrack(int[] nums, List<Integer> permutation, List<List<Integer>> permutations) {
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        for (int num : nums) {
            if (permutation.contains(num)) {
                continue;
            }
            permutation.add(num);
            backtrack(nums, permutation, permutations);
            permutation.removeLast();
        }
    }

    public static void main(String[] args) {
        final Permutations permutations = new Permutations();

        int[] nums = new int[]{1, 2, 3};
        System.out.println(permutations.permute(nums));

        nums = new int[]{0, 1};
        System.out.println(permutations.permute(nums));

        nums = new int[]{1};
        System.out.println(permutations.permute(nums));
    }
}
