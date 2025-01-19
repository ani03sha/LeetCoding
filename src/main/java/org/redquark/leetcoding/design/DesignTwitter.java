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

        // Tweets posted by a user
        private final Map<Integer, List<Integer>> userTweets;
        // Users followed by a user
        private final Map<Integer, Set<Integer>> userFollowing;
        // Mappings of tweets and their timestamps
        private final Map<Integer, Integer> tweets;
        // Global clock
        private int timestamp;

        public Twitter() {
            this.userTweets = new HashMap<>();
            this.userFollowing = new HashMap<>();
            this.tweets = new HashMap<>();
            this.timestamp = 0;
        }

        public void postTweet(int userId, int tweetId) {
            this.userTweets.computeIfAbsent(userId, _ -> new ArrayList<>()).add(tweetId);
            this.tweets.put(tweetId, timestamp++);
        }

        public List<Integer> getNewsFeed(int userId) {
            // Get all the users this user is following
            final Set<Integer> following = userFollowing.getOrDefault(userId, new HashSet<>());
            // Set to have list of all users for which we need to get tweets
            final Set<Integer> users = new HashSet<>(following);
            users.add(userId);
            // Max heap to store recent 10 tweets
            final Queue<Integer> top10Tweets = new PriorityQueue<>(10, (a, b) -> tweets.get(b) - tweets.get(a));
            // Collect tweets from every user but restrict to 10 recent tweets
            for (int user : users) {
                final List<Integer> tweets = userTweets.get(user);
                if (tweets != null && !tweets.isEmpty()) {
                    for (int i = tweets.size() - 1; i >= 0; i--) {
                        top10Tweets.offer(tweets.get(i));
                    }
                }
            }
            // Retrieve 10 most recent tweets
            final List<Integer> newsFeed = new ArrayList<>();
            while (!top10Tweets.isEmpty() && newsFeed.size() < 10) {
                newsFeed.add(top10Tweets.remove());
            }
            return newsFeed;
        }

        public void follow(int followerId, int followeeId) {
            this.userFollowing.computeIfAbsent(followerId, _ -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            this.userFollowing.computeIfAbsent(followerId, _ -> new HashSet<>()).remove(followeeId);
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
