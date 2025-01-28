package org.redquark.leetcoding.twopointers;

public class LongPressedName {

    public boolean isLongPressedName(String name, String typed) {
        // Lengths of both strings
        final int m = name.length();
        final int n = typed.length();
        // Pointers to keep track of current indices in both strings
        int i = 0;
        int j = 0;
        // Process both strings
        while (j < n) {
            // Check if current characters are same
            if (i < m && name.charAt(i) == typed.charAt(j)) {
                i++;
            }
            // We will hit this condition only if the characters from
            // name and typed do not match. This can happen only in two
            // scenarios:
            // 1. Either the first character of two strings doesn't match
            // 2. Or any mismatched character is present in between.
            else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }
            j++;
        }
        // Check if all the characters of name string are exhausted
        return i == m;
    }

    public static void main(String[] args) {
        final LongPressedName longPressedName = new LongPressedName();

        String name = "alex";
        String typed = "aaleex";
        System.out.println(longPressedName.isLongPressedName(name, typed));

        name = "saeed";
        typed = "ssaaedd";
        System.out.println(longPressedName.isLongPressedName(name, typed));

        name = "rick";
        typed = "kric";
        System.out.println(longPressedName.isLongPressedName(name, typed));

        name = "alex";
        typed = "aaleexa";
        System.out.println(longPressedName.isLongPressedName(name, typed));

        name = "vtkgn";
        typed = "vttkgnn";
        System.out.println(longPressedName.isLongPressedName(name, typed));

        name = "pyplrz";
        typed = "ppyypllr";
        System.out.println(longPressedName.isLongPressedName(name, typed));
    }
}
