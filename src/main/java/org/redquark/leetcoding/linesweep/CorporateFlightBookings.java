package org.redquark.leetcoding.linesweep;

import java.util.Arrays;

public class CorporateFlightBookings {

    public int[] corpFlightBookings(int[][] bookings, int n) {
        // Create an array to store total number of bookings
        final int[] bookingCount = new int[n + 1];
        // Process the bookings
        for (int[] booking : bookings) {
            final int beginIndex = booking[0] - 1;
            final int endIndex = booking[1];
            final int seats = booking[2];
            bookingCount[beginIndex] += seats;
            bookingCount[endIndex] -= seats;
        }
        // Compute the prefix sum
        for (int i = 1; i < n; i++) {
            bookingCount[i] += bookingCount[i - 1];
        }
        return Arrays.copyOf(bookingCount, n);
    }

    public static void main(String[] args) {
        final CorporateFlightBookings corporateFlightBookings = new CorporateFlightBookings();

        int[][] bookings = new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        System.out.println(Arrays.toString(corporateFlightBookings.corpFlightBookings(bookings, n)));

        bookings = new int[][]{{1, 2, 10}, {2, 2, 15}};
        n = 2;
        System.out.println(Arrays.toString(corporateFlightBookings.corpFlightBookings(bookings, n)));
    }
}
