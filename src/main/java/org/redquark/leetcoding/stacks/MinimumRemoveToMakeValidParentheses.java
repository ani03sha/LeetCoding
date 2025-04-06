package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinimumRemoveToMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        final int n = s.length();
        // Set to store the indices to remove
        final Set<Integer> indicesToRemove = new HashSet<>();
        // Stack to store left parentheses
        final Deque<Integer> stack = new ArrayDeque<>();
        // Process the string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indicesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Check if there are any remaining left parentheses in the string
        // that are unbalanced
        while (!stack.isEmpty()) {
            indicesToRemove.add(stack.pop());
        }
        // Create the final result
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!indicesToRemove.contains(i)) {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final MinimumRemoveToMakeValidParentheses minimumRemoveToMakeValidParentheses = new MinimumRemoveToMakeValidParentheses();

        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValid("))(("));
    }
}
