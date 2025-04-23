package org.redquark.leetcoding.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {

    /**
     * This is a straight-forward solution but results in TLE
     */
    public List<List<String>> findLaddersWorst(String beginWord, String endWord, List<String> wordList) {
        // List to store all the transformations
        final List<List<String>> transformations = new ArrayList<>();
        // Convert wordList to HashSet for constant time lookup
        final Set<String> words = new HashSet<>(wordList);
        // If endWord is not present in the dictionary
        if (!words.contains(endWord)) {
            return transformations;
        }
        // Queue to perform BFS
        final Queue<List<String>> nodes = new LinkedList<>();
        nodes.offer(List.of(beginWord));
        // Set to store words visited on a level
        final Set<String> visitedInLevel = new HashSet<>();
        // Flag to keep track if endWord is found
        boolean foundEnd = false;
        // Perform BFS
        while (!nodes.isEmpty() && !foundEnd) {
            final int size = nodes.size();
            // Clear the visitedInLevel for new level
            visitedInLevel.clear();
            for (int i = 0; i < size; i++) {
                final List<String> path = nodes.remove();
                // Get the last word
                final String lastWord = path.getLast();
                final char[] word = lastWord.toCharArray();
                for (int j = 0; j < word.length; j++) {
                    final char originalCharacter = word[j];
                    // Try every lowercase character
                    for (char c = 'a'; c <= 'z'; c++) {
                        word[j] = c;
                        final String nextWord = new String(word);
                        // If we have reached the endWord
                        if (nextWord.equals(endWord)) {
                            final List<String> newPath = new ArrayList<>(path);
                            newPath.add(nextWord);
                            transformations.add(newPath);
                            foundEnd = true;
                        }
                        // If nextWord is an intermediate word
                        else if (words.contains(nextWord)) {
                            final List<String> newPath = new ArrayList<>(path);
                            newPath.add(nextWord);
                            nodes.offer(newPath);
                            visitedInLevel.add(nextWord);
                        }
                    }
                    // Reset character
                    word[j] = originalCharacter;
                }
            }
            // Remove all visited words from the dictionary
            words.removeAll(visitedInLevel);
        }
        return transformations;
    }

    /**
     * Slightly better but still results in TLE
     */
    public List<List<String>> findLaddersSubOptimal(String beginWord, String endWord, List<String> wordList) {
        // List to store all transformations
        final List<List<String>> transformations = new ArrayList<>();
        // Convert wordList to HashSet for O(1) time lookup
        final Set<String> words = new HashSet<>(wordList);
        // If the list doesn't have endWord, we return immediately
        if (!words.contains(endWord)) {
            return transformations;
        }
        // Adjacency list - graph with the shortest transformations
        final Map<String, List<String>> graph = new HashMap<>();
        // Sets for bidirectional BFS
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        // Flag to keep track if the endWord is found
        boolean isEndWordFound = false;
        // Flag to keep track of direction - begin -> end or end -> begin
        boolean isForwardDirection = true;
        // Perform BFS
        while (!beginSet.isEmpty() && !endSet.isEmpty() && !isEndWordFound) {
            // Always expand the smaller frontier
            if (beginSet.size() > endSet.size()) {
                final Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
                isForwardDirection = !isForwardDirection;
            }
            // Remove all visited words to avoid cycles
            words.removeAll(beginSet);
            words.removeAll(endSet);
            // Next level
            // Set to store visited words in the current level
            final Set<String> visitedInThisLevel = new HashSet<>();
            for (String word : beginSet) {
                final char[] wordArray = word.toCharArray();
                // Check for every character in the array
                for (int i = 0; i < wordArray.length; i++) {
                    // Original character at the current position
                    final char originalCharacter = wordArray[i];
                    // Place every lowercase character at the current position
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[i] = c;
                        final String nextWord = new String(wordArray);
                        // If the current word is not present in the original dictionary
                        // and endSet, we try another character
                        if (!words.contains(nextWord) && !endSet.contains(nextWord)) {
                            continue;
                        }
                        final String key = isForwardDirection ? word : nextWord;
                        final String value = isForwardDirection ? nextWord : word;
                        graph.computeIfAbsent(key, _ -> new ArrayList<>()).add(value);
                        // If we find the endWord
                        if (endSet.contains(nextWord)) {
                            isEndWordFound = true;
                        } else {
                            visitedInThisLevel.add(nextWord);
                        }
                    }
                    wordArray[i] = originalCharacter;
                }
            }
            beginSet = visitedInThisLevel;
            words.removeAll(visitedInThisLevel);
        }
        // If the path exists, backtrack them
        if (isEndWordFound) {
            final List<String> path = new ArrayList<>();
            path.add(beginWord);
            backtrack(beginWord, endWord, graph, path, transformations);
        }
        return transformations;
    }

    private void backtrack(String current, String target, Map<String, List<String>> graph, List<String> path, List<List<String>> transformations) {
        if (current.equals(target)) {
            transformations.add(new ArrayList<>(path));
            return;
        }
        if (!graph.containsKey(current)) {
            return;
        }
        for (String neighbor : graph.get(current)) {
            path.add(neighbor);
            backtrack(neighbor, target, graph, path, transformations);
            path.removeLast();
        }
    }

    /**
     * Even better but still results in TLE
     */
    public List<List<String>> findLaddersOptimal(String beginWord, String endWord, List<String> wordList) {
        // List to store final output
        final List<List<String>> transformations = new ArrayList<>();
        // Store words in the HashSet for constant time lookup
        final Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) {
            return transformations;
        }
        // Level map to store the shortest distance from the beginWord
        final Map<String, Integer> levelMap = new HashMap<>();
        levelMap.put(beginWord, 0);
        // Graph from word -> list of neighbors (only shortest path neighbors)
        final Map<String, List<String>> graph = new HashMap<>();
        // Queue to perform BFS
        final Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // Set to store strings visited in the current level
        final Set<String> visitedInThisLevel = new HashSet<>();
        // Perform BFS
        while (!queue.isEmpty()) {
            final int size = queue.size();
            visitedInThisLevel.clear();
            // Check for every word at the current level
            for (int i = 0; i < size; i++) {
                final String currentWord = queue.remove();
                int currentLevel = levelMap.get(currentWord);
                final char[] word = currentWord.toCharArray();
                for (int j = 0; j < word.length; j++) {
                    // Original character
                    final char originalCharacter = word[j];
                    // Check for every combination of lowercase character
                    for (char c = 'a'; c < 'z'; c++) {
                        word[j] = c;
                        final String nextWord = new String(word);
                        // If this word is not present in the dictionary, we check
                        // another combination
                        if (!words.contains(nextWord)) {
                            continue;
                        }
                        if (!levelMap.containsKey(nextWord)) {
                            levelMap.put(nextWord, currentLevel + 1);
                            queue.offer(nextWord);
                            visitedInThisLevel.add(nextWord);
                        }
                        // Only connect the shortest paths
                        if (levelMap.get(nextWord) == currentLevel + 1) {
                            graph.computeIfAbsent(currentWord, _ -> new ArrayList<>()).add(nextWord);
                        }
                    }
                    // Reset original character
                    word[j] = originalCharacter;
                }
            }
            // Remove only after to avoid cycle
            words.removeAll(visitedInThisLevel);
        }
        // Backtrack to collect all paths
        final List<String> path = new ArrayList<>();
        path.add(beginWord);
        backtrack(beginWord, endWord, graph, path, transformations);
        return transformations;
    }

    public List<List<String>> findLaddersBest(String beginWord, String endWord, List<String> wordList) {
        // List to store the final result
        List<List<String>> output = new ArrayList<>();
        // Set to represent nodes which are unvisited
        Set<String> unvisited = new HashSet<>(wordList);
        // Base case
        if (beginWord == null || endWord == null || beginWord.isEmpty() || endWord.isEmpty() || !unvisited.contains(endWord)) {
            return output;
        }
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> dMap = new HashMap<>();
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.add(beginWord);
        if (!wordSet.contains(endWord)) {
            return output;
        }
        bfs(endWord, wordSet, dMap, graph);
        dfs(beginWord, endWord, dMap, graph, output, new ArrayList<>());
        return output;
    }

    private void bfs(String endWord, Set<String> wordSet, Map<String, Integer> dMap, Map<String, List<String>> graph) {
        Queue<String> q = new LinkedList<>();
        q.offer(endWord);
        dMap.put(endWord, 0);
        int paces = 0;
        for (String s : wordSet) {
            graph.put(s, new ArrayList<>());
        }
        while (!q.isEmpty()) {
            paces++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                List<String> nextWords = nextWords(Objects.requireNonNull(w), wordSet);
                graph.get(w).addAll(nextWords);
                for (String next : nextWords) {
                    if (!dMap.containsKey(next)) {
                        q.offer(next);
                        dMap.put(next, paces);
                    }
                }
            }
        }
    }

    private List<String> nextWords(String word, Set<String> wordSet) {
        List<String> result = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == cur) {
                    continue;
                }
                chars[i] = c;
                String newString = new String(chars);
                if (wordSet.contains(newString)) {
                    result.add(newString);
                }
            }
            chars[i] = cur;
        }
        return result;
    }

    private void dfs(String currentWord, String endWord, Map<String, Integer> dMap, Map<String, List<String>> graph,
                     List<List<String>> result, List<String> path) {
        path.add(currentWord);
        if (currentWord.equals(endWord)) {
            result.add(new ArrayList<>(path));
        } else {
            for (String s : graph.get(currentWord)) {
                if (dMap.get(currentWord) == dMap.get(s) + 1) {
                    dfs(s, endWord, dMap, graph, result, path);
                }
            }
        }
        path.removeLast();
    }

    public static void main(String[] args) {
        final WordLadderII wordLadderII = new WordLadderII();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(wordLadderII.findLaddersWorst(beginWord, endWord, wordList));
        System.out.println(wordLadderII.findLaddersSubOptimal(beginWord, endWord, wordList));
        System.out.println(wordLadderII.findLaddersOptimal(beginWord, endWord, wordList));
        System.out.println(wordLadderII.findLaddersBest(beginWord, endWord, wordList));

        wordList = List.of("hot", "dot", "dog", "lot", "log");
        System.out.println(wordLadderII.findLaddersWorst(beginWord, endWord, wordList));
        System.out.println(wordLadderII.findLaddersSubOptimal(beginWord, endWord, wordList));
        System.out.println(wordLadderII.findLaddersOptimal(beginWord, endWord, wordList));
        System.out.println(wordLadderII.findLaddersBest(beginWord, endWord, wordList));
    }
}
