package org.redquark.leetcoding.design;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DesignAuthenticationManager {

    static class AuthenticationManager {

        private final int timeToLive;
        private final Map<String, Integer> tokenExpiryTimes;

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
            this.tokenExpiryTimes = new HashMap<>();
        }

        public void generate(String tokenId, int currentTime) {
            this.tokenExpiryTimes.put(tokenId, currentTime + this.timeToLive);
        }

        public void renew(String tokenId, int currentTime) {
            // Get expiry time of this tokenId
            final int expiryTime = this.tokenExpiryTimes.getOrDefault(tokenId, 0);
            if (expiryTime > currentTime) {
                generate(tokenId, currentTime);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            int count = 0;
            for (int expiryTime : this. tokenExpiryTimes.values()) {
                if (expiryTime > currentTime) {
                    count++;
                }
            }
            return count;
        }
    }

    static class AuthenticationManagerUsingHeap {
        // TTL for a token
        private final int timeToLive;
        // Map to store all tokens with their expiry times
        private final Map<String, Integer> tokens;
        // Min heap to store tokens sorted on expiry times
        private final Queue<AbstractMap.SimpleEntry<String, Integer>> expiryHeap;

        public AuthenticationManagerUsingHeap(int timeToLive) {
            this.timeToLive = timeToLive;
            this.tokens = new HashMap<>();
            this.expiryHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        }

        public void generate(String tokenId, int currentTime) {
            final int expiryTime = currentTime + this.timeToLive;
            this.tokens.put(tokenId, expiryTime);
            this.expiryHeap.offer(new AbstractMap.SimpleEntry<>(tokenId, expiryTime));
        }

        public void renew(String tokenId, int currentTime) {
            // Get the expiry time of the given tokenId;
            final int expiryTime = this.tokens.getOrDefault(tokenId, -1);
            if (expiryTime != -1 && expiryTime > currentTime) {
                generate(tokenId, currentTime);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            // Remove all tokens that are expired from heap
            while (!expiryHeap.isEmpty()) {
                final AbstractMap.SimpleEntry<String, Integer> entry = this.expiryHeap.peek();
                final String tokenId = entry.getKey();
                final int expiryTime = entry.getValue();
                if (expiryTime <= currentTime) {
                    this.expiryHeap.remove();
                    if (this.tokens.getOrDefault(tokenId, -1) == expiryTime) {
                        this.tokens.remove(tokenId);
                    }
                } else {
                    break;
                }
            }
            return this.tokens.size();
        }
    }

    public static void main(String[] args) {
        AuthenticationManager authenticationManager = new AuthenticationManager(5);
        authenticationManager.renew("aaa", 1);
        authenticationManager.generate("aaa", 2);
        System.out.println(authenticationManager.countUnexpiredTokens(6)); // Output: 1
        authenticationManager.generate("bbb", 7);
        authenticationManager.renew("aaa", 8);
        authenticationManager.renew("bbb", 10);
        System.out.println(authenticationManager.countUnexpiredTokens(15)); // Output: 0

        // Add similar test cases for AuthenticationManagerUsingHeap
        AuthenticationManagerUsingHeap authenticationManagerUsingHeap = new AuthenticationManagerUsingHeap(5);
        authenticationManagerUsingHeap.renew("aaa", 1);
        authenticationManagerUsingHeap.generate("aaa", 2);
        System.out.println(authenticationManagerUsingHeap.countUnexpiredTokens(6)); // Output: 1
        authenticationManagerUsingHeap.generate("bbb", 7);
        authenticationManagerUsingHeap.renew("aaa", 8);
        authenticationManagerUsingHeap.renew("bbb", 10);
        System.out.println(authenticationManagerUsingHeap.countUnexpiredTokens(15)); // Output: 0
    }
}
