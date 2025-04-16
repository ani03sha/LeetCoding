package org.redquark.leetcoding.strings;

public class MinimumAddToMakeParenthesesValid {

    public int minAddToMakeValid(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // Variable to keep track of unbalanced left parentheses
        int unbalanced = 0;
        // Variable to keep track of dangling right parentheses
        int dangling = 0;
        // Process through the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                unbalanced++;
            } else {
                if (unbalanced == 0) {
                    dangling++;
                } else {
                    unbalanced--;
                }
            }
        }
        return unbalanced + dangling;
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
