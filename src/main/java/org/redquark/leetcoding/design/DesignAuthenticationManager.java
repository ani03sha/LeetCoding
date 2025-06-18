package org.redquark.leetcoding.design;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        AuthenticationManager authenticationManager = new AuthenticationManager(5);
        authenticationManager.renew("aaa", 1);
        authenticationManager.generate("aaa", 2);
        System.out.println(authenticationManager.countUnexpiredTokens(6)); // Output: 1
        authenticationManager.generate("bbb", 7);
        authenticationManager.renew("aaa", 8);
        authenticationManager.renew("bbb", 10);
        System.out.println(authenticationManager.countUnexpiredTokens(15)); // Output: 0
    }
}
