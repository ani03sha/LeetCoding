package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {

    public boolean isValid(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return true;
        }
        // Stack to store left parentheses
        final Deque<Character> leftParentheses = new ArrayDeque<>();
        // Process all characters in the string
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                leftParentheses.push(c);
            } else if (!leftParentheses.isEmpty() && c == ')' && leftParentheses.peek() == '(') {
                leftParentheses.pop();
            } else if (!leftParentheses.isEmpty() && c == '}' && leftParentheses.peek() == '{') {
                leftParentheses.pop();
            } else if (!leftParentheses.isEmpty() && c == ']' && leftParentheses.peek() == '[') {
                leftParentheses.pop();
            } else {
                return false;
            }
        }
        return leftParentheses.isEmpty();
    }

    public static void main(String[] args) {
        final ValidParentheses validParentheses = new ValidParentheses();

        String s = "()";
        System.out.println(validParentheses.isValid(s));

        s = "()[]{}";
        System.out.println(validParentheses.isValid(s));

        s = "(]";
        System.out.println(validParentheses.isValid(s));

        s = "([])";
        System.out.println(validParentheses.isValid(s));
    }
}
