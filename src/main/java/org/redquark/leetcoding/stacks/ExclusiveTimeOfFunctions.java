package org.redquark.leetcoding.stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class ExclusiveTimeOfFunctions {

    public int[] exclusiveTime(int n, List<String> logs) {
        // Array to store the exclusive time of the functions
        final int[] exclusiveTimes = new int[n];
        // Functions call stack
        final Deque<Integer> callStack = new ArrayDeque<>();
        // Time spent by the previous function
        int previous = 0;
        // Traverse the logs
        for (String log : logs) {
            final String[] logComponents = log.split(":");
            final int functionId = Integer.parseInt(logComponents[0]);
            final int time = Integer.parseInt(logComponents[2]);
            // When a new function is put on to the stack
            if (logComponents[1].equals("start")) {
                if (!callStack.isEmpty()) {
                    exclusiveTimes[callStack.peek()] += time - previous;
                }
                // Push the current function to the callStack
                callStack.push(functionId);
                previous = time;
            }
            // When an executing function ends
            else {
                // Pop the current executing function and update its execution time
                exclusiveTimes[callStack.pop()] += time - previous + 1;
                // Update previous
                previous = time + 1;
            }
        }
        return exclusiveTimes;
    }

    public static void main(String[] args) {
        final ExclusiveTimeOfFunctions exclusiveTimeOfFunctions = new ExclusiveTimeOfFunctions();

        int n = 2;
        List<String> logs = List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        System.out.println(Arrays.toString(exclusiveTimeOfFunctions.exclusiveTime(n, logs)));

        n = 1;
        logs = List.of("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7");
        System.out.println(Arrays.toString(exclusiveTimeOfFunctions.exclusiveTime(n, logs)));

        n = 2;
        logs = List.of("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7");
        System.out.println(Arrays.toString(exclusiveTimeOfFunctions.exclusiveTime(n, logs)));
    }
}
