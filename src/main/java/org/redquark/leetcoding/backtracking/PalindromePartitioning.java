package org.redquark.leetcoding.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        // List to store partitions
        final List<List<String>> partitions = new ArrayList<>();
        // Special case
        if (s == null || s.isEmpty()) {
            return partitions;
        }
        // Perform backtracking on the string
        backtrack(s, 0, new ArrayList<>(), partitions);
        return partitions;
    }

    private void backtrack(String s, int index, List<String> partition, List<List<String>> partitions) {
        if (index == s.length()) {
            partitions.add(new ArrayList<>(partition));
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            // Get current substring
            final String current = s.substring(index, i);
            // Check if this string is palindrome
            if (isPalindrome(current)) {
                partition.add(current);
                backtrack(s, i, partition, partitions);
                partition.removeLast();
            }
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        final PalindromePartitioning palindromePartitioning = new PalindromePartitioning();

        String s = "aab";
        System.out.println(palindromePartitioning.partition(s));

        s = "a";
        System.out.println(palindromePartitioning.partition(s));
    }
}
