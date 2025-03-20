package org.redquark.leetcoding.strings;

public class MinimumSwapsToMakeStringsEqual {

    public int minimumSwap(String s1, String s2) {
        // Variables to keep track of x and y in s1 and s2
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        // Process both strings
        for (int i = 0; i < s1.length(); i++) {
            final char c1 = s1.charAt(i);
            final char c2 = s2.charAt(i);
            // If both characters are same, we don't have to swap
            if (c1 == c2) {
                continue;
            }
            // Calculate the number of x and y in s1 and s2
            if (c1 == 'x') {
                x1++;
            } else {
                y1++;
            }
            if (c2 == 'x') {
                x2++;
            } else {
                y2++;
            }
        }
        // If either x and y count is odd, we can't make them equal
        if ((x1 + x2) % 2 == 1 || (y1 + y2) % 2 == 1) {
            return -1;
        }
        // Count the number of swaps
        return x1 / 2 + y1 / 2 + (x1 % 2) * 2;
    }

    public static void main(String[] args) {
        final MinimumSwapsToMakeStringsEqual minimumSwapsToMakeStringsEqual = new MinimumSwapsToMakeStringsEqual();

        String s1 = "xx";
        String s2 = "yy";
        System.out.println(minimumSwapsToMakeStringsEqual.minimumSwap(s1, s2));

        s1 = "xy";
        s2 = "yx";
        System.out.println(minimumSwapsToMakeStringsEqual.minimumSwap(s1, s2));

        s1 = "xx";
        s2 = "xy";
        System.out.println(minimumSwapsToMakeStringsEqual.minimumSwap(s1, s2));
    }
}
