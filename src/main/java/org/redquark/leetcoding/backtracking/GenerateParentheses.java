package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        // List to store all combinations
        final List<String> combinations = new ArrayList<>();
        // Special case
        if (n <= 0) {
            return combinations;
        }
        // Perform backtracking
        backtrack(n, 0, 0, new StringBuilder(), combinations);
        return combinations;
    }

    private void backtrack(int n, int left, int right, StringBuilder combination, List<String> combinations) {
        if (combination.length() == 2 * n) {
            combinations.add(combination.toString());
            return;
        }
        if (left < n) {
            combination.append("(");
            backtrack(n, left + 1, right, combination, combinations);
            combination.deleteCharAt(combination.length() - 1);
        }
        if (right < left) {
            combination.append(")");
            backtrack(n, left, right + 1, combination, combinations);
            combination.deleteCharAt(combination.length() - 1);
        }
    }

    public static void main(String[] args) {
        final GenerateParentheses generateParentheses = new GenerateParentheses();

        System.out.println(generateParentheses.generateParenthesis(3));
        System.out.println(generateParentheses.generateParenthesis(1));
    }
}
