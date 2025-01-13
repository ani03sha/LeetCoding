package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        // List to store all combinations
        final List<List<Integer>> combinations = new ArrayList<>();
        // Special case
        if (n < k) {
            return combinations;
        }
        // Perform backtracking
        backtrack(n, k, 1, new ArrayList<>(), combinations);
        return combinations;
    }

    private void backtrack(int n, int k, int index, List<Integer> combination, List<List<Integer>> combinations) {
        if (combination.size() == k) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i <= n; i++) {
            combination.add(i);
            backtrack(n, k, i + 1, combination, combinations);
            combination.removeLast();
        }
    }

    public static void main(String[] args) {
        final Combinations combinations = new Combinations();

        System.out.println(combinations.combine(4, 2));
        System.out.println(combinations.combine(1, 1));
    }
}
