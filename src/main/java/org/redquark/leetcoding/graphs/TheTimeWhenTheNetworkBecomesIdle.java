package org.redquark.leetcoding.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TheTimeWhenTheNetworkBecomesIdle {

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        // Total number of servers
        final int n = patience.length;
        // Build graph using an adjacency list
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            final int u = edge[0];
            final int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // Queue to perform BFS
        final Queue<Integer> nodes = new LinkedList<>();
        nodes.offer(0);
        // Visited array
        final boolean[] visited = new boolean[n];
        visited[0] = true;
        // Idle time
        int idleTime = 0;
        // Distance from master server (0)
        int distance = 0;
        // Perform BFS
        while (!nodes.isEmpty()) {
            distance++;
            // Total travel time
            int travelTime = 2 * distance;
            final int size = nodes.size();
            for (int i = 0; i < size; i++) {
                final int node = nodes.remove();
                // Process its neighbors
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        nodes.offer(neighbor);
                        // Calculate message's last transmission time
                        final int lastTransmissionTime = (travelTime - 1) / patience[neighbor] * patience[neighbor];
                        int totalIdleTime = lastTransmissionTime + travelTime + 1;
                        idleTime = Math.max(idleTime, totalIdleTime);
                    }
                }
            }
        }
        return idleTime;
    }

    public static void main(String[] args) {
        final TheTimeWhenTheNetworkBecomesIdle theTimeWhenTheNetworkBecomesIdle = new TheTimeWhenTheNetworkBecomesIdle();

        int[][] edges = {
                {0, 1},
                {1, 2},
                {0, 3},
                {3, 4}
        };
        int[] patience = {0, 2, 1, 3, 2};
        int result = theTimeWhenTheNetworkBecomesIdle.networkBecomesIdle(edges, patience);
        System.out.println(result);

        edges = new int[][]{
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 4}
        };
        patience = new int[]{0, 10, 10, 10, 10};
        result = theTimeWhenTheNetworkBecomesIdle.networkBecomesIdle(edges, patience);
        System.out.println(result);
    }
}
