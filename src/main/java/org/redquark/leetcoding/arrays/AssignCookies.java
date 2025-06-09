package org.redquark.leetcoding.arrays;

import java.util.Arrays;

public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        // Sort both arrays
        Arrays.sort(g);
        Arrays.sort(s);
        // Indices to traverse both arrays
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }

    public static void main(String[] args) {
        final AssignCookies assignCookies = new AssignCookies();

        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 1};
        System.out.println(assignCookies.findContentChildren(g, s)); // 1

        g = new int[]{1, 2};
        s = new int[]{1, 2, 3};
        System.out.println(assignCookies.findContentChildren(g, s)); // 2

        g = new int[]{10, 9, 8, 7};
        s = new int[]{5, 6, 7, 8};
        System.out.println(assignCookies.findContentChildren(g, s)); // 2
    }
}
