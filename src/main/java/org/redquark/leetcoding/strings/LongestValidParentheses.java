package org.redquark.leetcoding.strings;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {

    public int longestValidParenthesesSuboptimal(String s) {
        final int n = s.length();
        // Array to keep track of balanced parentheses
        final boolean[] balanced = new boolean[n];
        // Stack to keep track of balanced string
        final Deque<Integer> stack = new ArrayDeque<>();
        // Process the string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    balanced[i] = true;
                    balanced[stack.pop()] = true;
                }
            }
        }
        // Find the longest window of true in the array
        int longestLength = 0;
        int left = 0;
        int right = 0;
        while (right < n) {
            if (balanced[right]) {
                right++;
                longestLength = Math.max(longestLength, right - left);
            } else {
                right++;
                left = right;
            }
        }
        return longestLength;
    }

    public int longestValidParenthesesOptimal(String s) {
        final int n = s.length();
        // Counters for left and right parentheses
        int left = 0;
        int right = 0;
        // Longest length
        int longestLength = 0;
        // Process array from left to right
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            // If string becomes balanced, update longestLength
            if (left == right) {
                longestLength = Math.max(longestLength, 2 * right);
            }
            // If there are more right parentheses
            else if (right > left) {
                left = 0;
                right = 0;
            }
        }
        // Reset left and right counters
        left = 0;
        right = 0;
        // Process the string from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                longestLength = Math.max(longestLength, 2 * left);
            } else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return longestLength;
    }

    public static void main(String[] args) {
        final LongestValidParentheses longestValidParentheses = new LongestValidParentheses();

        System.out.println(longestValidParentheses.longestValidParenthesesSuboptimal("(()"));
        System.out.println(longestValidParentheses.longestValidParenthesesOptimal("(()"));

        System.out.println(longestValidParentheses.longestValidParenthesesSuboptimal(")()())"));
        System.out.println(longestValidParentheses.longestValidParenthesesOptimal(")()())"));

        System.out.println(longestValidParentheses.longestValidParenthesesSuboptimal(""));
        System.out.println(longestValidParentheses.longestValidParenthesesOptimal(""));
    }
}
