package org.redquark.leetcoding.strings;

public class LastSubstringInLexicographicalOrder {

    public String lastSubstring(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        final int n = s.length();
        // Starting index of the last substring lexicographically
        int lastCharacterStartIndex = 0;
        // Current index
        int currentIndex = 1;
        // Index for comparing characters
        int compareIndex = 0;
        // Process the string
        while (currentIndex + compareIndex < n) {
            // Compare character at current index and last character start index
            final int difference = s.charAt(lastCharacterStartIndex + compareIndex) - s.charAt(currentIndex + compareIndex);
            // If both characters are same, move to the next character
            // for comparison
            if (difference == 0) {
                compareIndex++;
            }
            // The current character is larger, update lastCharacterStartIndex to
            // the currentIndex
            else if (difference < 0) {
                lastCharacterStartIndex += compareIndex + 1;
                compareIndex = 0;
                if (lastCharacterStartIndex >= currentIndex) {
                    currentIndex = lastCharacterStartIndex + 1;
                }
            }
            // The current character is smaller, move past the current substring for comparison
            else {
                currentIndex += compareIndex + 1;
                compareIndex = 0;
            }
        }
        return s.substring(lastCharacterStartIndex);
    }

    public static void main(String[] args) {
        final LastSubstringInLexicographicalOrder lastSubstringInLexicographicalOrder = new LastSubstringInLexicographicalOrder();

        System.out.println(lastSubstringInLexicographicalOrder.lastSubstring("abab"));
        System.out.println(lastSubstringInLexicographicalOrder.lastSubstring("leetcode"));
    }
}
