package org.redquark.leetcoding.strings;

public class ShortestWordDistance {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        // Indices of both works
        int i = -1;
        int j = -1;
        // Shortest distance
        int shortestDistance = wordsDict.length;
        // Process wordDict
        for (int k = 0; k < wordsDict.length; k++) {
            if (wordsDict[k].equals(word1)) {
                i = k;
            }
            if (wordsDict[k].equals(word2)) {
                j = k;
            }
            if (i != -1 && j != -1) {
                shortestDistance = Math.min(shortestDistance, Math.abs(i - j));
            }
        }
        return shortestDistance;
    }

    public static void main(String[] args) {
        final ShortestWordDistance shortestWordDistance = new ShortestWordDistance();

        String[] wordsDict = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "practice";
        System.out.println(shortestWordDistance.shortestDistance(wordsDict, word1, word2));

        wordsDict = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        word1 = "makes";
        word2 = "coding";
        System.out.println(shortestWordDistance.shortestDistance(wordsDict, word1, word2));
    }
}
