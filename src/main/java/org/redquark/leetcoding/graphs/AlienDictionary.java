package org.redquark.leetcoding.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {

    public String alienOrder(String[] words) {
        // Special case
        if (words == null || words.length == 0) {
            return "";
        }
        // Map to store graph
        final Map<Character, Set<Character>> graph = new HashMap<>();
        // Map to store indegrees of characters
        final Map<Character, Integer> indegree = new HashMap<>();
        // Initialize graph nodes
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.put(c, 0);
            }
        }
        // Build graph
        for (int i = 0; i < words.length - 1; i++) {
            // First and second words
            final String firstWord = words[i];
            final String secondWord = words[i + 1];
            // If bigger word comes first
            if (firstWord.startsWith(secondWord) && firstWord.length() > secondWord.length()) {
                return "";
            }
            // Minimum length
            final int minLength = Math.min(firstWord.length(), secondWord.length());
            // Create graph nodes
            for (int j = 0; j < minLength; j++) {
                final char c1 = firstWord.charAt(j);
                final char c2 = secondWord.charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        // Topological sort
        final Queue<Character> zeroIndegreeNodes = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                zeroIndegreeNodes.offer(c);
            }
        }
        // Alien order
        final StringBuilder order = new StringBuilder();
        while (!zeroIndegreeNodes.isEmpty()) {
            final char current = zeroIndegreeNodes.remove();
            order.append(current);
            for (char neighbor : graph.get(current)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    zeroIndegreeNodes.offer(neighbor);
                }
            }
        }
        // Cycle detected
        if (order.length() != indegree.size()) {
            return "";
        }
        return order.toString();
    }

    public static void main(String[] args) {
        final AlienDictionary alienDictionary = new AlienDictionary();

        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienDictionary.alienOrder(words));

        words = new String[]{"z", "x"};
        System.out.println(alienDictionary.alienOrder(words));

        words = new String[]{"z", "x", "z"};
        System.out.println(alienDictionary.alienOrder(words));
    }
}
