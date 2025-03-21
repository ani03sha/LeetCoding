package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseSubstringsBetweenEachPairOfParentheses {

    public String reverseParentheses(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        final int n = s.length();
        // Stack to keep track of opening parentheses
        final Deque<Integer> stack = new ArrayDeque<>();
        // Array to keep track of pairs of indices of opening
        // and closing parentheses
        final int[] pairIndex = new int[n];
        // Process the string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pairIndex[i] = j;
                pairIndex[j] = i;
            }
        }
        // StringBuilder to store final result
        final StringBuilder result = new StringBuilder();
        int index = 0;
        int direction = 1;
        // Process string second time
        while (index < n) {
            if (s.charAt(index) == '(' || s.charAt(index) == ')') {
                index = pairIndex[index];
                direction = -direction;
            } else {
                result.append(s.charAt(index));
            }
            index += direction;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final ReverseSubstringsBetweenEachPairOfParentheses reverseSubstringsBetweenEachPairOfParentheses = new ReverseSubstringsBetweenEachPairOfParentheses();

        System.out.println(reverseSubstringsBetweenEachPairOfParentheses.reverseParentheses("(abcd)"));
        System.out.println(reverseSubstringsBetweenEachPairOfParentheses.reverseParentheses("(u(love)i)"));
        System.out.println(reverseSubstringsBetweenEachPairOfParentheses.reverseParentheses("(ed(et(oc))el)"));
    }
}
