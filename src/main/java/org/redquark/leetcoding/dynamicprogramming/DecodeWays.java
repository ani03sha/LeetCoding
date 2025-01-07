package org.redquark.leetcoding.dynamicprogramming;

public class DecodeWays {

    public int numDecodings(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return 0;
        }
        final int n = s.length();
        // Lookup table to store all the combinations
        final int[] lookup = new int[n + 1];
        // Base initialization
        lookup[0] = 1;
        lookup[1] = s.charAt(0) == '0' ? 0 : 1;
        // Process for remaining positions
        for (int i = 2; i <= n; i++) {
            final int singleDigit = Integer.parseInt(s.substring(i - 1, i));
            if (singleDigit > 0) {
                lookup[i] += lookup[i - 1];
            }
            final int doubleDigit = Integer.parseInt(s.substring(i - 2, i));
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                lookup[i] += lookup[i - 2];
            }
        }
        return lookup[n];
    }

    public static void main(String[] args) {
        final DecodeWays decodeWays = new DecodeWays();

        String s = "12";
        System.out.println(decodeWays.numDecodings(s));

        s = "226";
        System.out.println(decodeWays.numDecodings(s));

        s = "06";
        System.out.println(decodeWays.numDecodings(s));
    }
}
