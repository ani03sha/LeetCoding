package org.redquark.leetcoding.arrays;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        // Special case
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        // Array to store the frequencies of every task
        final int[] frequencies = new int[26];
        // Variable to denote maximum frequency
        int maxFrequency = 0;
        // Process the tasks array
        for (char task : tasks) {
            frequencies[task - 'A']++;
            maxFrequency = Math.max(maxFrequency, frequencies[task - 'A']);
        }
        // Calculate the count of tasks with maxFrequency
        int mostOccurringTasks = 0;
        for (int frequency : frequencies) {
            if (frequency == maxFrequency) {
                mostOccurringTasks++;
            }
        }
        return Math.max(tasks.length, (maxFrequency - 1) * (n + 1) + mostOccurringTasks);
    }

    public static void main(String[] args) {
        final TaskScheduler taskScheduler = new TaskScheduler();

        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        System.out.println(taskScheduler.leastInterval(tasks, n));

        tasks = new char[]{'A', 'C', 'A', 'B', 'D', 'B'};
        n = 1;
        System.out.println(taskScheduler.leastInterval(tasks, n));

        tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        n = 3;
        System.out.println(taskScheduler.leastInterval(tasks, n));
    }
}
