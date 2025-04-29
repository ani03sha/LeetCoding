package org.redquark.leetcoding.strings;

public class BullsAndCows {

    public String getHint(String secret, String guess) {
        if (secret.length() != guess.length()) {
            return "";
        }
        // Frequencies of characters in secret string and guess string
        final int[] secretFrequencies = new int[10];
        final int[] guessFrequencies = new int[10];
        // Count of bulls and cows
        int bulls = 0;
        int cows = 0;
        // Process the strings
        for (int i = 0; i < secret.length(); i++) {
            // If characters in both strings are same, it's "BULL"
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            }
            // If the characters are not same, we update their frequencies
            else {
                secretFrequencies[secret.charAt(i) - '0']++;
                guessFrequencies[guess.charAt(i) - '0']++;
            }
        }
        // For each character find the overlapping frequency to
        // find the number of cows
        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretFrequencies[i], guessFrequencies[i]);
        }
        // Generate final output
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        final BullsAndCows bullsAndCows = new BullsAndCows();

        String secret = "1807";
        String guess = "7810";
        System.out.println(bullsAndCows.getHint(secret, guess));

        secret = "1123";
        guess = "0111";
        System.out.println(bullsAndCows.getHint(secret, guess));
    }
}
