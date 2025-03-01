package org.redquark.leetcoding.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Create a graph using flights
        final Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            final int source = flight[0];
            final int destination = flight[1];
            final int cost = flight[2];
            graph.putIfAbsent(source, new ArrayList<>());
            graph.get(source).add(new int[]{destination, cost});
        }
        // Distance table: tracks the minimum cost to reach a node with
        // at most 'k' stops
        final int[] minimumCosts = new int[n];
        Arrays.fill(minimumCosts, Integer.MAX_VALUE);
        minimumCosts[src] = 0;
        // BFS queue: {node, current cost, stops remaining}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int currCost = current[1];
            int stops = current[2];

            // Stop processing if we exceed the allowed stops
            if (stops > k) continue;

            // Explore neighbors
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0];
                    int nextCost = currCost + neighbor[1];
                    // If we found a cheaper way to reach nextNode within allowed stops, update cost
                    if (nextCost < minimumCosts[nextNode]) {
                        minimumCosts[nextNode] = nextCost;
                        queue.offer(new int[]{nextNode, nextCost, stops + 1});
                    }
                }
            }
        }
        return minimumCosts[dst] == Integer.MAX_VALUE ? -1 : minimumCosts[dst];
    }

    public static void main(String[] args) {
        final CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();

        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int n = 4;
        int src = 0;
        int dst = 3;
        int k = 1;
        System.out.println(cheapestFlightsWithinKStops.findCheapestPrice(n, flights, src, dst, k));

        flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        n = 3;
        src = 0;
        dst = 2;
        k = 1;
        System.out.println(cheapestFlightsWithinKStops.findCheapestPrice(n, flights, src, dst, k));

        flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        n = 3;
        src = 0;
        dst = 2;
        k = 0;
        System.out.println(cheapestFlightsWithinKStops.findCheapestPrice(n, flights, src, dst, k));
    }
}
