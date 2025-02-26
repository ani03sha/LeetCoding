package org.redquark.leetcoding.strings;

public class StringCompression {

    public int compress(char[] chars) {
        // Special case
        if (chars == null || chars.length == 0) {
            return 0;
        }
        final int n = chars.length;
        // Index to keep track of current position in the compressed array
        int index = 0;
        // Process the characters in the chars array
        for (int i = 0; i < n; i++) {
            // Count for consecutive characters
            int count = 1;
            while (i < n - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }
            // Add character at i-th index to array
            chars[index] = chars[i];
            index++;
            // Append count, if required
            if (count > 1) {
                final String countString = String.valueOf(count);
                for (char c : countString.toCharArray()) {
                    chars[index] = c;
                    index++;
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        final StringCompression stringCompression = new StringCompression();

        char[] chars = new char[]{'a','a','b','b','c','c','c'};
        System.out.println(stringCompression.compress(chars));

        chars = new char[]{'a'};
        System.out.println(stringCompression.compress(chars));

        chars = new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(stringCompression.compress(chars));
    }
}
