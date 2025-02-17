package org.redquark.leetcoding.backtracking;

public class LetterTilePossibilities {

    public int numTilePossibilities(String tiles) {
        // Array to store the frequencies
        final int[] frequencies = new int[26];
        for (char c : tiles.toCharArray()) {
            frequencies[c - 'A']++;
        }
        // Perform backtracking
        return backtrack(frequencies);
    }

    private int backtrack(int[] frequencies) {
        // Sum of possibilities
        int possibilities = 0;
        // Check for all alphabets
        for (int i = 0; i < 26; i++) {
            // If the current alphabet is not used in the tiles
            // or it's all occurrences have been processed
            if (frequencies[i] == 0) {
                continue;
            }
            possibilities++;
            // We have used one occurrence of the current alphabet
            frequencies[i]--;
            // Backtrack
            possibilities += backtrack(frequencies);
            // Restore the count of current character
            frequencies[i]++;
        }
        return possibilities;
    }

    public static void main(String[] args) {
        final LetterTilePossibilities letterTilePossibilities = new LetterTilePossibilities();

        System.out.println(letterTilePossibilities.numTilePossibilities("AAB"));
        System.out.println(letterTilePossibilities.numTilePossibilities("AAABBC"));
        System.out.println(letterTilePossibilities.numTilePossibilities("V"));
    }
}
