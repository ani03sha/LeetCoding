package org.redquark.leetcoding.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindTheNumberOfDistinctColorsAmongTheBalls {

    public int[] queryResults(int[][] queries) {
        final int n = queries.length;
        // Map to store ball and color mappings
        final Map<Integer, Integer> ballToColorMappings = new HashMap<>();
        // Map to store distinct colors and their frequencies (i.e., on how many
        // balls this color is present)
        final Map<Integer, Integer> colorCountMappings = new HashMap<>();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            final int ball = queries[i][0];
            final int color = queries[i][1];
            // Check if we are coloring an existing ball
            if (ballToColorMappings.containsKey(ball)) {
                // Get the existing color of the ball
                final int existingColor = ballToColorMappings.get(ball);
                // Since this ball is getting colored with different color, there
                // will be one less ball with the existingColor
                colorCountMappings.put(existingColor, colorCountMappings.get(existingColor) - 1);
                // If no ball is left with this color, we remove it from our map
                if (colorCountMappings.get(existingColor) == 0) {
                    colorCountMappings.remove(existingColor);
                }
                // Color the ball with new color
                ballToColorMappings.put(ball, color);
                colorCountMappings.put(color, colorCountMappings.getOrDefault(color, 0) + 1);
            }
            // If a new ball is getting colored
            else {
                ballToColorMappings.put(ball, color);
                // Update count of this color in the other map
                colorCountMappings.put(color, colorCountMappings.getOrDefault(color, 0) + 1);
            }
            // Distinct colors at this point
            result[i] = colorCountMappings.size();
        }
        return result;
    }

    public static void main(String[] args) {
        final FindTheNumberOfDistinctColorsAmongTheBalls findTheNumberOfDistinctColorsAmongTheBalls = new FindTheNumberOfDistinctColorsAmongTheBalls();

        int[][] queries = new int[][]{{1, 4}, {2, 5}, {1, 3}, {3, 4}};
        System.out.println(Arrays.toString(findTheNumberOfDistinctColorsAmongTheBalls.queryResults(queries)));

        queries = new int[][]{{0, 1}, {1, 2}, {2, 2}, {3, 4}, {4, 5}};
        System.out.println(Arrays.toString(findTheNumberOfDistinctColorsAmongTheBalls.queryResults(queries)));
    }
}
