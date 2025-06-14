package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTeamsByVotes {

    public String rankTeamsWithMap(String[] votes) {
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

    public String rankTeamsWith2DArray(String[] votes) {
        // Total number of teams
        final int n = votes[0].length();
        // Array to store the counts of positions for every team
        // i.e., counts[i][j] => Team i has j-th position counts[i][j] times
        final int[][] counts = new int[26][n]; // i => teamId, j => position
        for (String vote : votes) {
            for (int i = 0; i < n; i++) {
                counts[vote.charAt(i) - 'A'][i]++;
            }
        }
        // Array to store teams
        final Character[] teams = new Character[n];
        for (int i = 0; i < n; i++) {
            teams[i] = votes[0].charAt(i);
        }
        // Sort the teams by the rules
        Arrays.sort(teams, (a, b) -> {
            final int aIndex = a - 'A';
            final int bIndex = b - 'A';
            for (int i = 0; i < n; i++) {
                // Difference of their ranks
                final int difference = counts[aIndex][i] - counts[bIndex][i];
                if (difference != 0) {
                    return difference > 0 ? -1 : 1;
                }
            }
            return a - b;
        });
        // Construct the final output
        final StringBuilder result = new StringBuilder();
        for (Character team : teams) {
            result.append(team);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        final RankTeamsByVotes rankTeamsByVotes = new RankTeamsByVotes();

        String[] votes = new String[]{"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println(rankTeamsByVotes.rankTeamsWithMap(votes));
        System.out.println(rankTeamsByVotes.rankTeamsWith2DArray(votes));

        votes = new String[]{"WXYZ", "XYZW"};
        System.out.println(rankTeamsByVotes.rankTeamsWithMap(votes));
        System.out.println(rankTeamsByVotes.rankTeamsWith2DArray(votes));

        votes = new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
        System.out.println(rankTeamsByVotes.rankTeamsWithMap(votes));
        System.out.println(rankTeamsByVotes.rankTeamsWith2DArray(votes));
    }
}
