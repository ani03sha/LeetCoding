package org.redquark.leetcoding.strings;

public class TownJudge {

    public int findJudge(int n, int[][] trust) {
        // Arrays to represent net degrees
        final int[] netDegrees = new int[n + 1];
        // Populate both arrays
        for (int[] t : trust) {
            netDegrees[t[0]]--;
            netDegrees[t[1]]++;
        }
        // Now, find a node that has indegree == 0 and outdegree == n - 1
        for (int i = 1; i <= n; i++) {
            if (netDegrees[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final TownJudge townJudge = new TownJudge();

        int n = 2;
        int[][] trust = new int[][]{{1, 2}};
        System.out.println(townJudge.findJudge(n, trust));

        n = 3;
        trust = new int[][]{{1, 3}, {2, 3}};
        System.out.println(townJudge.findJudge(n, trust));

        trust = new int[][]{{1, 3}, {2, 3}, {3, 1}};
        System.out.println(townJudge.findJudge(n, trust));
    }
}
