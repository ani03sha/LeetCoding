package org.redquark.leetcoding.dynamicprogramming;

public class CountVowelsPermutation {

    public int countVowelPermutation(int n) {
        // Lookup tables for every vowel
        final long[] aLookup = new long[n];
        final long[] eLookup = new long[n];
        final long[] iLookup = new long[n];
        final long[] oLookup = new long[n];
        final long[] uLookup = new long[n];
        // Since every vowel makes 1 permutation with itself
        aLookup[0] = 1L;
        eLookup[0] = 1L;
        iLookup[0] = 1L;
        oLookup[0] = 1L;
        uLookup[0] = 1L;
        // Modulo operator
        final int MODULO = 1_000_000_007;
        // Process for remaining position
        for (int i = 1; i < n; i++) {
            aLookup[i] = (eLookup[i - 1] + iLookup[i - 1] + uLookup[i - 1]) % MODULO;
            eLookup[i] = (aLookup[i - 1] + iLookup[i - 1]) % MODULO;
            iLookup[i] = (eLookup[i - 1] + oLookup[i - 1]) % MODULO;
            oLookup[i] = (iLookup[i - 1]) % MODULO;
            uLookup[i] = (iLookup[i - 1] + oLookup[i - 1]) % MODULO;
        }
        final long result = (aLookup[n - 1] + eLookup[n - 1] + iLookup[n - 1] + oLookup[n - 1] + uLookup[n - 1]) % MODULO;
        return (int) result;
    }

    public int countVowelPermutationSpaceOptimized(int n) {
        final int MODULO = 1_000_000_007;
        long a = 1;
        long e = 1;
        long i = 1;
        long o = 1;
        long u = 1;
        // Process for all n
        for (int x = 2; x <= n; x++) {
            final long newA = (e + i + u) % MODULO;
            final long newE = (a + i) % MODULO;
            final long newI = (e + o) % MODULO;
            final long newO = (i) % MODULO;
            final long newU = (i + o) % MODULO;
            a = newA;
            e = newE;
            i = newI;
            o = newO;
            u = newU;
        }
        final long result = (a + e + i + o + u) % MODULO;
        return (int) result;
    }

    public static void main(String[] args) {
        final CountVowelsPermutation countVowelsPermutation = new CountVowelsPermutation();

        System.out.println(countVowelsPermutation.countVowelPermutation(1)); // Output: 5
        System.out.println(countVowelsPermutation.countVowelPermutationSpaceOptimized(1)); // Output: 5

        System.out.println(countVowelsPermutation.countVowelPermutation(2)); // Output: 10
        System.out.println(countVowelsPermutation.countVowelPermutationSpaceOptimized(2)); // Output: 10
    }
}
