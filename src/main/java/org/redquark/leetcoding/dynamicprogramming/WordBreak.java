package org.redquark.leetcoding.dynamicprogramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        // Special case
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        final int n = s.length();
        // Set to store all words in the dictionary for O(1) lookup
        final Set<String> words = new HashSet<>(wordDict);
        // Lookup table to store if the word ending at i is present in the dictionary
        final boolean[] lookup = new boolean[n + 1];
        // Since empty string is always present
        lookup[0] = true;
        // Process the remaining positions
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (lookup[j] && words.contains(s.substring(j, i))) {
                    lookup[i] = true;
                    break;
                }
            }
        }
        return lookup[n];
    }

    public static void main(String[] args) {
        final WordBreak wordBreak = new WordBreak();

        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        System.out.println(wordBreak.wordBreak(s, wordDict));

        s = "applepenapple";
        wordDict = List.of("apple", "pen");
        System.out.println(wordBreak.wordBreak(s, wordDict));

        s = "catsandog";
        wordDict = List.of("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak.wordBreak(s, wordDict));
    }
}
