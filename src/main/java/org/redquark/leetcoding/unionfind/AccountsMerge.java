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
        // List to store merged accounts
        final List<List<String>> mergedAccounts = new ArrayList<>();
        // Special case
        if (accounts == null || accounts.isEmpty()) {
            return mergedAccounts;
        }
        final UnionFind unionFind = new UnionFind(accounts.size());
        // Map to store emails and their indices
        final Map<String, Integer> emailToAccount = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                final String email = accounts.get(i).get(j);
                if (emailToAccount.containsKey(email)) {
                    unionFind.union(i, emailToAccount.get(email));
                } else {
                    emailToAccount.put(email, i);
                }
            }
        }
        // Group emails by their leader
        Map<Integer, Set<String>> emailGroup = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailToAccount.entrySet()) {
            String email = entry.getKey();
            int accountIndex = entry.getValue();
            int leader = unionFind.find(accountIndex);
            emailGroup.computeIfAbsent(leader, k -> new TreeSet<>()).add(email);
        }
        // Add merged accounts with account names
        for (Map.Entry<Integer, Set<String>> entry : emailGroup.entrySet()) {
            int leader = entry.getKey();
            Set<String> emails = entry.getValue();
            List<String> mergedAccount = new ArrayList<>();
            // Add account name (first element of the leader's account)
            mergedAccount.add(accounts.get(leader).get(0));
            // Add sorted emails
            mergedAccount.addAll(emails);
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
            Arrays.setAll(this.parents, i -> i);
            Arrays.fill(this.ranks, 1);
        }

        public void union(int a, int b) {
            final int rootA = find(a);
            final int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            if (this.ranks[rootA] >= this.ranks[rootB]) {
                this.parents[rootB] = rootA;
                this.ranks[rootA] += this.ranks[rootB];
            } else {
                this.parents[rootA] = rootB;
                this.ranks[rootB] += this.ranks[rootA];
            }
        }


        private int find(int a) {
            if (this.parents[a] != a) {
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
