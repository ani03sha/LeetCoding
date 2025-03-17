package org.redquark.leetcoding.linesweep;

public class MaximumPopulationYear {

    public int maximumPopulation(int[][] logs) {
        // Array to store the number of people alive in each
        // year between 1950 and 2050
        final int[] population = new int[2050 - 1950 + 1];
        // Traverse through the logs
        for (int[] log : logs) {
            population[log[0] - 1950]++;
            population[log[1] - 1950]--;
        }
        // Maximum population in a year
        int maxPopulation = 0;
        // The earliest year
        int earliestYear = 1950;
        // Count of total people alive in a certain year
        int alivePopulation = 0;
        // Process the population array
        for (int i = 0; i < 101; i++) {
            alivePopulation += population[i];
            if (maxPopulation < alivePopulation) {
                maxPopulation = alivePopulation;
                earliestYear = i;
            }
        }
        return earliestYear + 1950;
    }

    public static void main(String[] args) {
        final MaximumPopulationYear maximumPopulationYear = new MaximumPopulationYear();

        int[][] logs = new int[][]{{1993, 1999}, {2000, 2010}};
        System.out.println(maximumPopulationYear.maximumPopulation(logs));

        logs = new int[][]{{1950, 1961}, {1960, 1971}, {1970, 1981}};
        System.out.println(maximumPopulationYear.maximumPopulation(logs));
    }
}
