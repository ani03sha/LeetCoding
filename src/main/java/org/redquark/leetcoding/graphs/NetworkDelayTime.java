package org.redquark.leetcoding.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        // Special case
        if (times == null || times.length == 0) {
            return 0;
        }
        // Map to represent weighted graph
        final Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }
        // Sort the edges connecting to every node
        for (int vertex : graph.keySet()) {
            graph.get(vertex).sort(Comparator.comparingInt(a -> a[0]));
        }
        // Array to store the time when the signal is first received
        final int[] firstSignalReceived = new int[n + 1];
        Arrays.fill(firstSignalReceived, Integer.MAX_VALUE);
        // Perform DFS on the graph
        dfs(graph, firstSignalReceived, k, 0);
        // Calculate minimum time taken
        int timeTaken = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            timeTaken = Math.max(timeTaken, firstSignalReceived[i]);
        }
        return timeTaken == Integer.MAX_VALUE ? -1 : timeTaken;
    }

    private void dfs(Map<Integer, List<int[]>> graph, int[] firstSignalReceived, int currentNode, int currentTime) {
        // If time taken is already equal to or more than
        // the time taken to reach this node, we return
        if (currentTime >= firstSignalReceived[currentNode]) {
            return;
        }
        // Fastest signal time to reach this node
        firstSignalReceived[currentNode] = currentTime;
        // If we don't have the mapping
        if (!graph.containsKey(currentNode)) {
            return;
        }
        // Check for neighbors
        for (int[] neighbor : graph.get(currentNode)) {
            dfs(graph, firstSignalReceived, neighbor[0], neighbor[1] + currentTime);
        }
    }

    public static void main(String[] args) {
        final NetworkDelayTime networkDelayTime = new NetworkDelayTime();

        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;
        System.out.println(networkDelayTime.networkDelayTime(times, n, k));

        times = new int[][]{{1, 2, 1}};
        n = 2;
        k = 1;
        System.out.println(networkDelayTime.networkDelayTime(times, n, k));

        times = new int[][]{{1, 2, 1}};
        n = 2;
        k = 2;
        System.out.println(networkDelayTime.networkDelayTime(times, n, k));
    }
}
