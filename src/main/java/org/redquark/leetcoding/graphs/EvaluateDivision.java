package org.redquark.leetcoding.graphs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Create graph from the equations and values
        final Map<String, Map<String, Double>> graph = createGraph(equations, values);
        final int n = queries.size();
        // Array to store results
        final double[] answers = new double[n];
        // Evaluate queries
        for (int i = 0; i < n; i++) {
            final String start = queries.get(i).getFirst();
            final String end = queries.get(i).getLast();
            // Set to store visited nodes
            final Set<String> visited = new HashSet<>();
            // Evaluate by DFS
            answers[i] = evaluateByDFS(graph, visited, start, end);
        }
        return answers;
    }

    private double evaluateByDFS(Map<String, Map<String, Double>> graph, Set<String> visited, String start, String end) {
        if (!graph.containsKey(start)) {
            return -1.0;
        }
        // If we have found direct edge between start and end,
        // we will return its value
        if (graph.get(start).containsKey(end)) {
            return graph.get(start).get(end);
        }
        // Mark start node as visited
        visited.add(start);
        // Loop through all the neighbors of the start node,
        // until we reach the end node
        for (Map.Entry<String, Double> neigbor : graph.get(start).entrySet()) {
            if (!visited.contains(neigbor.getKey())) {
                final double weight = evaluateByDFS(graph, visited, neigbor.getKey(), end);
                if (weight != -1.0) {
                    return neigbor.getValue() * weight;
                }
            }
        }
        return -1.0;
    }

    private Map<String, Map<String, Double>> createGraph(List<List<String>> equations, double[] values) {
        final Map<String, Map<String, Double>> graph = new HashMap<>();
        // Process all equations
        for (int i = 0; i < equations.size(); i++) {
            final String u = equations.get(i).getFirst();
            final String v = equations.get(i).getLast();
            graph.putIfAbsent(u, new HashMap<>());
            graph.putIfAbsent(v, new HashMap<>());
            graph.get(u).put(v, values[i]);
            graph.get(v).put(u, 1 / values[i]);
        }
        return graph;
    }

    public static void main(String[] args) {
        final EvaluateDivision evaluateDivision = new EvaluateDivision();

        List<List<String>> equations = List.of(List.of("a", "b"), List.of("b", "c"));
        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = List.of(List.of("a", "b"), List.of("b", "a"), List.of("a", "e"), List.of("a", "a"), List.of("x", "x"));
        System.out.println(Arrays.toString(evaluateDivision.calcEquation(equations, values, queries)));
    }
}
