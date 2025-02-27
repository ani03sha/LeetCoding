package org.redquark.leetcoding.strings;

import java.util.List;

public class LongestWordInDictionaryThroughDeleting {

    public String findLongestWord(String s, List<String> dictionary) {
        // String to store the longest word in length
        // but smallest lexicographically
        String result = "";
        // Process every word in the dictionary
        for (String word : dictionary) {
            // Check if current word is a subsequence of s
            if (isSubsequence(s, word)) {
                // Update result if required based on length
                if (word.length() > result.length()) {
                    result = word;
                }
                // Find smallest lexicographically
                if (word.length() == result.length() && word.compareTo(result) < 0) {
                    result = word;
                }
            }
        }
        return result;
    }

    private boolean isSubsequence(String s, String w) {
        // Pointers for both strings
        int i = 0;
        int j = 0;
        while (i < s.length() && j < w.length()) {
            if (s.charAt(i) == w.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == w.length();
    }

    public static void main(String[] args) {
        final LongestWordInDictionaryThroughDeleting longestWordInDictionaryThroughDeleting = new LongestWordInDictionaryThroughDeleting();

        String s = "abpcplea";
        List<String> dictionary = List.of("ale", "apple", "monkey", "plea");
        System.out.println(longestWordInDictionaryThroughDeleting.findLongestWord(s, dictionary));

        dictionary = List.of("a", "b", "c");
        System.out.println(longestWordInDictionaryThroughDeleting.findLongestWord(s, dictionary));
    }
}
