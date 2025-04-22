package org.redquark.leetcoding.prefixsum;

public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        // Array to store the number of passengers at each stop.
        // i.e., stops[i] => number of passengers at the i-th stop
        final int[] stops = new int[1001];
        // Process every trip
        for (int[] trip : trips) {
            // Since trip[1] represents number of passengers entering
            // the car
            stops[trip[1]] += trip[0];
            // Since trip[2] represents number of passengers leaving
            // the car
            stops[trip[2]] -= trip[0];
        }
        // Check if at any stop the number of passengers in the car
        // exceeded the capacity
        int totalPassengers = 0;
        for (int stop : stops) {
            totalPassengers += stop;
            if (totalPassengers > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final CarPooling carPooling = new CarPooling();

        int[][] trips = new int[][]{{2,1,5},{3,3,7}};
        int capacity = 4;
        System.out.println(carPooling.carPooling(trips, capacity));

        capacity = 5;
        System.out.println(carPooling.carPooling(trips, capacity));
    }
}
