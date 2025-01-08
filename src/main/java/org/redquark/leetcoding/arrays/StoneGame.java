package org.redquark.leetcoding.arrays;

public class StoneGame {

    public boolean stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        /*
         * Alice is first to pick pile.
         * piles.length is even, and this lead to an interesting fact:
         * Alice can always pick odd piles or always pick even piles!
         *
         * For example,
         * If Alice wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
         * she picks first piles[0], then Bob can pick either piles[1] or piles[n - 1].
         * Every turn, Alice can always pick even indexed piles and Bob can only pick odd indexed piles.
         *
         * In the description, we know that sum(piles) is odd.
         * If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
         * If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.
         *
         * So, Alex always defeats Lee in this game.
         */
        return true;
    }

    public static void main(String[] args) {
        final StoneGame stoneGame = new StoneGame();

        int[] piles = new int[]{5, 3, 4, 5};
        System.out.println(stoneGame.stoneGame(piles));

        piles = new int[]{3, 7, 2, 3};
        System.out.println(stoneGame.stoneGame(piles));
    }
}
