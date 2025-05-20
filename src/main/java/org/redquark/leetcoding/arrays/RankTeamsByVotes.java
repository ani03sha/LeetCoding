package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTeamsByVotes {

    public String rankTeams(String[] votes) {
        // Map to store the ranks by position of each team
        final Map<Character, int[]> ranksByPosition = new HashMap<>();
        // Total number of teams
        final int n = votes[0].length();
        // Process all votes
        for (String vote : votes) {
            for (int i = 0; i < n; i++) {
                final char c = vote.charAt(i);
                ranksByPosition.computeIfAbsent(c, _ -> new int[26])[i]++;
            }
        }
        // Sort all the teams (keys in the map) based on rules
        final List<Character> teams = new ArrayList<>(ranksByPosition.keySet());
        teams.sort((a, b) -> {
            for (int i = 0; i < n; i++) {
                if (ranksByPosition.get(a)[i] != ranksByPosition.get(b)[i]) {
                    return ranksByPosition.get(b)[i] - ranksByPosition.get(a)[i];
                }
            }
            return a - b;
        });
        // Construct the result
        final StringBuilder result = new StringBuilder();
        for (char team : teams) {
            result.append(team);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final RankTeamsByVotes rankTeamsByVotes = new RankTeamsByVotes();

        String[] votes = new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println(rankTeamsByVotes.rankTeams(votes));

        votes = new String[]{"WXYZ", "XYZW"};
        System.out.println(rankTeamsByVotes.rankTeams(votes));

        votes = new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
        System.out.println(rankTeamsByVotes.rankTeams(votes));
    }
}
