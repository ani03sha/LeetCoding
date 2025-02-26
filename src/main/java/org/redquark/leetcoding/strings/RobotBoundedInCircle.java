package org.redquark.leetcoding.strings;

public class RobotBoundedInCircle {

    public boolean isRobotBounded(String instructions) {
        // Special case
        if (instructions == null || instructions.isEmpty()) {
            return true;
        }
        // Abscissa and ordinate
        int x = 0;
        int y = 0;
        // Directions
        char direction = 'N';
        // Process all instructions
        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                if (direction == 'N') {
                    y++;
                } else if (direction == 'S') {
                    y--;
                } else if (direction == 'W') {
                    x++;
                } else {
                    x--;
                }
            } else if (c == 'L') {
                if (direction == 'N') {
                    direction = 'W';
                } else if (direction == 'W') {
                    direction = 'S';
                } else if (direction == 'S') {
                    direction = 'E';
                } else {
                    direction = 'N';
                }
            } else if (c == 'R') {
                if (direction == 'N') {
                    direction = 'E';
                } else if (direction == 'W') {
                    direction = 'N';
                } else if (direction == 'S') {
                    direction = 'W';
                } else {
                    direction = 'S';
                }
            }
        }
        return (x == 0 && y == 0) || direction != 'N';
    }

    public static void main(String[] args) {
        final RobotBoundedInCircle robotBoundedInCircle = new RobotBoundedInCircle();

        System.out.println(robotBoundedInCircle.isRobotBounded("GGLLGG"));
        System.out.println(robotBoundedInCircle.isRobotBounded("GG"));
        System.out.println(robotBoundedInCircle.isRobotBounded("GL"));
    }
}
