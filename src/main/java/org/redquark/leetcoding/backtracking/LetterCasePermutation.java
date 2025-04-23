package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        // List to store all the permutations
        final List<String> permutations = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return permutations;
        }
        // Perform backtracking
        backtrack(s.toCharArray(), 0, permutations);
        return permutations;
    }

    private void backtrack(char[] characters, int index, List<String> permutations) {
        // If we have covered all the characters
        if (index == characters.length) {
            permutations.add(new String(characters));
        }
        // If the current character is a letter, we try both lowercase
        // and uppercase combinations
        else if (Character.isLetter(characters[index])) {
            // Uppercase
            characters[index] = Character.toUpperCase(characters[index]);
            backtrack(characters, index + 1, permutations);
            // Lowercase
            characters[index] = Character.toLowerCase(characters[index]);
            backtrack(characters, index + 1, permutations);
        }
        // If the current character is a digit, we take it as is
        else {
            backtrack(characters, index + 1, permutations);
        }
    }

    public static void main(String[] args) {
        final LetterCasePermutation letterCasePermutation = new LetterCasePermutation();

        System.out.println(letterCasePermutation.letterCasePermutation("a1b2"));
        System.out.println(letterCasePermutation.letterCasePermutation("3z4"));
    }
}
