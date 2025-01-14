package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        // List to store the final output
        final List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return result;
        }
        // Convert dictionary to Set for O(1) lookup
        final Set<String> words = new HashSet<>(wordDict);
        // Perform backtracking
        backtrack(s, 0, new ArrayList<>(), result, words);
        return result;
    }

    private void backtrack(String s, int index, List<String> current, List<String> result, Set<String> words) {
        if (index == s.length()) {
            result.add(String.join(" ", current));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            final String word = s.substring(index, i + 1);
            if (words.contains(word)) {
                current.add(word);
                backtrack(s, i + 1, current, result, words);
                current.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        final WordBreakII wordBreakII = new WordBreakII();

        String s = "catsanddog";
        List<String> wordDict = List.of("cat", "cats", "and", "sand", "dog");
        System.out.println(wordBreakII.wordBreak(s, wordDict));

        s = "pineapplepenapple";
        wordDict = List.of("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreakII.wordBreak(s, wordDict));

        s = "catsandog";
        wordDict = List.of("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreakII.wordBreak(s, wordDict));
    }
}
