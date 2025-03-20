package org.redquark.leetcoding.strings;

public class BreakAPalindrome {

    public String breakPalindrome(String palindrome) {
        final int n = palindrome.length();
        // Special case
        if (n == 1) {
            return "";
        }
        final char[] characters = palindrome.toCharArray();
        // Process character by character
        for (int i = 0; i < n / 2; i++) {
            // If the current character is not 'a', we will replace it
            // with 'a'
            if (characters[i] != 'a') {
                characters[i] = 'a';
                return new String(characters);
            }
        }
        // When the flow reaches here, the possible strings could be
        // "aaa", "aba", etc. In this case, the best case it to use
        // character 'b' as the last character
        characters[n - 1] = 'b';
        return new String(characters);
    }

    public static void main(String[] args) {
        final BreakAPalindrome breakAPalindrome = new BreakAPalindrome();

        System.out.println(breakAPalindrome.breakPalindrome("abccba"));
        System.out.println(breakAPalindrome.breakPalindrome("a"));
    }
}
