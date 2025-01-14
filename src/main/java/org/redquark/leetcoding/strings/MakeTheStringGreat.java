package org.redquark.leetcoding.strings;

public class MakeTheStringGreat {

    public String makeGood(String s) {
        // Special case
        if (s == null || s.isEmpty()) {
            return s;
        }
        // StringBuilder to store final result
        final StringBuilder result = new StringBuilder();
        // Process the string
        for (char c : s.toCharArray()) {
            if (!result.isEmpty() && Math.abs(c - result.charAt(result.length() - 1)) == 32) {
                result.deleteCharAt(result.length() - 1);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final MakeTheStringGreat makeTheStringGreat = new MakeTheStringGreat();

        System.out.println(makeTheStringGreat.makeGood("leEeetcode"));
        System.out.println(makeTheStringGreat.makeGood("abBAcC"));
        System.out.println(makeTheStringGreat.makeGood("s"));
    }
}
