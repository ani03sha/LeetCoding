package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class VerbalArithmeticPuzzle {

    public boolean isSolvable(String[] words, String result) {
        // We know that in an equation, every term/letter has its coefficient,
        // thus, we will maintain an array of coefficients of all the letters
        // in the equation - including LHS and RHS
        final int[] coefficients = new int[26];
        // The First letters of words can't be zero, so we need to track them
        final boolean[] nonZero = new boolean[26];
        // Build coefficients for every character from LHS
        for (String word : words) {
            final int n = word.length();
            // Mark the first letter of the word as non-zero
            if (n > 1) {
                nonZero[word.charAt(0) - 'A'] = true;
            }
            for (int i = 0; i < n; i++) {
                final int power = (int) Math.pow(10, n - i - 1);
                coefficients[word.charAt(i) - 'A'] += power;
            }
        }
        // Now, use the same coefficients map to subtract cost of RHS (LHS - RHS = 0)
        final int n = result.length();
        if (n > 1) {
            nonZero[result.charAt(0) - 'A'] = true;
        }
        for (int i = 0; i < n; i++) {
            final int power = (int) Math.pow(10, n - i - 1);
            coefficients[result.charAt(i) - 'A'] -= power;
        }
        // Extract unique characters involved in the equation
        final List<Integer> uniqueLetters = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (coefficients[i] != 0) {
                uniqueLetters.add(i);
            }
        }
        // Now, we have to assign digits to letters, so we do this
        // using backtracking
        return backtrack(uniqueLetters, 0, 0, coefficients, nonZero, new boolean[10]);
    }

    /**
     * Recursive function to assign digits to letters and evaluate the sum.
     *
     * @param uniqueLetters List of letter indices (A=0 to Z=25)
     * @param index         Current letter index in the list to assign a digit to
     * @param sum           Accumulated sum using coefficients and current digit assignments
     * @param coefficients  Coefficient array for each letter
     * @param nonZero       Flags for letters that cannot be assigned 0
     * @param used          Flags for digits already used (0-9)
     * @return True if a valid assignment found; false otherwise
     */
    private boolean backtrack(List<Integer> uniqueLetters, int index, int sum, int[] coefficients, boolean[] nonZero, boolean[] used) {
        // Base case
        if (index == uniqueLetters.size()) {
            return sum == 0;
        }
        final int c = uniqueLetters.get(index);
        // Try assigning every unused digit to this character
        for (int digit = 0; digit < 10; digit++) {
            if (used[digit]) {
                continue;
            }
            if (digit == 0 && nonZero[c]) {
                continue;
            }
            used[digit] = true;
            if (backtrack(uniqueLetters, index + 1, sum + coefficients[c] * digit, coefficients, nonZero, used)) {
                return true;
            }
            used[digit] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        final VerbalArithmeticPuzzle verbalArithmeticPuzzle = new VerbalArithmeticPuzzle();

        String[] words1 = {"SEND", "MORE"};
        String result1 = "MONEY";
        System.out.println(verbalArithmeticPuzzle.isSolvable(words1, result1)); // Output: true

        String[] words2 = {"LEET", "CODE"};
        String result2 = "POINT";
        System.out.println(verbalArithmeticPuzzle.isSolvable(words2, result2)); // Output: false

        String[] words3 = {"SIX", "SEVEN", "SEVEN"};
        String result3 = "TWENTY";
        System.out.println(verbalArithmeticPuzzle.isSolvable(words3, result3)); // Output: true
    }
}
