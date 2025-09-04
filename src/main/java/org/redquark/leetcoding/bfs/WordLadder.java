package org.redquark.leetcoding.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Special case
        if (wordList == null || wordList.isEmpty()) {
            return 0;
        }
        // Convert wordList to HashSet for O(1) lookup
        final Set<String> words = new HashSet<>(wordList);
        // If endWord is not present in the list
        if (!words.contains(endWord)) {
            return 0;
        }
        // Minimum length of the ladder
        int ladderLength = 0;
        // Queue to perform BFS
        final Queue<String> nodes = new LinkedList<>();
        nodes.offer(beginWord);
        // Process all words
        while (!nodes.isEmpty()) {
            ladderLength++;
            // Get current size of the queue
            final int size = nodes.size();
            for (int i = 0; i < size; i++) {
                final char[] word = nodes.remove().toCharArray();
                // Loop through every character of the word
                for (int j = 0; j < word.length; j++) {
                    char originalCharacter = word[j];
                    // Try every character a-z at current place
                    for (char c = 'a'; c <= 'z'; c++) {
                        word[j] = c;
                        final String tempWord = new String(word);
                        // Check if this tempWord is equal to the endWord or not
                        if (tempWord.equals(endWord)) {
                            return ladderLength + 1;
                        }
                        // If the word is not in the dictionary, we will try
                        // other character
                        if (!words.contains(tempWord)) {
                            continue;
                        }
                        // Remove this word from the dictionary
                        words.remove(tempWord);
                        // Add this word to the queue
                        nodes.offer(tempWord);
                    }
                    word[j] = originalCharacter;
                }
            }
        }
        return 0;
    }

    public int ladderLengthBidirectionalBFS(String beginWord, String endWord, List<String> wordList) {
        // Convert the wordList to Set for constant time lookup
        final Set<String> words = new HashSet<>(wordList);
        // Check if endWord is present in the words set
        if (!words.contains(endWord)) {
            return 0;
        }
        // Perform Bidirectional BFS
        final Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        final Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        return bidirectionalBFS(beginSet, endSet, words, 1);
    }

    private int bidirectionalBFS(Set<String> beginSet, Set<String> endSet, Set<String> words, int level) {
        // Edge case
        if (beginSet.isEmpty() || endSet.isEmpty()) {
            return 0;
        }
        // Always expand smaller set
        if (beginSet.size() > endSet.size()) {
            return bidirectionalBFS(endSet, beginSet, words, level);
        }
        // Set to store nodes for next level
        final Set<String> nextLevel = new HashSet<>();
        for (String word : beginSet) {
            final char[] characters = word.toCharArray();
            for (int i = 0; i < characters.length; i++) {
                // Preserve current character
                final char originalCharacter = characters[i];
                // Try every character from 'a' to 'z' at current index
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originalCharacter) {
                        continue;
                    }
                    characters[i] = c;
                    final String tempWord = new String(characters);
                    // If we have found the endWord
                    if (endSet.contains(tempWord)) {
                        return level + 1;
                    }
                    // If tempWord exists in the words
                    if (words.contains(tempWord)) {
                        nextLevel.add(tempWord);
                        words.remove(tempWord);
                    }
                }
                characters[i] = originalCharacter;
            }
        }
        return bidirectionalBFS(nextLevel, endSet, words, level + 1);
    }

    public static void main(String[] args) {
        final WordLadder wordLadder = new WordLadder();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(wordLadder.ladderLength(beginWord, endWord, wordList));
        System.out.println(wordLadder.ladderLengthBidirectionalBFS(beginWord, endWord, wordList));

        beginWord = "hit";
        endWord = "cog";
        wordList = List.of("hot", "dot", "dog", "lot", "log");
        System.out.println(wordLadder.ladderLength(beginWord, endWord, wordList));
        System.out.println(wordLadder.ladderLengthBidirectionalBFS(beginWord, endWord, wordList));
    }
}
