package org.redquark.leetcoding.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        // List to store the starting indices
        final List<Integer> indices = new ArrayList<>();
        // Special case
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return indices;
        }
        // Length of the string
        final int m = s.length();
        // Total number of words in the array
        final int n = words.length;
        // Length of each word
        final int w = words[0].length();
        // Map to store frequencies of every word in the array
        final Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        // Process the string
        for (int i = 0; i <= m - n * w; i++) {
            // Map to keep track of the current word from the string and its frequencies
            final Map<String, Integer> currentFrequencies = getStringIntegerMap(s, n, w, i);
            // Check if both the maps are equal
            if (wordFrequencies.equals(currentFrequencies)) {
                indices.add(i);
            }
        }
        return indices;
    }

    private static Map<String, Integer> getStringIntegerMap(String s, int n, int w, int i) {
        final Map<String, Integer> currentFrequencies = new HashMap<>();
        // Index to keep track of the number of parts we need which is equal to the
        // number of words in the array
        int index = 0;
        // Index to keep track of the position we are at in the string
        int j = i;
        // Now, find n substrings in the current string
        while (index < n) {
            final String currentPart = s.substring(j, j + w);
            currentFrequencies.put(currentPart, currentFrequencies.getOrDefault(currentPart, 0) + 1);
            j += w;
            index++;
        }
        return currentFrequencies;
    }

    public List<Integer> findSubstringOptimized(String s, String[] words) {
        // List to store the starting indices
        final List<Integer> indices = new ArrayList<>();
        // Special case
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return indices;
        }
        // Length of the string
        final int m = s.length();
        // Total number of words in the array
        final int n = words.length;
        // Length of each word
        final int w = words[0].length();
        // Map to store frequencies of every word in the array
        final Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        // Try all possible starting points withing length w
        for (int i = 0; i < w; i++) {
            // Start and end pointers of the sliding window
            int start = i;
            int end = i;
            // Map to store the frequencies of the seen words
            final Map<String, Integer> seenWords = new HashMap<>();
            // Count of words found
            int count = 0;
            // Process all words in the string
            while (end + w <= m) {
                final String word = s.substring(end, end + w);
                end += w;
                // If the word is already present in seenWords map
                if (wordFrequencies.containsKey(word)) {
                    seenWords.put(word, seenWords.getOrDefault(word, 0) + 1);
                    count++;
                    // Shrink from the left
                    while (seenWords.get(word) > wordFrequencies.get(word)) {
                        final String leftWord = s.substring(start, start + w);
                        seenWords.put(leftWord, seenWords.get(leftWord) - 1);
                        start += w;
                        count--;
                    }
                    // If we found all words, add the index
                    if (count == n) {
                        indices.add(start);
                    }
                }
                // Reset the window if invalid words is found
                else {
                    seenWords.clear();
                    count = 0;
                    start = end;
                }
            }
        }
        return indices;
    }

    public static void main(String[] args) {
        final SubstringWithConcatenationOfAllWords substringWithConcatenationOfAllWords = new SubstringWithConcatenationOfAllWords();

        String s = "barfoothefoobarman";
        String[] words = new String[]{"foo", "bar"};
        System.out.println(substringWithConcatenationOfAllWords.findSubstring(s, words));
        System.out.println(substringWithConcatenationOfAllWords.findSubstringOptimized(s, words));

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word", "good", "best", "word"};
        System.out.println(substringWithConcatenationOfAllWords.findSubstring(s, words));
        System.out.println(substringWithConcatenationOfAllWords.findSubstringOptimized(s, words));

        s = "barfoofoobarthefoobarman";
        words = new String[]{"bar", "foo", "the"};
        System.out.println(substringWithConcatenationOfAllWords.findSubstring(s, words));
        System.out.println(substringWithConcatenationOfAllWords.findSubstringOptimized(s, words));
    }
}
