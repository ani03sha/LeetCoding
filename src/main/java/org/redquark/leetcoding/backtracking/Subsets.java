package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        // List to store the subsets of the array
        final List<List<Integer>> subsets = new ArrayList<>();
        // Special case
        if (nums == null || nums.length == 0) {
            return subsets;
        }
        // Perform backtracking
        backtrack(nums, 0, new ArrayList<>(), subsets);
        return subsets;
    }

    private void backtrack(int[] nums, int index, List<Integer> subset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(subset));
        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            backtrack(nums, i + 1, subset, subsets);
            subset.removeLast();
        }
    }

    public static void main(String[] args) {
        final Subsets subsets = new Subsets();

        int[] nums = new int[]{1, 2, 3};
        System.out.println(subsets.subsets(nums));

        nums = new int[]{0};
        System.out.println(subsets.subsets(nums));
    }
}
