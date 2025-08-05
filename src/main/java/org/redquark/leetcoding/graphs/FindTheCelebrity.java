package org.redquark.leetcoding.graphs;

import org.redquark.leetcoding.utils.Relation;

public class FindTheCelebrity extends Relation {

    FindTheCelebrity(int[][] graph) {
        super(graph);
    }

    public int findCelebrity(int n) {
        // Assume 0 is a celebrity
        int celebrity = 0;
        for (int i = 0; i < n; i++) {
            // Check if celebrity knows i
            if (knows(celebrity, i)) {
                // If current celebrity knows i, then it cannot
                // be the celebrity, but since i is known by someone,
                // it is a candidate for being a celebrity.
                celebrity = i;
            }
        }
        // Recheck our tests
        for (int i = 0; i < n; i++) {
            if (celebrity != i) {
                // Two checks
                // 1. If celebrity knows i => violation
                // 2. If 'i' does not know celebrity => violation
                if (knows(celebrity, i) || !knows(i, celebrity)) {
                    return -1;
                }
            }
        }
        return celebrity;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 1, 0}, {0, 1, 0}, {1, 1, 1}};
        FindTheCelebrity findTheCelebrity = new FindTheCelebrity(graph);
        System.out.println(findTheCelebrity.findCelebrity(3));
    }
}
