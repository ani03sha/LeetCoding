package org.redquark.leetcoding.dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffers {

    public int shoppingOffers(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs) {
        // Map to store a combination of specific needs fulfilled and corresponding cost
        final Map<List<Integer>, Integer> lookup = new HashMap<>();
        // Perform DFS on the arrays
        return dfs(prices, specials, needs, lookup);
    }

    private int dfs(List<Integer> prices, List<List<Integer>> specials, List<Integer> needs, Map<List<Integer>, Integer> lookup) {
        // If we have already calculated this combination
        if (lookup.containsKey(needs)) {
            return lookup.get(needs);
        }
        final int n = needs.size();
        // Minimum cost to fulfill all needs
        int minCost = 0;
        for (int i = 0; i < n; i++) {
            minCost += needs.get(i) * prices.get(i);
        }
        // Check all the special offers to find minCost
        for (List<Integer> special : specials) {
            // List to store new needs
            final List<Integer> newNeeds = new ArrayList<>();
            boolean isValidOffer = true;
            for (int i = 0; i < n; i++) {
                if (special.get(i) > needs.get(i)) {
                    isValidOffer = false;
                    break;
                }
                newNeeds.add(needs.get(i) - special.get(i));
            }
            if (isValidOffer) {
                minCost = Math.min(minCost, special.get(n) + dfs(prices, specials, newNeeds, lookup));
            }
        }
        lookup.put(needs, minCost);
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
