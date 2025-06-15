package org.redquark.extras.solutions.design;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiter {

    private final int maxRequests;
    private final int windowSizeInSeconds;
    private final ConcurrentHashMap<String, Bucket> userBuckets;

    public RateLimiter(int maxRequests, int windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.userBuckets = new ConcurrentHashMap<>();
    }

    public boolean isAllowed(String userId) {
        return userBuckets
                .computeIfAbsent(userId, _ -> new Bucket(this.maxRequests, this.windowSizeInSeconds))
                .allowRequest();
    }

    static class Bucket {
        private final int maxRequests;
        private final long windowSizeMillis;
        private final int maxCredits;
        private final Lock lock;

        private long windowStart;
        private int requestCount;
        private int credits;

        Bucket(int maxRequests, int windowSizeInSeconds) {
            this.maxRequests = maxRequests;
            this.windowSizeMillis = windowSizeInSeconds * 1000L;
            this.maxCredits = 2 * maxRequests;
            this.lock = new ReentrantLock();
            this.windowStart = System.currentTimeMillis();
            this.requestCount = 0;
            this.credits = 0;
        }

        boolean allowRequest() {
            // Get the current time
            final long now = System.currentTimeMillis();
            lock.lock();
            try {
                if (now - this.windowStart >= this.windowSizeMillis) {
                    // New window
                    int unused = this.maxRequests - this.requestCount;
                    this.credits = Math.max(this.maxCredits, this.credits + unused);
                    this.windowStart = now;
                    this.requestCount = 0;
                }
                if (this.requestCount < this.maxRequests) {
                    this.requestCount++;
                    return true;
                } else if (this.credits > 0) {
                    this.credits--;
                    return true;
                }
                return false;
            } finally {
                this.lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final RateLimiter rateLimiter = new RateLimiter(5, 10);

        String user = "user1";

        for (int i = 0; i < 6; i++) {
            System.out.println("Request " + i + ": " + rateLimiter.isAllowed(user));
            Thread.sleep(1000);
        }

        Thread.sleep(11000); // Wait for the new window
        System.out.println("New window starts");

        for (int i = 0; i < 8; i++) {
            System.out.println("Request " + i + ": " + rateLimiter.isAllowed(user));
        }
    }
}
