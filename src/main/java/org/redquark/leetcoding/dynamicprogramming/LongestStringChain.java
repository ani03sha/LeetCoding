package org.redquark.leetcoding.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestStringChain {

    public int longestStrChainMemoization(String[] words) {
        // Set to store all the words for constant time lookup
        final Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        // Lookup table for memoization => key, value = lastWordInSequence, lengthOfPossibleWord
        final Map<String, Integer> lookup = new HashMap<>();
        // Longest string chain length
        int length = 0;
        // Process for all words
        for (String word : words) {
            length = Math.max(length, dfs(wordSet, lookup, word));
        }
        return length;
    }

    private int dfs(Set<String> wordSet, Map<String, Integer> lookup, String currentWord) {
        // If we have already found currentWord
        if (lookup.containsKey(currentWord)) {
            return lookup.get(currentWord);
        }
        // Max length of the word sequence with currentWord as the last word
        int maxLength = 1;
        final StringBuilder sb = new StringBuilder(currentWord);
        // Creating all possible string by taking out one character at a time
        // from the currentWord
        for (int i = 0; i < currentWord.length(); i++) {
            sb.deleteCharAt(i);
            final String newWord = sb.toString();
            // If this word is in the wordSet, it means it will be part of
            // the sequence
            if (wordSet.contains(newWord)) {
                final int currentLength = 1 + dfs(wordSet, lookup, newWord);
                maxLength = Math.max(maxLength, currentLength);
            }
            // Reinsert removed character
            sb.insert(i, currentWord.charAt(i));
        }
        lookup.put(currentWord, maxLength);
        return maxLength;
    }

    public int longestStrChainDP(String[] words) {
        // Map to store word as key and value as the longest sequence
        // possible with word as the last word
        final Map<String, Integer> lookup = new HashMap<>();
        // Sort the words by their lengths
        Arrays.sort(words, Comparator.comparingInt(String::length));
        // Max length
        int maxLength = 1;
        // Process all words
        for (String word : words) {
            int currentLength = 1;
            // Find all predecessors of word by removing one character
            // at a time
            for (int i = 0; i < word.length(); i++) {
                final StringBuilder sb = new StringBuilder(word);
                // Delete the character at i-th position
                sb.deleteCharAt(i);
                // Predecessor word
                final String predecessor = sb.toString();
                final int previousLength = lookup.getOrDefault(predecessor, 0);
                currentLength = Math.max(currentLength, 1 + previousLength);
            }
            lookup.put(word, currentLength);
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        final LongestStringChain longestStringChain = new LongestStringChain();

        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println("Longest string chain length (Memoization): " + longestStringChain.longestStrChainMemoization(words));
        System.out.println("Longest string chain length (DP): " + longestStringChain.longestStrChainDP(words));

        words = new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        System.out.println("Longest string chain length (Memoization): " + longestStringChain.longestStrChainMemoization(words));
        System.out.println("Longest string chain length (DP): " + longestStringChain.longestStrChainDP(words));

        words = new String[]{"abcd", "dbqca"};
        System.out.println("Longest string chain length (Memoization): " + longestStringChain.longestStrChainMemoization(words));
        System.out.println("Longest string chain length (DP): " + longestStringChain.longestStrChainDP(words));

    }
}
