package org.redquark.leetcoding.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffers {

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // Memoization map to store the minimum cost for given needs
        final Map<List<Integer>, Integer> memo = new HashMap<>();
        // Start the depth-first search to find the minimum cost
        return dfs(price, special, needs, memo);
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> memo) {
        // Base case: if needs is empty, return 0
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }
        int n = needs.size();
        int minCost = 0;
        // Calculate the cost without any special offers
        for (int i = 0; i < n; i++) {
            minCost += needs.get(i) * price.get(i);
        }
        // Try each special offer
        for (List<Integer> offer : special) {
            // Check if the offer can be applied to the current needs
            List<Integer> newNeeds = new ArrayList<>();
            // Assume the offer is valid
            boolean validOffer = true;
            // Check if the offer can be applied
            for (int i = 0; i < n; i++) {
                // If the offer exceeds the needs, it is not valid
                if (offer.get(i) > needs.get(i)) {
                    validOffer = false;
                    break;
                }
                // Calculate the new needs after applying the offer
                newNeeds.add(needs.get(i) - offer.get(i));
            }
            // If the offer is valid, calculate the cost with this offer
            if (validOffer) {
                minCost = Math.min(minCost, offer.get(n) + dfs(price, special, newNeeds, memo));
            }
        }
        // Store the minimum cost for the current needs in the memoization map
        memo.put(needs, minCost);
        return minCost;
    }

    public static void main(String[] args) {
        final ShoppingOffers shoppingOffers = new ShoppingOffers();

        List<Integer> price = List.of(2, 5);
        List<List<Integer>> special = List.of(
                List.of(3, 0, 5),
                List.of(1, 2, 10)
        );
        List<Integer> needs = List.of(3, 2);
        int result = shoppingOffers.shoppingOffers(price, special, needs);
        System.out.println(result); // Output: 14

        price = List.of(2, 3, 4);
        special = List.of(
                List.of(1, 1, 0, 4),
                List.of(2, 2, 1, 9)
        );
        needs = List.of(1, 2, 1);
        result = shoppingOffers.shoppingOffers(price, special, needs);
        System.out.println(result); // Output: 11
    }
}
