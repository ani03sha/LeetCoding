package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

    public List<String> letterCombinations(String digits) {
        // List to store all combinations
        final List<String> combinations = new ArrayList<>();
        // Special case
        if (digits == null || digits.isEmpty()) {
            return combinations;
        }
        // Mappings of numbers and letters
        final String[] mappings = {
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };
        // Perform backtracking
        backtrack(digits, 0, new StringBuilder(), combinations, mappings);
        return combinations;
    }

    private void backtrack(String digits, int index, StringBuilder combination, List<String> combinations, String[] mappings) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }
        // Get letters corresponding to the current digit
        String letters = mappings[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            backtrack(digits, index + 1, combination.append(letter), combinations, mappings);
            combination.deleteCharAt(combination.length() - 1);
        }
    }

    public List<String> letterCombinationsOptimized(String digits) {
        // List to store all the combinations
        List<String> combinations = new ArrayList<>();
        // Special case
        if (digits == null || digits.isEmpty()) {
            return combinations;
        }
        // Digits to character mapping
        final String[] mappings = {
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };
        // Add empty string to the list
        combinations.add("");
        // Process for each character in the 'digits' string
        for (char c : digits.toCharArray()) {
            // The alphabets corresponding to this digit
            final String alphabets = mappings[c - '0'];
            // List to store current combinations of characters
            final List<String> currentCombinations = new ArrayList<>();
            // For every existing combination in the 'combinations' list,
            // we make a pair with the current set of alphabets
            for (String combination : combinations) {
                for (char alphabet : alphabets.toCharArray()) {
                    currentCombinations.add(combination + alphabet);
                }
            }
            combinations = currentCombinations;
        }
        return combinations;
    }

    public static void main(String[] args) {
        final LetterCombinationsOfAPhoneNumber letterCombinationsOfAPhoneNumber = new LetterCombinationsOfAPhoneNumber();

        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinations("23"));
        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinationsOptimized("23"));
        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinations(""));
        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinationsOptimized(""));
        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinations("2"));
        System.out.println(letterCombinationsOfAPhoneNumber.letterCombinationsOptimized("2"));
    }
}
