package org.redquark.leetcoding.heaps;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        // Special case
        if (stones == null || stones.length == 0) {
            return 0;
        }
        // Max heap to store the stones
        final Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Add all stones to the heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        // Destroy all stones one by one until only the heaviest stone is left
        while (maxHeap.size() > 1) {
            final int heaviestStone = maxHeap.remove();
            final int secondHeaviestStone = maxHeap.remove();
            if (heaviestStone != secondHeaviestStone) {
                maxHeap.offer(heaviestStone - secondHeaviestStone);
            }
        }
        return !maxHeap.isEmpty() ? maxHeap.remove() : 0;
    }

    public static void main(String[] args) {
        final LastStoneWeight lastStoneWeight = new LastStoneWeight();

        int[] stones = new int[]{2, 7, 4, 1, 8, 1};
        System.out.println(lastStoneWeight.lastStoneWeight(stones));

        stones = new int[]{1};
        System.out.println(lastStoneWeight.lastStoneWeight(stones));
    }
}
