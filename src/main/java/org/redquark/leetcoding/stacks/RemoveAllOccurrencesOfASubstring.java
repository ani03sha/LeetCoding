package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllOccurrencesOfASubstring {

    public String removeOccurrences(String s, String part) {
        // Special case
        if (s == null || s.isEmpty() || part == null || part.isEmpty()) {
            return s;
        }
        final int m = s.length();
        final int n = part.length();
        // Stack to store the characters
        final Deque<Character> stack = new ArrayDeque<>();
        // Process the string
        for (int i = 0; i < m; i++) {
            // Add current character to stack
            stack.push(s.charAt(i));
            // We need to remove part only if stack has enough characters
            if (stack.size() >= n) {
                // Flag to check if the entire word is matched
                boolean isMatch = true;
                // StringBuilder to keep track of characters we are removing
                final StringBuilder removedCharacters = new StringBuilder();
                for (int j = n - 1; j >= 0; j--) {
                    // Get the top of the stack
                    char top = stack.pop();
                    removedCharacters.append(top);
                    // If top doesn't match the current character in part, we break
                    if (top != part.charAt(j)) {
                        isMatch = false;
                        break;
                    }
                }
                // Since, we didn't find the match, we restore characters in the stack
                if (!isMatch) {
                    for (int k = removedCharacters.length() - 1; k >= 0; k--) {
                        stack.push(removedCharacters.charAt(k));
                    }
                }
            }
        }
        final StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        final RemoveAllOccurrencesOfASubstring removeAllOccurrencesOfASubstring = new RemoveAllOccurrencesOfASubstring();

        String s = "daabcbaabcbc";
        String part = "abc";
        System.out.println(removeAllOccurrencesOfASubstring.removeOccurrences(s, part));

        s = "axxxxyyyyb";
        part = "xy";
        System.out.println(removeAllOccurrencesOfASubstring.removeOccurrences(s, part));

        s = "sfdsffsfsffsfsffsdfdsgsdgdsg";
        part = "dfs";
        System.out.println(removeAllOccurrencesOfASubstring.removeOccurrences(s, part));
    }
}
