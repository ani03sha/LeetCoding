package org.redquark.leetcoding.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create graph from the prerequisites array
        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        // Array to store indegree of each node
        final int[] indegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            indegrees[prerequisite[0]]++;
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }
        // Queue to store all nodes with zero indegree
        final Deque<Integer> zeroIndegreeNodes = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                zeroIndegreeNodes.offer(i);
            }
        }
        // List to store course order
        final List<Integer> order = new ArrayList<>();
        // Process until we have elements in the queue
        while (!zeroIndegreeNodes.isEmpty()) {
            // Get the current node
            final int current = zeroIndegreeNodes.remove();
            order.add(current);
            for (int neighbor : graph.get(current)) {
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0) {
                    zeroIndegreeNodes.offer(neighbor);
                }
            }
        }
        if (order.size() != numCourses) {
            return new int[0];
        }
        // Array to store final output
        final int[] result = new int[order.size()];
        for (int i = 0; i < numCourses; i++) {
            result[i] = order.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        final CourseScheduleII courseScheduleII = new CourseScheduleII();

        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        System.out.println(Arrays.toString(courseScheduleII.findOrder(numCourses, prerequisites)));

        numCourses = 2;
        prerequisites = new int[][]{{1, 0}, {0, 1}};
        System.out.println(Arrays.toString(courseScheduleII.findOrder(numCourses, prerequisites)));
    }
}
