package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        // List to store the unique permutations
        final List<List<Integer>> permutations = new ArrayList<>();
        // Special case
        if (nums == null || nums.length == 0) {
            return permutations;
        }
        Arrays.sort(nums);
        // Array to mark visited elements
        final boolean[] visited = new boolean[nums.length];
        // Perform backtracking
        backtrack(nums, new ArrayList<>(), permutations, visited);
        return permutations;
    }

    private void backtrack(int[] nums, List<Integer> permutation, List<List<Integer>> permutations, boolean[] visited) {
        if (permutation.size() == nums.length) {
            permutations.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            backtrack(nums, permutation, permutations, visited);
            visited[i] = false;
            permutation.removeLast();
        }
    }

    public static void main(String[] args) {
        final PermutationsII permutationsII = new PermutationsII();

        int[] nums = new int[]{1, 1, 2};
        System.out.println(permutationsII.permuteUnique(nums));

        nums = new int[]{1, 2, 3};
        System.out.println(permutationsII.permuteUnique(nums));
    }
}
