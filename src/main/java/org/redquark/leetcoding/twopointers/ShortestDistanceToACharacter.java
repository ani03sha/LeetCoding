package org.redquark.leetcoding.twopointers;

import java.util.Arrays;

public class ShortestDistanceToACharacter {

    public int[] shortestToChar(String s, char c) {
        final int n = s.length();
        // Answer array
        int[] answer = new int[n];
        // Index to keep track of previous occurrence of c
        int previous = Integer.MIN_VALUE / 2;
        // Process string from left to right
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                previous = i;
            }
            answer[i] = i - previous;
        }
        // Reset previous
        previous = Integer.MAX_VALUE / 2;
        // Process string from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                previous = i;
            }
            answer[i] = Math.min(answer[i], previous - i);
        }
        return answer;
    }

    public static void main(String[] args) {
        final ShortestDistanceToACharacter shortestDistanceToACharacter = new ShortestDistanceToACharacter();

        String s = "loveleetcode";
        char c = 'e';
        System.out.println(Arrays.toString(shortestDistanceToACharacter.shortestToChar(s, c)));

        s = "aaab";
        c = 'b';
        System.out.println(Arrays.toString(shortestDistanceToACharacter.shortestToChar(s, c)));
    }
}
