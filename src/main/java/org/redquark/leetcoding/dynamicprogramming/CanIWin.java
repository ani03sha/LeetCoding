package org.redquark.leetcoding.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class CanIWin {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // Check if desiredTotal is possible with the range of
        // maxChoosableInteger
        if (maxChoosableInteger * (1 + maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        // Lookup table to store if current used number's state
        // gives us the desiredTotal
        final Map<Integer, Boolean> lookup = new HashMap<>();
        // Perform DFS on the maxChoosableInteger
        return dfs(0, 0, maxChoosableInteger, desiredTotal, lookup);
    }

    private boolean dfs(int usedState, int currentTotal, int maxChoosableInteger, int desiredTotal, Map<Integer, Boolean> lookup) {
        // If we have already computed this state
        if (lookup.containsKey(usedState)) {
            return lookup.get(usedState);
        }
        // Assume that the current player cannot win
        boolean canWin = false;
        // Process all integers until maxChoosableInteger
        for (int i = 1; i <= maxChoosableInteger; i++) {
            // If the number is not chosen yet
            if (((usedState >> (i - 1)) & 1) == 0) {
                // If choosing the current number, we reach or exceed
                // desiredTotal, or the opposition cannot win
                if (currentTotal + i >= desiredTotal || !dfs(usedState | (1 << (i - 1)), currentTotal + i, maxChoosableInteger, desiredTotal, lookup)) {
                    canWin = true;
                    break;
                }
            }
        }
        lookup.put(usedState, canWin);
        return canWin;
    }

    public static void main(String[] args) {
        final CanIWin canIWin = new CanIWin();

        int maxChoosableInteger = 10;
        int desiredTotal = 11;
        System.out.println(canIWin.canIWin(maxChoosableInteger, desiredTotal));

        maxChoosableInteger = 10;
        desiredTotal = 0;
        System.out.println(canIWin.canIWin(maxChoosableInteger, desiredTotal));

        maxChoosableInteger = 10;
        desiredTotal = 1;
        System.out.println(canIWin.canIWin(maxChoosableInteger, desiredTotal));
    }
}
