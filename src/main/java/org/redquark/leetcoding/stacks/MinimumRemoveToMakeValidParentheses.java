package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class MinimumRemoveToMakeValidParentheses {

    public String minRemoveToMakeValidWithStack(String s) {
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

    public String minRemoveToMakeValidTwoPass(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder result = getBalancedString(s, '(', ')');
        result = getBalancedString(result.reverse().toString(), ')', '(');
        return result.reverse().toString();
    }

    private StringBuilder getBalancedString(String s, char left, char right) {
        final StringBuilder sb = new StringBuilder();
        // Balance of parentheses
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == left) {
                balance++;
            } else if (c == right) {
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }
        return sb;
    }

    public String minRemoveToMakeValidSinglePass(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        // Step 1 - remove all invalid ')'
        final StringBuilder sb = new StringBuilder();
        // Count of left parentheses
        int leftCount = 0;
        // Balance
        int balance = 0;
        // Process the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftCount++;
                balance++;
            } else if (c == ')') {
                if (balance == 0) {
                    continue;
                }
                balance--;
            }
            sb.append(c);
        }

        // Step 2 - Remove the rightmost '('
        int leftToKeep = leftCount - balance;
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            final char c = sb.charAt(i);
            if (c == '(') {
                leftToKeep--;
                if (leftToKeep < 0) {
                    continue;
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final MinimumRemoveToMakeValidParentheses minimumRemoveToMakeValidParentheses = new MinimumRemoveToMakeValidParentheses();

        System.out.println("Using Stack...");
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidWithStack("lee(t(c)o)de)"));
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidWithStack("a)b(c)d"));
        System.out.print(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidWithStack("))(("));
        System.out.println("-------------------------------------\n");

        System.out.println("Using Two Pass String Builder");
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("lee(t(c)o)de)"));
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("a)b(c)d"));
        System.out.print(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidTwoPass("))(("));
        System.out.println("-------------------------------------\n");

        System.out.println("Using One Pass String Builder");
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidSinglePass("lee(t(c)o)de)"));
        System.out.println(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidSinglePass("a)b(c)d"));
        System.out.print(minimumRemoveToMakeValidParentheses.minRemoveToMakeValidSinglePass("))(("));
        System.out.println("-------------------------------------\n");
    }
}