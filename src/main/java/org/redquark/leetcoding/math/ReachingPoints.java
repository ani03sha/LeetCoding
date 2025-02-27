package org.redquark.leetcoding.math;

public class ReachingPoints {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        // Move from (tx, ty) to (sx, sy) i.e., backwards
        while (tx > sx && ty > sy) {
            // If tx > ty, reduce tx
            if (tx > ty) {
                tx %= ty;
            }
            // Else, reduce ty
            else {
                ty %= tx;
            }
        }
        // Check if we have reached (sx, sy)
        if (tx == sx && ty == sy) {
            return true;
        }
        // If we are in the same horizontal line as the starting point,
        // check if we can reach to vertical position by reducing ty
        if (tx == sx) {
            return (ty > sy) && (ty - sy) % sx == 0;
        }
        // If we are in the same vertical line as the starting point,
        // check if we can reach to the horizontal position by reducing tx
        if (ty == sy) {
            return (tx > sx) && (tx - sx) % sy == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        final ReachingPoints reachingPoints = new ReachingPoints();

        int sx = 1;
        int sy = 1;
        int tx = 3;
        int ty = 5;
        System.out.println(reachingPoints.reachingPoints(sx, sy, tx, ty));

        sx = 1;
        sy = 1;
        tx = 2;
        ty = 2;
        System.out.println(reachingPoints.reachingPoints(sx, sy, tx, ty));

        sx = 1;
        sy = 1;
        tx = 1;
        ty = 1;
        System.out.println(reachingPoints.reachingPoints(sx, sy, tx, ty));

    }
}
