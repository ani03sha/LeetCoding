package org.redquark.leetcoding.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // Special case
        if (rooms == null || rooms.isEmpty()) {
            return true;
        }
        final int n = rooms.size();
        // Array to keep track of visited rooms
        final boolean[] visited = new boolean[n];
        // Queue to perform BFS
        final Queue<Integer> keys = new LinkedList<>();
        // Add 0-th room to the queue and mark it visited
        keys.offer(0);
        visited[0] = true;
        // BFS
        while (!keys.isEmpty()) {
            // Get current key
            final int key = keys.remove();
            // Traverse all the rooms, keys to which this room holds
            for (int nextKey : rooms.get(key)) {
                if (visited[nextKey]) {
                    continue;
                }
                visited[nextKey] = true;
                keys.offer(nextKey);
            }
        }
        // Check if all the rooms have been visited
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final KeysAndRooms keysAndRooms = new KeysAndRooms();

        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(List.of(1));
        rooms.add(List.of(2));
        rooms.add(List.of(3));
        rooms.add(List.of());
        System.out.println(keysAndRooms.canVisitAllRooms(rooms));

        rooms = new ArrayList<>();
        rooms.add(List.of(1, 3));
        rooms.add(List.of(3, 0, 1));
        rooms.add(List.of(2));
        rooms.add(List.of(0));
        System.out.println(keysAndRooms.canVisitAllRooms(rooms));
    }
}
