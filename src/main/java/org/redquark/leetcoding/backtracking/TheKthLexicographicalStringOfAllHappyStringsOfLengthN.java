package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class TheKthLexicographicalStringOfAllHappyStringsOfLengthN {

    public String getHappyString(int n, int k) {
        // Set of all available characters
        final char[] availableCharacters = {'a', 'b', 'c'};
        // List to store all happy strings
        final List<String> happyStrings = new ArrayList<>();
        // Create all happy strings
        backtrack(n, availableCharacters, new StringBuilder(), happyStrings);
        if (happyStrings.size() >= k) {
            return happyStrings.get(k - 1);
        }
        return "";
    }

    private void backtrack(int n, char[] availableCharacters, StringBuilder current, List<String> happyStrings) {
        // Base case
        if (current.length() == n) {
            happyStrings.add(current.toString());
            return;
        }
        // Check for all three characters
        for (int i = 0; i < 3; i++) {
            if (current.isEmpty() || current.charAt(current.length() - 1) != availableCharacters[i]) {
                current.append(availableCharacters[i]);
                backtrack(n, availableCharacters, current, happyStrings);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        final TheKthLexicographicalStringOfAllHappyStringsOfLengthN theKthLexicographicalStringOfAllHappyStringsOfLengthN
                = new TheKthLexicographicalStringOfAllHappyStringsOfLengthN();

        System.out.println(theKthLexicographicalStringOfAllHappyStringsOfLengthN.getHappyString(1, 3));
        System.out.println(theKthLexicographicalStringOfAllHappyStringsOfLengthN.getHappyString(1, 4));
        System.out.println(theKthLexicographicalStringOfAllHappyStringsOfLengthN.getHappyString(3, 9));
    }
}
