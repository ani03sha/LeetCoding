package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        // List to store all permutations
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

    public List<List<Integer>> permuteOptimized(int[] nums) {
        // List to store final output
        final List<List<Integer>> permutations = new ArrayList<>();
        // Special case
        if (nums == null || nums.length == 0) {
            return permutations;
        }
        // Perform backtracking
        backtrack(nums, 0, permutations);
        return permutations;
    }

    private void backtrack(int[] nums, int index, List<List<Integer>> permutations) {
        // Base case
        if (index == nums.length) {
            // Create a list with the current permutation of nums
            final List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            permutations.add(permutation);
            return;
        }
        // Create combinations of numbers
        for (int i = index; i < nums.length; i++) {
            // Swap elements at i and index
            swap(nums, i, index);
            // Backtrack with next index
            backtrack(nums, index + 1, permutations);
            // Re-swap elements i and index
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        final Permutations permutations = new Permutations();

        int[] nums = new int[]{1, 2, 3};
        System.out.println(permutations.permute(nums));
        System.out.println(permutations.permuteOptimized(nums));

        nums = new int[]{0, 1};
        System.out.println(permutations.permute(nums));
        System.out.println(permutations.permuteOptimized(nums));

        nums = new int[]{1};
        System.out.println(permutations.permute(nums));
        System.out.println(permutations.permuteOptimized(nums));
    }
}
