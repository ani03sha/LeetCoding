package org.redquark.extras.solutions.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TeleportDiceGame {

    // Part 1: Get all final positions after one die roll
    public Set<Integer> getAllFinalPositions(int lastNumber, int startPosition, int maxDieValue, List<String> teleporters) {
        // Parse the teleporters
        final Map<Integer, Integer> teleportMap = parseTeleporters(teleporters);
        // Set to store all final positions
        final Set<Integer> finalPositions = new HashSet<>();
        // Try for every roll of the die
        for (int roll = 1; roll <= maxDieValue; roll++) {
            int nextPosition = startPosition + roll;
            if (nextPosition > lastNumber) {
                nextPosition = lastNumber;
            }
            nextPosition = resolveTeleport(nextPosition, teleportMap);
            finalPositions.add(nextPosition);
        }
        return finalPositions;
    }

    public boolean canReachLastNumber(int lastNumber, int startPosition, int maxDieValue, List<String> teleporters) {
        final Map<Integer, Integer> teleportMap = parseTeleporters(teleporters);
        // Visited array
        final boolean[] visited = new boolean[lastNumber + 1];
        // Queue to perform BFS
        final Queue<Integer> positions = new LinkedList<>();
        final int start = resolveTeleport(startPosition, teleportMap);
        positions.offer(start);
        visited[start] = true;
        // Perform BFS
        while (!positions.isEmpty()) {
            final int currentPosition = positions.remove();
            if (currentPosition == lastNumber) {
                return true;
            }
            for (int roll = 1; roll <= maxDieValue; roll++) {
                int nextPosition = currentPosition + roll;
                if (nextPosition > lastNumber) {
                    nextPosition = lastNumber;
                }
                nextPosition = resolveTeleport(nextPosition, teleportMap);
                if (!visited[nextPosition]) {
                    visited[nextPosition] = true;
                    positions.offer(nextPosition);
                }
            }
        }
        return false;
    }

    private int resolveTeleport(int position, Map<Integer, Integer> teleportMap) {
        final Set<Integer> visited = new HashSet<>();
        while (teleportMap.containsKey(position)) {
            if (visited.contains(position)) {
                break;
            }
            visited.add(position);
            position = teleportMap.get(position);
        }
        return position;
    }

    private Map<Integer, Integer> parseTeleporters(List<String> teleporters) {
        final Map<Integer, Integer> teleportMap = new HashMap<>();
        for (String teleporter : teleporters) {
            final String[] parts = teleporter.split(",");
            teleportMap.put(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        return teleportMap;
    }

    public static void main(String[] args) {
        final TeleportDiceGame teleportDiceGame = new TeleportDiceGame();
        int lastNumber = 10;
        int startPosition = 3;
        int maxDieValue = 6;
        List<String> teleporters = List.of("3,1", "5,10", "8,2");

        System.out.println("=== Part 1: One Roll Final Positions ===");
        Set<Integer> finals = teleportDiceGame.getAllFinalPositions(lastNumber, startPosition, maxDieValue, teleporters);
        System.out.println("Final Positions: " + finals);

        System.out.println("\n=== Part 2: Can Reach LastNumber ===");
        boolean canReach = teleportDiceGame.canReachLastNumber(lastNumber, startPosition, maxDieValue, teleporters);
        System.out.println("Reachable: " + canReach);
    }
}
