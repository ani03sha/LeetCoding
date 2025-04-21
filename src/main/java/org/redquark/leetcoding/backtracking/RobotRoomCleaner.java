package org.redquark.leetcoding.backtracking;

import org.redquark.leetcoding.utils.Robot;
import org.redquark.leetcoding.utils.RobotImpl;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {

    // Set to mark visited cells
    private final Set<String> visited = new HashSet<>();
    // Four directions
    private final int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // Instance of the Robot
    private Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        // Perform backtracking from the initial cell
        backtrack(0, 0, 0);
    }

    private void backtrack(int i, int j, int direction) {
        // Clean the current room
        this.robot.clean();
        // Mark the cell as visited
        final String key = i + "_" + j;
        this.visited.add(key);
        // Explore all four directions
        for (int k = 0; k < 4; k++) {
            final int newDirection = (direction + k) % 4;
            // Next cell's coordinates
            final int x = i + this.directions[newDirection][0];
            final int y = j + this.directions[newDirection][1];
            // If the next cell is not visited and cleaned, move and
            // continue DFS
            if (!this.visited.contains(x + "_" + y) && this.robot.move()) {
                backtrack(x, y, newDirection);
                // Move back to previous cell (backtracking)
                this.robot.turnRight();
                this.robot.turnRight();
                this.robot.move();
                // Reorient to the original direction
                this.robot.turnRight();
                this.robot.turnRight();
            }
            // Turn the robot to the next direction
            this.robot.turnRight();
        }
    }

    public static void main(String[] args) {
        int[][] room = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}
        };

        RobotImpl robot = new RobotImpl(room, 1, 3);

        RobotRoomCleaner sol = new RobotRoomCleaner();
        sol.cleanRoom(robot); // calls your logic on this mock robot

        robot.printCleanedMap();
    }
}
