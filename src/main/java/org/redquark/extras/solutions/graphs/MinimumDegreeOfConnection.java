package org.redquark.extras.solutions.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinimumDegreeOfConnection {

    public int findMinimumDegreeOfConnection(int[][] connections, int start, int end) {
        // Create a graph represented as an adjacency list
        final Map<Integer, List<Integer>> graph = buildGraph(connections);
        // Edge case: if start or end is not in the graph, return -1
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1;
        }
        // If start and end are the same, return 0
        if (start == end) {
            return 0;
        }
        // Initialize start and end queues for bidirectional BFS
        Queue<Integer> startQueue = new LinkedList<>();
        Queue<Integer> endQueue = new LinkedList<>();
        // Initialize a map to track visited nodes from both ends
        Set<Integer> startVisited = new HashSet<>();
        Set<Integer> endVisited = new HashSet<>();
        // Add the start and end nodes to their respective queues
        startQueue.offer(start);
        endQueue.offer(end);
        // Mark the start and end nodes as visited
        startVisited.add(start);
        endVisited.add(end);
        // Define the degree of connection
        int degree = 0;
        // Perform BFS from both ends until they meet
        while (!startQueue.isEmpty() && !endQueue.isEmpty()) {
            degree++;
            // Always expand the smaller queue first
            if (startQueue.size() > endQueue.size()) {
                final Queue<Integer> tempQueue = startQueue;
                startQueue = endQueue;
                endQueue = tempQueue;
                final Set<Integer> tempVisited = startVisited;
                startVisited = endVisited;
                endVisited = tempVisited;
            }
            // Process all nodes at the current degree
            int size = startQueue.size();
            for (int i = 0; i < size; i++) {
                int currentNode = startQueue.remove();
                // Check all neighbors of the current node
                for (int neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
                    // If the neighbor is already visited from the other end, return the degree
                    if (endVisited.contains(neighbor)) {
                        return degree;
                    }
                    // If the neighbor has not been visited from this end, add it to the queue and mark as visited
                    if (!startVisited.contains(neighbor)) {
                        startVisited.add(neighbor);
                        startQueue.offer(neighbor);
                    }
                }
            }
        }
        return -1; // If no connection is found, return -1
    }

    public List<Integer> findPath(int[][] connections, int start, int end) {
        // Create a graph represented as an adjacency list
        final Map<Integer, List<Integer>> graph = buildGraph(connections);
        // Edge case: if start or end is not in the graph, return empty list
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return List.of();
        }
        // Mapping of nodes to their parents
        final Map<Integer, Integer> parents = new HashMap<>();
        // Initialize a queue for BFS
        final Queue<Integer> nodes = new LinkedList<>();
        // Initialize a set to track visited nodes
        final Set<Integer> visited = new HashSet<>();
        // Add the start node to the queue
        nodes.offer(start);
        // Mark the start node as visited
        visited.add(start);
        // Perform BFS to find the path
        while (!nodes.isEmpty()) {
            final int node = nodes.remove();
            // If we reach the end node, construct the path
            if (node == end) {
                break;
            }
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                // If the neighbor has not been visited, add it to the queue and mark as visited
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parents.put(neighbor, node);
                    nodes.offer(neighbor);
                }
            }
        }
        if (!parents.containsKey(end) && start != end) {
            return List.of();
        }
        // Construct the path from end to start using the 'parents' map
        final List<Integer> path = new ArrayList<>();
        int currentNode = end;
        while (currentNode != start) {
            path.addFirst(currentNode); // Add to the front of the list
            currentNode = parents.get(currentNode);
        }
        path.addFirst(start); // Add the start node at the beginning
        return path;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] connections) {
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];
            graph.computeIfAbsent(u, _ -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, _ -> new ArrayList<>()).add(u);
        }
        return graph;
    }

    public static void main(String[] args) {
        final MinimumDegreeOfConnection minimumDegreeOfConnection = new MinimumDegreeOfConnection();

        int[][] connections = {
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 7},
                {7, 8},
                {8, 9},
                {9, 10}
        };
        int start = 1;
        int end = 10;
        int degree = minimumDegreeOfConnection.findMinimumDegreeOfConnection(connections, start, end);
        System.out.println("Minimum Degree of Connection from " + start + " to " + end + ": " + degree);

        final List<Integer> path = minimumDegreeOfConnection.findPath(connections, start, end);
        System.out.println("Path from " + start + " to " + end + ": " + path);

        // Another complex example
        int[][] complexConnections = {
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {3, 6},
                {4, 7},
                {5, 8},
                {6, 9},
                {7, 10},
                {8, 11},
                {9, 12}
        };
        int complexStart = 1;
        int complexEnd = 12;
        int complexDegree = minimumDegreeOfConnection.findMinimumDegreeOfConnection(complexConnections, complexStart, complexEnd);
        System.out.println("Minimum Degree of Connection from " + complexStart + " to " + complexEnd + ": " + complexDegree);
        final List<Integer> complexPath = minimumDegreeOfConnection.findPath(complexConnections, complexStart, complexEnd);
        System.out.println("Path from " + complexStart + " to " + complexEnd + ": " + complexPath);
    }
}
