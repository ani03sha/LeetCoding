package org.redquark.leetcoding.strings;

public class RobotReturnToOrigin {

    public boolean judgeCircle(String moves) {
        // Special case
        if (moves == null || moves.isEmpty()) {
            return false;
        }
        // Abscissa and ordinates
        int x = 0;
        int y = 0;
        // Process the string
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'U' -> y++;
                case 'D' -> y--;
                case 'L' -> x--;
                case 'R' -> x++;
            }
        }
        return x == 0 && y == 0;
    }

    public static void main(String[] args) {
        final RobotReturnToOrigin robotReturnToOrigin = new RobotReturnToOrigin();

        System.out.println(robotReturnToOrigin.judgeCircle("UD"));
        System.out.println(robotReturnToOrigin.judgeCircle("LL"));
    }
}
