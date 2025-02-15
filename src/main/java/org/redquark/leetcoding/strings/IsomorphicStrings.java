package org.redquark.leetcoding.strings;

public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        // Special case
        if (s.length() != t.length()) {
            return false;
        }
        final int n = s.length();
        // Arrays to store indices of characters in both strings
        final int[] sMap = new int[256];
        final int[] tMap = new int[256];
        // Process both strings
        for (int i = 0; i < n; i++) {
            final char sChar = s.charAt(i);
            final char tChar = t.charAt(i);
            // If the indices don't map
            if (sMap[sChar] != tMap[tChar]) {
                return false;
            }
            sMap[sChar] = i + 1;
            tMap[tChar] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        final IsomorphicStrings isomorphicStrings = new IsomorphicStrings();

        String s = "egg";
        String t = "add";
        System.out.println(isomorphicStrings.isIsomorphic(s, t));

        s = "foo";
        t = "bar";
        System.out.println(isomorphicStrings.isIsomorphic(s, t));

        s = "paper";
        t = "title";
        System.out.println(isomorphicStrings.isIsomorphic(s, t));
    }
}
