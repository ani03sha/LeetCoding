package org.redquark.leetcoding.graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WallsAndGates {

    public void wallsAndGates(int[][] rooms) {
        // Special case
        if (rooms == null || rooms.length == 0) {
            return;
        }
        // Queue to store positions of all gates
        final Deque<int[]> gates = new ArrayDeque<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    gates.offer(new int[]{i, j});
                }
            }
        }
        // Four directions
        final int[][] directions = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        // Distance from nearest gate
        int distance = 0;
        // Process until we have elements in queue
        while (!gates.isEmpty()) {
            distance++;
            int size = gates.size();
            for (int i = 0; i < size; i++) {
                final int[] gate = gates.remove();
                // Check in all four directions
                for (int[] direction : directions) {
                    final int x = gate[0] + direction[0];
                    final int y = gate[1] + direction[1];
                    // Check if the cell represent by x, y is a valid cell or not
                    if (x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length && rooms[x][y] == Integer.MAX_VALUE) {
                        rooms[x][y] = distance;
                        gates.offer(new int[]{x, y});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        final WallsAndGates wallsAndGates = new WallsAndGates();

        int[][] rooms = new int[][]{
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}};
        wallsAndGates.wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));

        rooms = new int[][]{
                {-1}
        };
        wallsAndGates.wallsAndGates(rooms);
        System.out.println(Arrays.deepToString(rooms));
    }
}
