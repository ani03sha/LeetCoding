package org.redquark.leetcoding.strings;

public class MinimumAddToMakeParenthesesValid {

    public int minAddToMakeValid(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // Number of '(' and ')' we need to add
        int left = 0;
        int right = 0;
        // Process the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                right++;
            } else if (right > 0) {
                right--;
            } else {
                left++;
            }
        }
        return left + right;
    }

    public static void main(String[] args) {
        final MinimumAddToMakeParenthesesValid minimumAddToMakeParenthesesValid = new MinimumAddToMakeParenthesesValid();

        System.out.println(minimumAddToMakeParenthesesValid.minAddToMakeValid("())"));
        System.out.println(minimumAddToMakeParenthesesValid.minAddToMakeValid("((("));
        System.out.println(minimumAddToMakeParenthesesValid.minAddToMakeValid("((()()(()))))((())))()()()()()()()()()" +
                "()()()((((((((((()())))))))))))))))))()()()()()()(((((((((((((((((())))))))))))))))))))))))))))()()()()()"));
        System.out.println(minimumAddToMakeParenthesesValid.minAddToMakeValid("()))(("));
    }
}
