package org.redquark.leetcoding.binarysearch;

public class OnlineElection {

    static class TopVotedCandidate {

        // Array to store voting times
        private final int[] times;
        // Array to store winner at each timestamp
        private final int[] winners;

    public TopVotedCandidate(int[] persons, int[] times) {
            final int n = persons.length;
            this.times = times;
            this.winners = new int[n];
            // Array to keep track of vote counts for each person
            final int[] votes = new int[n];
            // Current winner
            int currentWinner = 0;
            // Max votes so far
            int maxVotes = 0;
            // Process all votes
            for (int i = 0; i < n; i++) {
                // Increment the vote count of the current candidate
                votes[persons[i]]++;
                if (votes[persons[i]] >= maxVotes) {
                    maxVotes = votes[persons[i]];
                    currentWinner = persons[i];
                }
                this.winners[i] = currentWinner;
            }
        }

        public int q(int t) {
            // Perform binary search on the time
            int left = 0;
            int right = this.winners.length - 1;
            while (left < right) {
                final int middle = left + (right - left + 1) / 2; // Bias right to avoid infinite loop
                if (this.times[middle] <= t) {
                    left = middle;
                } else {
                    right = middle - 1;
                }
            }
            return this.winners[left];
        }
    }

    public static void main(String[] args) {
        final TopVotedCandidate topVotedCandidate = new TopVotedCandidate(
                new int[]{0, 1, 1, 0, 0, 1, 0},
                new int[]{0, 5, 10, 15, 20, 25, 30}
        );
        System.out.println(topVotedCandidate.q(3));  // Output: 0
        System.out.println(topVotedCandidate.q(12)); // Output: 1
        System.out.println(topVotedCandidate.q(25)); // Output: 1
        System.out.println(topVotedCandidate.q(15)); // Output: 0
        System.out.println(topVotedCandidate.q(24)); // Output: 0
        System.out.println(topVotedCandidate.q(8));  // Output: 1
    }
}
