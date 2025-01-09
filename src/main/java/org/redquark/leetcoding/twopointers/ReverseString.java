package org.redquark.leetcoding.twopointers;

import java.util.Arrays;

public class ReverseString {

    public void reverseString(char[] s) {
        // Special case
        if (s == null || s.length == 0) {
            return;
        }
        // Left and right pointers
        int left = 0;
        int right = s.length - 1;
        // Process strings from both ends
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        final ReverseString reverseString = new ReverseString();

        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverseString.reverseString(s);
        System.out.println(Arrays.toString(s));

        s = new char[]{'h', 'a', 'n', 'n', 'a', 'h'};
        reverseString.reverseString(s);
        System.out.println(Arrays.toString(s));
    }
}
