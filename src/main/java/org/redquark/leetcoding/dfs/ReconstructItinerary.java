package org.redquark.leetcoding.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        // Create a graph of airports. We have a list of every destination
        // airport from a source airport stored in a PriorityQueue so that
        // they are in the correct lexicographical order
        final Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            final String source = ticket.getFirst();
            final String destination = ticket.getLast();
            graph.computeIfAbsent(source, _ -> new PriorityQueue<>()).offer(destination);
        }
        // LinkedList to store the final itinerary
        final LinkedList<String> itinerary = new LinkedList<>();
        // Perform DFS on this graph starting from JFK
        dfs(graph, itinerary, "JFK");
        return itinerary;
    }

    private void dfs(Map<String, PriorityQueue<String>> graph, LinkedList<String> itinerary, String currentAirport) {
        // Get all neighboring airports corresponding to the currentAirport
        final PriorityQueue<String> neighboringAirports = graph.get(currentAirport);
        // Process all neighboring airports
        while (neighboringAirports != null && !neighboringAirports.isEmpty()) {
            final String neighboringAirport = neighboringAirports.remove();
            dfs(graph, itinerary, neighboringAirport);
        }
        itinerary.addFirst(currentAirport);
    }

    public static void main(String[] args) {
        final ReconstructItinerary reconstructItinerary = new ReconstructItinerary();

        List<List<String>> tickets = List.of(List.of("MUC", "LHR"), List.of("JFK", "MUC"), List.of("SFO", "SJC"), List.of("LHR", "SFO"));
        System.out.println(reconstructItinerary.findItinerary(tickets));

        tickets = List.of(List.of("JFK", "SFO"), List.of("JFK", "ATL"), List.of("SFO", "ATL"), List.of("ATL", "JFK"), List.of("ATL", "SFO"));
        System.out.println(reconstructItinerary.findItinerary(tickets));
    }
}
