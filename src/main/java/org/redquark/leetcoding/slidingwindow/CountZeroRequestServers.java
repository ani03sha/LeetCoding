package org.redquark.leetcoding.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountZeroRequestServers {

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Sort the "logs" array by time
        Arrays.sort(logs, Comparator.comparingInt(log -> log[1]));
        // Create a new list with the query and id
        final List<int[]> queriesWithId = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            queriesWithId.add(new int[]{i, queries[i]});
        }
        // Sort these queries with their times
        queriesWithId.sort(Comparator.comparingInt(query -> query[1]));
        // Array to store the final output
        final int[] output = new int[queries.length];
        // Map to keep track of servers that accept requests
        // Key => Server ID, Value => Number of requests received
        final Map<Integer, Integer> activeServers = new HashMap<>();
        // Left and right pointers for the sliding window
        int left = 0;
        int right = 0;
        // Traverse for every query
        for (int[] query : queriesWithId) {
            final int queryId = query[0];
            final int queryStartTime = query[1] - x;
            final int queryEndTime = query[1];
            // Include all logs that are within time period
            while (right < logs.length && logs[right][1] <= queryEndTime) {
                final int serverId = logs[right][0];
                activeServers.put(serverId, activeServers.getOrDefault(serverId, 0) + 1);
                right++;
            }
            // Exclude all logs out of time period - shrink window
            while (left < logs.length && logs[left][1] < queryStartTime) {
                final int serverId = logs[left][0];
                activeServers.put(serverId, activeServers.getOrDefault(serverId, 0) - 1);
                if (activeServers.get(serverId) == 0) {
                    activeServers.remove(serverId);
                }
                left++;
            }
            output[queryId] = n - activeServers.size();
        }
        return output;
    }

    public static void main(String[] args) {
        final CountZeroRequestServers countZeroRequestServers = new CountZeroRequestServers();

        int n = 3;
        int[][] logs = new int[][]{{1, 3}, {2, 6}, {1, 5}};
        int x = 5;
        int[] queries = new int[]{10, 11};
        System.out.println(Arrays.toString(countZeroRequestServers.countServers(n, logs, x, queries)));

        n = 3;
        logs = new int[][]{{2, 4}, {2, 1}, {1, 2}, {3, 1}};
        x = 2;
        queries = new int[]{3, 4};
        System.out.println(Arrays.toString(countZeroRequestServers.countServers(n, logs, x, queries)));
    }
}
