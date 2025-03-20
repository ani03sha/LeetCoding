package org.redquark.leetcoding.design;

import java.util.ArrayDeque;
import java.util.Deque;

public class DesignHitCounter {

    static class HitCounter {

        private final Deque<Integer> hits;

        public HitCounter() {
            this.hits = new ArrayDeque<>();
        }

        public void hit(int timestamp) {
            this.hits.offerLast(timestamp);
        }

        public int getHits(int timestamp) {
            while (!this.hits.isEmpty() && timestamp - 300 >= this.hits.peekFirst()) {
                this.hits.removeFirst();
            }
            return this.hits.size();
        }
    }

    public static void main(String[] args) {
        final HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        System.out.println(hitCounter.getHits(4));   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        System.out.println(hitCounter.getHits(300)); // get hits at timestamp 300, return 4.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 3.
    }
}
