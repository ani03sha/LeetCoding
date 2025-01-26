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

    public static void main(String[] args) {
        final WordLadder wordLadder = new WordLadder();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(wordLadder.ladderLength(beginWord, endWord, wordList));

        beginWord = "hit";
        endWord = "cog";
        wordList = List.of("hot", "dot", "dog", "lot", "log");
        System.out.println(wordLadder.ladderLength(beginWord, endWord, wordList));
    }
}
