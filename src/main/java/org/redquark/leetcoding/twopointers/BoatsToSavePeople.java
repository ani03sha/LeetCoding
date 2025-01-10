package org.redquark.leetcoding.twopointers;

import java.util.Arrays;

public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        // Special case
        if (people == null || people.length == 0 || limit <= 0) {
            return 0;
        }
        // Sort the array
        Arrays.sort(people);
        // Left and right pointers
        int left = 0;
        int right = people.length - 1;
        // Total number of boats required
        int requiredBoats = 0;
        // Process array from both ends
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
                right--;
            } else if (people[left] + people[right] > limit) {
                right--;
            }
            requiredBoats++;
        }
        return requiredBoats;
    }

    public static void main(String[] args) {
        final BoatsToSavePeople boatsToSavePeople = new BoatsToSavePeople();

        int[] people = new int[]{1, 2};
        int limit = 3;
        System.out.println(boatsToSavePeople.numRescueBoats(people, limit));

        people = new int[]{3, 2, 2, 1};
        System.out.println(boatsToSavePeople.numRescueBoats(people, limit));

        people = new int[]{3, 5, 3, 4};
        limit = 5;
        System.out.println(boatsToSavePeople.numRescueBoats(people, limit));
    }
}
