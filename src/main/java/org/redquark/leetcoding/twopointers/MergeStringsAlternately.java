package org.redquark.leetcoding.twopointers;

public class MergeStringsAlternately {

    public String mergeAlternately(String word1, String word2) {
        final int m = word1.length();
        final int n = word2.length();
        // Pointers to keep track of two stringsd
        int i = 0;
        int j = 0;
        // Merged string
        final StringBuilder mergedString = new StringBuilder();
        while (i < m && j < n) {
            mergedString.append(word1.charAt(i)).append(word2.charAt(j));
            i++;
            j++;
        }
        while (i < m) {
            mergedString.append(word1.charAt(i));
            i++;
        }
        while (j < n) {
            mergedString.append(word2.charAt(j));
            j++;
        }
        return mergedString.toString();
    }

    public static void main(String[] args) {
        final MergeStringsAlternately mergeStringsAlternately = new MergeStringsAlternately();

        String word1 = "abc";
        String word2 = "pqr";
        System.out.println(mergeStringsAlternately.mergeAlternately(word1, word2));

        word1 = "ab";
        word2 = "pqrs";
        System.out.println(mergeStringsAlternately.mergeAlternately(word1, word2));

        word1 = "abcd";
        word2 = "pq";
        System.out.println(mergeStringsAlternately.mergeAlternately(word1, word2));
    }
}
