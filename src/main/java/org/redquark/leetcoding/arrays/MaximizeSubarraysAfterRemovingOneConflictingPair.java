package org.redquark.leetcoding.arrays;

import java.util.ArrayList;
import java.util.List;

public class MaximizeSubarraysAfterRemovingOneConflictingPair {

    @SuppressWarnings("unchecked")
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        long totalValid = 0;
        // gains[r] stores the additional subarrays we gain by removing the conflict ending at position r
        final long[] gains = new long[n + 2];
        // conflicts[r] contains all 'left' elements where (left, r) is a conflict
        // Note: This groups conflicts by their left endpoint for efficient processing
        final List<Integer>[] conflicts = new List[n + 2];
        for (int i = 0; i <= n + 1; i++) {
            conflicts[i] = new ArrayList<>();
        }
        // Group each conflicting pair by its left endpoint
        for (int[] pair : conflictingPairs) {
            int a = pair[0], b = pair[1];
            int left = Math.min(a, b), right = Math.max(a, b);
            conflicts[left].add(right); // Note: flip direction — group by 'left'
        }
        // Track the two smallest right endpoints encountered so far
        // These represent the closest and second-closest conflicting right endpoints
        int minRight = n + 1;        // The nearest conflicting right endpoint
        int secondMinRight = n + 1;  // The second-nearest conflicting right endpoint
        // Traverse from n → 1 (reverse order)
        // This allows us to maintain the invariant of tracking the closest conflicts
        for (int left = n; left >= 1; left--) {
            // Update tracking of closest conflicts for the current left position
            for (int right : conflicts[left]) {
                if (right < minRight) {
                    // The new closest conflict found
                    secondMinRight = minRight;
                    minRight = right;
                } else if (right < secondMinRight) {
                    // The new second-closest conflict found
                    secondMinRight = right;
                }
            }
            // Calculate valid subarrays starting at 'left' and potential gains
            if (minRight <= n) {
                // There is a conflict that limits our subarrays
                // We can form subarrays [left, left], [left, left+1], ..., [left, minRight-1]
                totalValid += (minRight - left);

                // If we remove the closest conflict, we can extend to secondMinRight-1
                // This gives us (secondMinRight - minRight) additional subarrays
                gains[minRight] += (secondMinRight - minRight);
            } else {
                // No conflicts limit us, so we can form all subarrays from left to n
                totalValid += (n - left + 1);
            }
        }
        // Find the maximum gain from removing any single conflicting pair
        long bestGain = 0;
        for (long gain : gains) {
            bestGain = Math.max(bestGain, gain);
        }
        return totalValid + bestGain;
    }

    public static void main(String[] args) {
        final MaximizeSubarraysAfterRemovingOneConflictingPair maximizeSubarraysAfterRemovingOneConflictingPair
                = new MaximizeSubarraysAfterRemovingOneConflictingPair();

        int n = 4;
        int[][] conflictingPairs = new int[][]{{2, 3}, {1, 4}};
        long result = maximizeSubarraysAfterRemovingOneConflictingPair.maxSubarrays(n, conflictingPairs);
        System.out.println("Example 1: " + result); // Output: 9

        n = 5;
        conflictingPairs = new int[][]{{1, 2}, {2, 5}, {3, 5}};
        result = maximizeSubarraysAfterRemovingOneConflictingPair.maxSubarrays(n, conflictingPairs);
        System.out.println("Example 2: " + result); // Output: 12
    }
}
