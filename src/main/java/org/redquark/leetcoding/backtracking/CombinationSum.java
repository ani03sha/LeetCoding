package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // List to store all the combination sums
        final List<List<Integer>> combinations = new ArrayList<>();
        // Special case
        if (candidates == null || candidates.length == 0) {
            return combinations;
        }
        Arrays.sort(candidates);
        // Perform backtracking
        backtrack(candidates, target, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void backtrack(int[] candidates, int target, int index, List<Integer> combination, List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                combination.add(candidates[i]);
                backtrack(candidates, target - candidates[i], i, combination, combinations);
                combination.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        final CombinationSum combinationSum = new CombinationSum();

        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum.combinationSum(candidates, target));

        candidates = new int[]{2, 3, 5};
        target = 8;
        System.out.println(combinationSum.combinationSum(candidates, target));

        candidates = new int[]{2};
        target = 1;
        System.out.println(combinationSum.combinationSum(candidates, target));
    }
}
