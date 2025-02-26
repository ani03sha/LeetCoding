package org.redquark.leetcoding.twopointers;

import java.util.Arrays;

public class DIStringMatch {

    public int[] diStringMatch(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return new int[]{};
        }
        final int n = s.length();
        // Array to store result
        final int[] result = new int[n + 1];
        // Left and right pointers
        int left = 0;
        int right = n;
        // Index to keep track of the current pointer in the array
        int index = 0;
        // Now process the string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                result[index] = left;
                left++;
            } else {
                result[index] = right;
                right--;
            }
            index++;
        }
        result[index] = left;
        return result;
    }

    public static void main(String[] args) {
        final DIStringMatch diStringMatch = new DIStringMatch();

        System.out.println(Arrays.toString(diStringMatch.diStringMatch("IDID")));
        System.out.println(Arrays.toString(diStringMatch.diStringMatch("III")));
        System.out.println(Arrays.toString(diStringMatch.diStringMatch("DDI")));
        System.out.println(Arrays.toString(diStringMatch.diStringMatch("IIIIIIDDDDDIIIDIDIDIDIIIDDIDIDIDIDIDDDIDDIDIDI")));
    }
}
