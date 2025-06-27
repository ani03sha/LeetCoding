package org.redquark.leetcoding.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindCommonCharacters {

    public List<String> commonChars(String[] words) {
        // Arrays to store common characters' frequencies and
        // individual word's characters' frequency
        final int[] commonFrequencies = new int[26];
        final int[] frequencies = new int[26];
        // If a character is not in the first word, then it
        // cannot be common across all the words. So, we can
        // safely assume that characters in the first word
        // will be the superset of all the common characters
        for (char c : words[0].toCharArray()) {
            commonFrequencies[c - 'a']++;
        }
        // Now, process remaining words
        for (int i = 1; i < words.length; i++) {
            // Count frequencies of characters in the current word
            Arrays.fill(frequencies, 0);
            for (char c : words[i].toCharArray()) {
                frequencies[c - 'a']++;
            }
            // Update common frequencies
            for (int j = 0; j < 26; j++) {
                commonFrequencies[j] = Math.min(frequencies[j], commonFrequencies[j]);
            }
        }
        // Prepare the final output
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < commonFrequencies[i]; j++) {
                result.add(String.valueOf((char) (i + 'a')));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final FindCommonCharacters findCommonCharacters = new FindCommonCharacters();

        String[] words = {"bella", "label", "roller"};
        List<String> commonChars = findCommonCharacters.commonChars(words);
        System.out.println(commonChars); // Output: [e, l, l]

        words = new String[]{"cool", "lock", "cook"};
        commonChars = findCommonCharacters.commonChars(words);
        System.out.println(commonChars); // Output: [c, o]
    }
}
