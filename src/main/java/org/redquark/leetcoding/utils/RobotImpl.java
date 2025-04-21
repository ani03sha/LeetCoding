package org.redquark.leetcoding.utils;

public class RobotImpl implements Robot {

    private final int[][] room;
    private final boolean[][] cleaned;
    private int x, y, dir;
    private final int[][] directions = { {-1,0}, {0,1}, {1,0}, {0,-1} }; // up, right, down, left

    public RobotImpl(int[][] room, int startX, int startY) {
        this.room = room;
        this.cleaned = new boolean[room.length][room[0].length];
        this.x = startX;
        this.y = startY;
        this.dir = 0; // initially facing up
    }

    @Override
    public boolean move() {
        int nx = x + directions[dir][0];
        int ny = y + directions[dir][1];
        if (nx >= 0 && ny >= 0 && nx < room.length && ny < room[0].length && room[nx][ny] == 1) {
            x = nx;
            y = ny;
            return true;
        }
        return false;
    }

    @Override
    public void turnLeft() {
        dir = (dir + 3) % 4;
    }

    @Override
    public void turnRight() {
        dir = (dir + 1) % 4;
    }

    @Override
    public void clean() {
        cleaned[x][y] = true;
        System.out.println("Cleaned cell: (" + x + ", " + y + ")");
    }

    // Utility for testing:
    public boolean isCleaned(int i, int j) {
        return cleaned[i][j];
    }

    public void printCleanedMap() {
        for (int i = 0; i < cleaned.length; i++) {
            for (int j = 0; j < cleaned[0].length; j++) {
                System.out.print(cleaned[i][j] ? "C " : "- ");
            }
            System.out.println();
        }
    }
}
