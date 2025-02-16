package org.redquark.leetcoding.strings;

public class RotateString {

    public boolean rotateString(String s, String goal) {
        if (goal.length() != s.length()) {
            return false;
        }
        // Create a string by appending s twice
        final String temp = s.concat(s);
        // Check if goal contains in temp
        return temp.contains(goal);
    }

    public static void main(String[] args) {
        final RotateString rotateString = new RotateString();

        String s = "abcde";
        String goal = "cdeab";
        System.out.println(rotateString.rotateString(s, goal));

        s = "abcde";
        goal = "abced";
        System.out.println(rotateString.rotateString(s, goal));
    }
}
