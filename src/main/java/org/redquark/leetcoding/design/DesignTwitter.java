package org.redquark.leetcoding.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DesignTwitter {

    static class Twitter {
        private final Map<Integer, List<Integer>> tweetsByUser;
        // Following mappings => [follower -> [followee]]
        private final Map<Integer, Set<Integer>> followings;
        // Tweets and their timestamps [tweetId -> timestamp]
        private final Map<Integer, Integer> tweets;
        // Global clock
        private int timestamp;

        public Twitter() {
            this.tweetsByUser = new HashMap<>();
            this.followings = new HashMap<>();
            this.tweets = new HashMap<>();
            this.timestamp = 0;
        }

        public void postTweet(int userId, int tweetId) {
            this.tweetsByUser.computeIfAbsent(userId, _ -> new ArrayList<>()).add(tweetId);
            this.tweets.put(tweetId, this.timestamp++);
        }

        public List<Integer> getNewsFeed(int userId) {
            // Get all users this user follows
            final Set<Integer> userIds = new HashSet<>(this.followings.getOrDefault(userId, new HashSet<>()));
            // News feed will also include own tweets
            userIds.add(userId);
            // Heap to store all the latest tweets (only 10) for news feed for every user
            final Queue<Integer> top10Tweets = new PriorityQueue<>(10, (a, b) -> this.tweets.get(b) - this.tweets.get(a));
            // Process all users
            for (int user : userIds) {
                // Get all tweets for this user id
                final List<Integer> tweetIds = this.tweetsByUser.get(user);
                if (tweetIds != null && !tweetIds.isEmpty()) {
                    int j = 0;
                    for (int i = tweetIds.size() - 1; i >= 0 && j < 10; i--) {
                        top10Tweets.offer(tweetIds.get(i));
                        j++;
                    }
                }
            }
            // Populate the news feed
            final List<Integer> newsFeed = new ArrayList<>();
            while (!top10Tweets.isEmpty() && newsFeed.size() < 10) {
                newsFeed.add(top10Tweets.remove());
            }
            return newsFeed;
        }

        public void follow(int followerId, int followeeId) {
            this.followings.computeIfAbsent(followerId, _ -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            this.followings.computeIfAbsent(followerId, _ -> new HashSet<>()).remove(followeeId);
        }
    }

    public static void main(String[] args) {
        final Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.

    }
}
