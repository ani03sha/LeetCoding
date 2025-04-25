package org.redquark.leetcoding.arrays;

public class FriendRequestsOfAppropriateAges {

    public int numFriendRequests(int[] ages) {
        // Array to store the count of people of every age
        final int[] ageCounts = new int[121];
        for (int age : ages) {
            ageCounts[age]++;
        }
        // Total number of friend requests made
        int friendRequests = 0;
        // Process combination of every age
        for (int i = 0; i <= 120; i++) {
            // Count of people of age i
            int countA = ageCounts[i];
            for (int j = 0; j <= 120; j++) {
                // Count of people of age j
                int countB = ageCounts[j];
                // Now, check for conditions
                if ((j <= 0.5 * i + 7) || (i < j) || (j > 100 && i < 100)) {
                    continue;
                }
                friendRequests += countA * countB;
                // But same person can't send request to themselves
                if (i == j) {
                    friendRequests -= countA;
                }
            }
        }
        return friendRequests;
    }

    public static void main(String[] args) {
        final FriendRequestsOfAppropriateAges friendRequestsOfAppropriateAges = new FriendRequestsOfAppropriateAges();

        int[] ages = new int[]{16, 16};
        System.out.println(friendRequestsOfAppropriateAges.numFriendRequests(ages));

        ages = new int[]{16, 17, 18};
        System.out.println(friendRequestsOfAppropriateAges.numFriendRequests(ages));

        ages = new int[]{20, 30, 100, 110, 120};
        System.out.println(friendRequestsOfAppropriateAges.numFriendRequests(ages));
    }
}
