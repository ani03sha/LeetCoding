package org.redquark.leetcoding.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseScheduleIV {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Get the topological order first
        final Map<Integer, Set<Integer>> prerequisiteMap = getTopologicalOrder(numCourses, prerequisites);
        // Evaluate queries
        final List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            if (prerequisiteMap.containsKey(query[1]) && prerequisiteMap.get(query[1]).contains(query[0])) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

    private Map<Integer, Set<Integer>> getTopologicalOrder(int numCourses, int[][] prerequisites) {
        // Create a graph
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        // Array to keep track of indegrees
        final int[] indegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            indegrees[prerequisite[1]]++;
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }
        // Nodes with zero indegree
        final Deque<Integer> zeroIndegreeNodes = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                zeroIndegreeNodes.offer(i);
            }
        }
        // Map to store prerequisites of every node
        final Map<Integer, Set<Integer>> prerequisiteMap = new HashMap<>();
        int index = 0;
        while (!zeroIndegreeNodes.isEmpty()) {
            int node = zeroIndegreeNodes.remove();
            for (int neighbor : graph.get(node)) {
                if (!prerequisiteMap.containsKey(neighbor)) {
                    prerequisiteMap.put(neighbor, new HashSet<>());
                }
                prerequisiteMap.get(neighbor).add(node);
                if (prerequisiteMap.containsKey(node)) {
                    prerequisiteMap.get(neighbor).addAll(prerequisiteMap.get(node));
                }
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0) {
                    zeroIndegreeNodes.offer(neighbor);
                }
            }
        }
        return prerequisiteMap;
    }

    public static void main(String[] args) {
        final CourseScheduleIV courseScheduleIV = new CourseScheduleIV();

        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        int[][] queries = new int[][]{{0, 1}, {1, 0}};
        System.out.println(courseScheduleIV.checkIfPrerequisite(numCourses, prerequisites, queries));

        prerequisites = new int[][]{};
        queries = new int[][]{{1, 9}, {0, 1}};
        System.out.println(courseScheduleIV.checkIfPrerequisite(numCourses, prerequisites, queries));

        numCourses = 3;
        prerequisites = new int[][]{{1, 2}, {1, 0}, {2, 0}};
        queries = new int[][]{{1, 0}, {1, 2}};
        System.out.println(courseScheduleIV.checkIfPrerequisite(numCourses, prerequisites, queries));
    }
}
