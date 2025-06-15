package org.redquark.extras.solutions.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionWinner {

    public String findWinner(String[] votes) {
        // Map to store the frequency of votes for each candidate
        final Map<String, Integer> voteFrequencies = new HashMap<>();
        for (String vote : votes) {
            voteFrequencies.put(vote, voteFrequencies.getOrDefault(vote, 0) + 1);
        }
        // Maximum votes
        int maxVotes = 0;
        // List of candidates
        final List<String> candidates = new ArrayList<>();
        // Find max votes and tie candidates
        for (Map.Entry<String, Integer> entry : voteFrequencies.entrySet()) {
            final int currentVoteCount = entry.getValue();
            if (currentVoteCount > maxVotes) {
                maxVotes = currentVoteCount;
                candidates.clear();
                candidates.add(entry.getKey());
            } else if (currentVoteCount == maxVotes) {
                candidates.add(entry.getKey());
            }
        }
        // Tie-break logic: lexicographical order
        Collections.sort(candidates);
        return candidates.getFirst();
    }

    public static void main(String[] args) {
        final ElectionWinner electionWinner = new ElectionWinner();
        final String[] votes = {"c1", "c2", "c1", "c2", "c1", "c2", "c3", "c4", "c4"};
        String winner = electionWinner.findWinner(votes);
        System.out.println("Winner: " + winner); // Output: c1
    }
}
