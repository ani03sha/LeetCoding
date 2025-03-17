package org.redquark.leetcoding.math;

import java.util.Arrays;

public class CountPrimes {

    public int countPrimes(int n) {
        // Boolean array to store if a current number is prime or not
        final boolean[] isPrime = new boolean[n];
        // Initially assume all numbers are prime
        Arrays.fill(isPrime, true);
        // Count of primes less than n
        int primeCount = 0;
        // Process every number from 2 to n
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primeCount++;
                // Mark all multiples of i as composite
                for (int j = i * 2; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return primeCount;
    }

    public static void main(String[] args) {
        final CountPrimes countPrimes = new CountPrimes();

        System.out.println(countPrimes.countPrimes(10));
        System.out.println(countPrimes.countPrimes(0));
        System.out.println(countPrimes.countPrimes(1));
        System.out.println(countPrimes.countPrimes(5000000));
    }
}
