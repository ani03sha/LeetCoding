package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // List to store all the combinations
        final List<List<Integer>> combinations = new ArrayList<>();
        // Special case
        if (candidates == null || candidates.length == 0) {
            return combinations;
        }
        // Sort the array
        Arrays.sort(candidates);
        // Perform backtracking
        backtrack(candidates, target, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void backtrack(int[] candidates, int target, int index, List<Integer> combination,
                           List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // Skip duplicates and invalid candidates
            if ((i > index && candidates[i] == candidates[i - 1]) || candidates[i] > target) {
                continue;
            }
            combination.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, combination, combinations);
            combination.removeLast();
        }
    }

    public static void main(String[] args) {
        final CombinationSumII combinationSumII = new CombinationSumII();

        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(combinationSumII.combinationSum2(candidates, target));

        candidates = new int[]{2, 5, 2, 1, 2};
        target = 5;
        System.out.println(combinationSumII.combinationSum2(candidates, target));
    }
}
