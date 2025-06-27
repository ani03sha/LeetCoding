package org.redquark.leetcoding.strings;

public class FindTheOriginalTypedStringI {

    public int possibleStringCount(String word) {
        final int n = word.length();
        // Count of possible strings
        int count = 0;
        // Process the word character by character
        int index = 0;
        while (index < n) {
            final char currentCharacter = word.charAt(index);
            int start = index;
            // Count consecutive characters
            while (index < n - 1 && currentCharacter == word.charAt(index + 1)) {
                index++;
            }
            count += index - start;
            index++;
        }
        return count + 1;
    }

    public static void main(String[] args) {
        final FindTheOriginalTypedStringI findTheOriginalTypedStringI = new FindTheOriginalTypedStringI();

        System.out.println(findTheOriginalTypedStringI.possibleStringCount("abbcccc"));
        System.out.println(findTheOriginalTypedStringI.possibleStringCount("abcd"));
        System.out.println(findTheOriginalTypedStringI.possibleStringCount("aaaa"));
    }
}
