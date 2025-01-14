package org.redquark.leetcoding.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Special case
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
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
        // Total number of courses that can be taken
        int courses = 0;
        // Process until we have elements in the queue
        while (!zeroIndegreeNodes.isEmpty()) {
            courses++;
            // Get the current node
            final int current = zeroIndegreeNodes.remove();
            for (int neighbor : graph.get(current)) {
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0) {
                    zeroIndegreeNodes.offer(neighbor);
                }
            }
        }
        return numCourses == courses;
    }

    public static void main(String[] args) {
        final CourseSchedule courseSchedule = new CourseSchedule();

        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        System.out.println(courseSchedule.canFinish(numCourses, prerequisites));

        numCourses = 2;
        prerequisites = new int[][]{{1, 0}, {0, 1}};
        System.out.println(courseSchedule.canFinish(numCourses, prerequisites));
    }
}
