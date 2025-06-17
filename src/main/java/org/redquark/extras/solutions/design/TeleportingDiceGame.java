package org.redquark.extras.solutions.design;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TeleportingDiceGame {

    // Part 1: Get all final positions after one die roll
    public List<Integer> getAllFinalPositions(int lastNumber, int startPosition, int maxDieValue, List<String> teleporters) {
        // Create a map for teleporters
        final Map<Integer, Integer> teleportMap = new HashMap<>();
        for (String teleporter : teleporters) {
            final String[] parts = teleporter.split(",");
            final int source = Integer.parseInt(parts[0]);
            final int destination = Integer.parseInt(parts[1]);
            teleportMap.putIfAbsent(source, destination);
        }
        // Set to store final positions
        final List<Integer> finalPositions = new ArrayList<>();
        // Check for every roll
        for (int roll = 1; roll <= maxDieValue; roll++) {
            int nextPosition = startPosition + roll;
            if (nextPosition > lastNumber) {
                nextPosition = lastNumber;
            }
            if (teleportMap.containsKey(nextPosition)) {
                nextPosition = teleportMap.get(nextPosition);
            }
            finalPositions.add(nextPosition);
        }
        return finalPositions;
    }

    public boolean canReachLastNumber(int lastNumber, int startPosition, int maxDieValue, List<String> teleporters) {
        // Create a map for teleporters
        final Map<Integer, Integer> teleportMap = new HashMap<>();
        for (String teleport : teleporters) {
            final String[] parts = teleport.split(",");
            final int source = Integer.parseInt(parts[0]);
            final int destination = Integer.parseInt(parts[1]);
            teleportMap.putIfAbsent(source, destination);
        }
        // Queue for BFS
        final Queue<Integer> positions = new ArrayDeque<>();
        final Set<Integer> visited = new HashSet<>();
        positions.offer(startPosition);
        visited.add(startPosition);
        // Perform BFS
        while (!positions.isEmpty()) {
            final int current = positions.remove();
            for (int roll = 1; roll <= maxDieValue; roll++) {
                int nextPosition = current + roll;
                if (nextPosition > lastNumber) {
                    nextPosition = lastNumber;
                }
                if (teleportMap.containsKey(nextPosition)) {
                    nextPosition = teleportMap.get(nextPosition);
                }
                if (nextPosition == lastNumber) {
                    return true;
                }
                if (!visited.contains(nextPosition)) {
                    positions.offer(nextPosition);
                    visited.add(nextPosition);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final TeleportingDiceGame teleportDiceGame = new TeleportingDiceGame();
        int lastNumber = 10;
        int startPosition = 3;
        int maxDieValue = 6;
        List<String> teleporters = List.of("3,1", "5,10", "8,2");

        System.out.println("=== Part 1: One Roll Final Positions ===");
        List<Integer> finals = teleportDiceGame.getAllFinalPositions(lastNumber, startPosition, maxDieValue, teleporters);
        System.out.println("Final Positions: " + finals);

        System.out.println("\n=== Part 2: Can Reach LastNumber ===");
        boolean canReach = teleportDiceGame.canReachLastNumber(lastNumber, startPosition, maxDieValue, teleporters);
        System.out.println("Reachable: " + canReach);
    }
}
