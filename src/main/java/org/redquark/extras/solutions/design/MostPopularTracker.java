package org.redquark.extras.solutions.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MostPopularTracker {

    // Map to store content id and their popularity count
    private final Map<Integer, Integer> contentToPopularityMappings;
    // Map to store popularity and list of content ids with same popularity
    private final TreeMap<Integer, Set<Integer>> popularityToContentIdMappings;
    // Most recent top
    private int mostRecentTop;

    public MostPopularTracker() {
        this.contentToPopularityMappings = new HashMap<>();
        this.popularityToContentIdMappings = new TreeMap<>();
        this.mostRecentTop = -1;
    }

    public void increasePopularity(int contentId) {
        // Get old popularity for this content id
        final int oldPopularity = this.contentToPopularityMappings.getOrDefault(contentId, 0);
        // Updated popularity
        final int newPopularity = oldPopularity + 1;
        this.contentToPopularityMappings.put(contentId, newPopularity);
        // Remove from old popularity
        if (oldPopularity > 0) {
            final Set<Integer> contentIds = this.popularityToContentIdMappings.get(oldPopularity);
            contentIds.remove(contentId);
            if (contentIds.isEmpty()) {
                this.popularityToContentIdMappings.remove(oldPopularity);
            }
        }
        // Add to new popularity
        this.popularityToContentIdMappings.computeIfAbsent(newPopularity, _ -> new HashSet<>()).add(contentId);
        // Track the most recent top
        if (newPopularity == this.popularityToContentIdMappings.lastKey()) {
            this.mostRecentTop = contentId;
        }
    }

    public void decreasePopularity(int contentId) {
        // Get old popularity
        final int oldPopularity = this.contentToPopularityMappings.getOrDefault(contentId, 0);
        if (oldPopularity == 0) {
            return;
        }
        final int newPopularity = oldPopularity - 1;
        this.contentToPopularityMappings.put(contentId, newPopularity);
        // Remove from old popularity
        final Set<Integer> contentIds = this.popularityToContentIdMappings.get(oldPopularity);
        contentIds.remove(contentId);
        if (contentIds.isEmpty()) {
            this.popularityToContentIdMappings.remove(oldPopularity);
        }
        // Add to newPopularity, if > 0
        if (newPopularity > 0) {
            this.popularityToContentIdMappings.computeIfAbsent(newPopularity, _ -> new HashSet<>()).add(contentId);
        } else {
            this.popularityToContentIdMappings.remove(contentId);
        }
        // Update most recent top
        if (!this.popularityToContentIdMappings.isEmpty() && this.popularityToContentIdMappings.lastEntry().getValue().contains(contentId)) {
            this.mostRecentTop = contentId;
        }
    }

    public int mostPopular() {
        if (this.popularityToContentIdMappings.isEmpty()) {
            return -1;
        }
        return this.popularityToContentIdMappings.lastEntry().getValue().iterator().next();
    }

    public int mostRecentPopular() {
        return this.mostRecentTop;
    }

    public static void main(String[] args) {
        MostPopularTracker tracker = new MostPopularTracker();

        tracker.increasePopularity(7);
        tracker.increasePopularity(7);
        tracker.increasePopularity(8);
        System.out.println(tracker.mostPopular()); // 7

        tracker.increasePopularity(8);
        tracker.increasePopularity(8);
        System.out.println(tracker.mostPopular()); // 8

        tracker.decreasePopularity(8);
        tracker.decreasePopularity(8);
        System.out.println(tracker.mostPopular()); // 7

        tracker.decreasePopularity(7);
        tracker.decreasePopularity(7);
        tracker.decreasePopularity(8);
        System.out.println(tracker.mostPopular()); // -1

        System.out.println("Most recent popular contentId: " + tracker.mostRecentPopular()); // 8
    }
}