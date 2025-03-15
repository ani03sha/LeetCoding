package org.redquark.leetcoding.slidingwindow;

public class SubstringOfSizeThreeWithDistinctCharacters {

    public int countGoodSubstrings(String s) {
        // Special case
        if (s == null || s.length() < 3) {
            return 0;
        }
        // Count of valid substrings
        int count = 0;
        // Process all substrings
        for (int i = 0; i < s.length() - 2; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            char c = s.charAt(i + 2);
            if (a != b && b != c && a != c) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final SubstringOfSizeThreeWithDistinctCharacters substringOfSizeThreeWithDistinctCharacters = new SubstringOfSizeThreeWithDistinctCharacters();

        String s = "xyzzaz";
        System.out.println(substringOfSizeThreeWithDistinctCharacters.countGoodSubstrings(s));

        s = "aababcabc";
        System.out.println(substringOfSizeThreeWithDistinctCharacters.countGoodSubstrings(s));
    }
}
