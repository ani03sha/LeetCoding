package org.redquark.leetcoding.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // List to store the merged accounts
        final List<List<String>> mergedAccounts = new ArrayList<>();
        // Special case
        if (accounts == null || accounts.isEmpty()) {
            return mergedAccounts;
        }
        final int n = accounts.size();
        // Create an instance of union find data structure
        final UnionFind unionFind = new UnionFind(n);
        // Map to store email and their indices
        final Map<String, Integer> emailToAccount = new HashMap<>();
        // Process every account
        for (int i = 0; i < n; i++) {
            // Current account we are processing
            final List<String> account = accounts.get(i);
            // Now, combine emails in this account using union function
            for (int j = 1; j < account.size(); j++) {
                final String email = account.get(j);
                // If we have already encountered this email, we merge
                if (emailToAccount.containsKey(email)) {
                    unionFind.union(i, emailToAccount.get(email));
                }
                // If this is a new email, we add it to the map
                else {
                    emailToAccount.put(email, i);
                }
            }
        }
        // At this point, emails are merged, and we need to find the root of every email.
        // Once we find it, we add it to the group in sorted order
        final Map<Integer, Set<String>> emailGroup = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToAccount.entrySet()) {
            final String email = entry.getKey();
            final int accountIndex = entry.getValue();
            // Find the root of the group
            final int leaderIndex = unionFind.find(accountIndex);
            // Add this to the group
            emailGroup.computeIfAbsent(leaderIndex, _ -> new TreeSet<>()).add(email);
        }
        // Add the above-created group with the names of account holders
        for (Map.Entry<Integer, Set<String>> entry : emailGroup.entrySet()) {
            final int leaderIndex = entry.getKey();
            final Set<String> emails = entry.getValue();
            // Create a list for this merged account
            final List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(accounts.get(leaderIndex).getFirst());
            // Add all emails to the mergedAccount list
            mergedAccount.addAll(emails);
            // Add this list to final output
            mergedAccounts.add(mergedAccount);
        }
        return mergedAccounts;
    }

    static class UnionFind {
        private final int[] parents;
        private final int[] ranks;

        UnionFind(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            // In the beginning, every node is its own parent
            Arrays.setAll(this.parents, i -> i);
            // Every node has rank one in the beginning
            Arrays.fill(this.ranks, 1);
        }

        void union(int a, int b) {
            // Find parents of both a and b
            final int rootA = find(a);
            final int rootB = find(b);
            // If both nodes belong to the same group, we return
            if (rootA == rootB) {
                return;
            }
            // Merge nodes
            if (this.ranks[rootA] > this.ranks[rootB]) {
                this.parents[rootB] = this.parents[rootA];
                this.ranks[rootA] += this.ranks[rootB];
            } else {
                this.parents[rootA] = this.parents[rootB];
                this.ranks[rootB] += this.ranks[rootA];
            }
        }

        int find (int a) {
            if (this.parents[a] != a) {
                // Path compression
                this.parents[a] = find(this.parents[a]);
            }
            return this.parents[a];
        }
    }

    public static void main(String[] args) {
        final AccountsMerge accountsMerge = new AccountsMerge();

        List<List<String>> accounts = new ArrayList<>();
        accounts.add(List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(List.of("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(List.of("Mary", "mary@mail.com"));
        accounts.add(List.of("John", "johnnybravo@mail.com"));
        System.out.println(accountsMerge.accountsMerge(accounts));

        accounts = new ArrayList<>();
        accounts.add(List.of("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"));
        accounts.add(List.of("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"));
        accounts.add(List.of("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"));
        accounts.add(List.of("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"));
        accounts.add(List.of("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co"));
        System.out.println(accountsMerge.accountsMerge(accounts));
    }
}
