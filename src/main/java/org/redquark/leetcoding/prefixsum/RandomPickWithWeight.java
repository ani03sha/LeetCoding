package org.redquark.leetcoding.prefixsum;

public class RandomPickWithWeight {

    // Array to store the prefix sum
    private final int[] prefixSum;
    // Total sum of the elements in the array
    private final int totalSum;

    public RandomPickWithWeight(int[] w) {
        final int n = w.length;
        this.prefixSum = new int[n];
        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += w[i];
            this.prefixSum[i] = prefixSum;
        }
        this.totalSum = prefixSum;
    }

    public int pickIndex() {
        final double target = this.totalSum * Math.random();
        // Perform binary search to find the position of this
        // target in the prefixSum array.
        int left = 0;
        int right = this.prefixSum.length;
        while (left < right) {
            final int middle = left + (right - left) / 2;
            if (this.prefixSum[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] w = new int[]{1};
        RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight(w);
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println("----------------x-----------------");

        w = new int[]{1, 3};
        randomPickWithWeight = new RandomPickWithWeight(w);
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println("----------------x-----------------");

        w = new int[]{3, 14, 1, 7};
        randomPickWithWeight = new RandomPickWithWeight(w);
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println(randomPickWithWeight.pickIndex());
        System.out.println("----------------x-----------------");
    }
}
