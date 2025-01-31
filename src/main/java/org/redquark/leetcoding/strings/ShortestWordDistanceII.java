package org.redquark.leetcoding.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII {

    static class WordDistance {

        // Map to store the string and the indices where this key occurs
        private final Map<String, List<Integer>> mappings;

        public WordDistance(String[] wordsDict) {
            this.mappings = new HashMap<>();
            for (int i = 0; i < wordsDict.length; i++) {
                this.mappings.putIfAbsent(wordsDict[i], new ArrayList<>());
                this.mappings.get(wordsDict[i]).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            // Get indices of both words
            final List<Integer> word1Indices = this.mappings.get(word1);
            final List<Integer> word2Indices = this.mappings.get(word2);
            // Find minimum difference between the elements of two lists
            int minimumDistance = Integer.MAX_VALUE;
            int i = 0;
            int j = 0;
            while (i < word1Indices.size() && j < word2Indices.size()) {
                minimumDistance = Math.min(minimumDistance, Math.abs(word1Indices.get(i) - word2Indices.get(j)));
                if (word1Indices.get(i) <= word2Indices.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
            return minimumDistance;
        }
    }

    public static void main(String[] args) {
        String[] wordsDict = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wordDistance = new WordDistance(wordsDict);
        System.out.println(wordDistance.shortest("coding", "practice"));
        System.out.println(wordDistance.shortest("makes", "coding"));
    }
}
