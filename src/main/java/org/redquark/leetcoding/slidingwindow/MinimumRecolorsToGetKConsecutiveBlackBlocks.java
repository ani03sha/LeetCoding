package org.redquark.leetcoding.slidingwindow;

public class MinimumRecolorsToGetKConsecutiveBlackBlocks {

    public int minimumRecolors(String blocks, int k) {
        final int n = blocks.length();
        // Left and right pointers for the sliding window
        int left = 0;
        int right = 0;
        // Count of while blocks
        int whiteCount = 0;
        // Find whiteRecolors in the first k characters
        while (right < k) {
            if (blocks.charAt(right) == 'W') {
                whiteCount++;
            }
            right++;
        }
        // Minimum recolors required
        int minRecolors = whiteCount;
        // Process remaining characters
        while (right < n) {
            if (blocks.charAt(right) == 'W') {
                whiteCount++;
            }
            // Move sliding window ahead
            if (blocks.charAt(left) == 'W') {
                whiteCount--;
            }
            // Update minRecolors, if required
            minRecolors = Math.min(minRecolors, whiteCount);
            left++;
            right++;
        }
        return minRecolors;
    }

    public static void main(String[] args) {
        final MinimumRecolorsToGetKConsecutiveBlackBlocks minimumRecolorsToGetKConsecutiveBlackBlocks = new MinimumRecolorsToGetKConsecutiveBlackBlocks();

        String blocks = "WBBWWBBWBW";
        int k = 7;
        System.out.println(minimumRecolorsToGetKConsecutiveBlackBlocks.minimumRecolors(blocks, k));

        blocks = "WBWBBBW";
        k = 2;
        System.out.println(minimumRecolorsToGetKConsecutiveBlackBlocks.minimumRecolors(blocks, k));
    }
}
