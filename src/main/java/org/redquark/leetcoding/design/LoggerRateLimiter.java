package org.redquark.leetcoding.design;

import java.util.HashMap;
import java.util.Map;

public class LoggerRateLimiter {

    static class Logger {
        // Map to store message and timestamp
        private final Map<String, Integer> rateLimiter;

        public Logger() {
            this.rateLimiter = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            final int time = this.rateLimiter.getOrDefault(message, 0);
            if (time > timestamp) {
                return false;
            }
            this.rateLimiter.put(message, timestamp + 10);
            return true;
        }
    }

    public static void main(String[] args) {
        final Logger logger = new Logger();

        System.out.println(logger.shouldPrintMessage(1, "foo"));  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
        System.out.println(logger.shouldPrintMessage(2, "bar"));  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
        System.out.println(logger.shouldPrintMessage(3, "foo"));  // 3 < 11, return false
        System.out.println(logger.shouldPrintMessage(8, "bar"));  // 8 < 12, return false
        System.out.println(logger.shouldPrintMessage(10, "foo")); // 10 < 11, return false
        System.out.println(logger.shouldPrintMessage(11, "foo")); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21
    }
}
