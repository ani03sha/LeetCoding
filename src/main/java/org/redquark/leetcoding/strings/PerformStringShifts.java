package org.redquark.leetcoding.strings;

public class PerformStringShifts {

    public String stringShift(String s, int[][] shifts) {
        // Total number of shifts
        int totalShifts = 0;
        // Process the "shifts" array
        for (int[] shift : shifts) {
            if (shift[0] == 0) {
                totalShifts -= shift[1];
            } else {
                totalShifts += shift[1];
            }
        }
        final int n = s.length();
        // Effective shifts
        totalShifts = (totalShifts % n + n) % n;
        // Find the final string by substrings
        return s.substring(n - totalShifts) + s.substring(0, n - totalShifts);
    }

    public static void main(String[] args) {
        final PerformStringShifts performStringShifts = new PerformStringShifts();

        String s = "abc";
        int[][] shifts = new int[][]{{0, 1}, {1, 2}};
        System.out.println(performStringShifts.stringShift(s, shifts));

        s = "abcdefg";
        shifts = new int[][]{{1, 1}, {1, 1}, {0, 2}, {1, 3}};
        System.out.println(performStringShifts.stringShift(s, shifts));
    }
}
