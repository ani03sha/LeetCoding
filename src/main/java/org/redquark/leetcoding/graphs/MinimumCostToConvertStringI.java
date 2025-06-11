package org.redquark.leetcoding.graphs;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumCostToConvertStringI {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Let's build a directed graph first where edge will be
        // from original[i] to changes[i] with weight cost[i]
        final List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < original.length; i++) {
            graph.get(original[i] - 'a').add(new int[]{changed[i] - 'a', cost[i]});
        }
        // Find the shortest path for all possible conversions using Dijkstra's
        final long[][] minCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            minCosts[i] = findMinCost(i, graph);
        }
        // Calculate the total cost of converting the source to target
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            // If the characters mismatch, we convert
            if (source.charAt(i) != target.charAt(i)) {
                long minCost = minCosts[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
                if (minCost == -1) {
                    return -1;
                }
                totalCost += minCost;
            }
        }
        return totalCost;
    }

    private long[] findMinCost(int source, List<List<int[]>> graph) {
        // Min heap to store characters with their conversion cost
        final Queue<AbstractMap.SimpleEntry<Long, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingLong(AbstractMap.SimpleEntry::getKey));
        // Add the source with cost 0
        minHeap.offer(new AbstractMap.SimpleEntry<>(0L, source));
        // Array to store min cost to each character from source
        final long[] minCosts = new long[26];
        Arrays.fill(minCosts, -1L);
        while (!minHeap.isEmpty()) {
            final AbstractMap.SimpleEntry<Long, Integer> current = minHeap.remove();
            final long cost = current.getKey();
            final int currentCharacter = current.getValue();
            if (minCosts[currentCharacter] != -1L && minCosts[currentCharacter] < cost) {
                continue;
            }
            // Explore all possible conversions
            for (int[] conversion : graph.get(currentCharacter)) {
                final int target = conversion[0];
                final long conversionCost = conversion[1];
                final long newTotalCost = cost + conversionCost;
                if (minCosts[target] == -1 || newTotalCost < minCosts[target]) {
                    minCosts[target] = newTotalCost;
                    minHeap.offer(new AbstractMap.SimpleEntry<>(newTotalCost, target));
                }
            }
        }
        return minCosts;
    }

    public static void main(String[] args) {
        final MinimumCostToConvertStringI minimumCostToConvertStringI = new MinimumCostToConvertStringI();

        String source = "abcd";
        String target = "acbe";
        char[] original = {'a', 'b', 'c', 'c', 'e', 'd'};
        char[] changed = {'b', 'c', 'b', 'e', 'b', 'e'};
        int[] cost = {2, 5, 5, 1, 2, 20};
        long result = minimumCostToConvertStringI.minimumCost(source, target, original, changed, cost);
        System.out.println("Minimum cost to convert: " + result); // Expected output: 28

        source = "aaaa";
        target = "bbbb";
        original = new char[]{'a', 'c'};
        changed = new char[]{'c', 'b'};
        cost = new int[]{1, 2};
        result = minimumCostToConvertStringI.minimumCost(source, target, original, changed, cost);
        System.out.println("Minimum cost to convert: " + result); // Expected output: 12

        source = "abcd";
        target = "abce";
        original = new char[]{'a'};
        changed = new char[]{'e'};
        cost = new int[]{10000};
        result = minimumCostToConvertStringI.minimumCost(source, target, original, changed, cost);
        System.out.println("Minimum cost to convert: " + result); // Expected output: -1
    }
}
