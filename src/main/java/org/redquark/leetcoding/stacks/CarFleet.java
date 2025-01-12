package org.redquark.leetcoding.stacks;

import java.util.Arrays;
import java.util.Comparator;

public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        final int n = position.length;
        // Array to keep track of positions and time to reach target
        final double[][] cars = new double[n][2];
        // Process the array
        for (int i = 0; i < n; i++) {
            cars[i] = new double[]{position[i], (double)(target - position[i]) / speed[i]};
        }
        // Sort the array in reverse order based on time
        Arrays.sort(cars, Comparator.comparingDouble(a -> a[0]));
        // Slowest time so far
        double slowest = 0;
        // Total number of fleets
        int fleets = 0;
        // Process the pairs in reverse order
        for (int i = n - 1; i >= 0; i--) {
            if (cars[i][1] > slowest) {
                slowest = cars[i][1];
                fleets++;
            }
        }
        return fleets;
    }

    public static void main(String[] args) {
        final CarFleet carFleet = new CarFleet();

        int target = 12;
        int[] position = new int[]{10, 8, 0, 5, 3};
        int[] speed = new int[]{2, 4, 1, 1, 3};
        System.out.println(carFleet.carFleet(target, position, speed));

        target = 10;
        position = new int[]{3};
        speed = new int[]{3};
        System.out.println(carFleet.carFleet(target, position, speed));

        target = 100;
        position = new int[]{0, 2, 4};
        speed = new int[]{4, 2, 1};
        System.out.println(carFleet.carFleet(target, position, speed));
    }
}
