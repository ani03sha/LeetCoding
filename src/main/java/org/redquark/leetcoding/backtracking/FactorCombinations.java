package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {

    public List<List<Integer>> getFactors(int n) {
        // List to store factors
        final List<List<Integer>> allFactors = new ArrayList<>();
        // Perform backtracking to find factors
        backtrack(n, 2, new ArrayList<>(), allFactors);
        return allFactors;
    }

    private void backtrack(int n, int index, List<Integer> currentFactors, List<List<Integer>> allFactors) {
        // If the currentFactors is not empty, it means we have a valid
        // combination of factors
        if (!currentFactors.isEmpty()) {
            final List<Integer> factors = new ArrayList<>(currentFactors);
            // Add the remaining 'n' to the combination
            factors.add(n);
            allFactors.add(new ArrayList<>(factors));
        }
        // We only need to check combinations up to sqrt(n)
        for (int i = index; i * i <= n; i++) {
            // If the current element is the factor of n
            if (n % i == 0) {
                currentFactors.add(i);
                backtrack(n / i, i, currentFactors, allFactors);
                currentFactors.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        final FactorCombinations factorCombinations = new FactorCombinations();

        System.out.println(factorCombinations.getFactors(1));
        System.out.println(factorCombinations.getFactors(12));
        System.out.println(factorCombinations.getFactors(37));
    }
}
