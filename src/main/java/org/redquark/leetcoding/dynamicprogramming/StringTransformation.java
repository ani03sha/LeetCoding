package org.redquark.leetcoding.dynamicprogramming;

public class StringTransformation {

    private static final int MODULO = 1_000_000_007;
    private static final long BASE = 911; // Prime base for hashing

    public int numberOfWays(String s, String t, long k) {
        int n = s.length();
        char[] sCharacters = s.toCharArray();
        char[] tCharacters = t.toCharArray();

        // Compute hash of s
        long hashS = computeHash(sCharacters);

        // Build t + t and compute prefix hashes
        char[] tt = new char[2 * n];
        System.arraycopy(tCharacters, 0, tt, 0, n);
        System.arraycopy(tCharacters, 0, tt, n, n);
        long[] prefixHash = new long[2 * n + 1];
        long[] power = new long[2 * n + 1];
        power[0] = 1;
        for (int i = 0; i < 2 * n; i++) {
            prefixHash[i + 1] = (prefixHash[i] * BASE + tt[i]) % MODULO;
            power[i + 1] = (power[i] * BASE) % MODULO;
        }

        // Find valid rotations
        boolean[] isPossible = new boolean[n];
        for (int i = 0; i < n; i++) {
            long hash = getHash(prefixHash, power, i, i + n - 1);
            if (hash == hashS) {
                isPossible[i] = true;
            }
        }

        // Matrix exponentiation to get dp[k]
        long[][] A = {
                {0, 1},
                {(n - 1) % MODULO, (n - 2 + MODULO) % MODULO}
        };
        long[][] Ak = matrixPower(A, k);

        // From initial vector [1, 0]
        long dp0 = Ak[0][0];
        long dp1 = Ak[0][1];

        // Count good rotations
        int good0 = isPossible[0] ? 1 : 0;
        int good1 = 0;
        for (int i = 1; i < n; i++) {
            if (isPossible[i]) good1++;
        }

        return (int) ((dp0 * good0 + dp1 * good1) % MODULO);
    }

    // Compute hash of a char array
    private long computeHash(char[] s) {
        long hash = 0;
        for (char c : s) {
            hash = (hash * BASE + c) % MODULO;
        }
        return hash;
    }

    // Get substring hash using prefix hash and powers
    private long getHash(long[] prefixHash, long[] pow, int l, int r) {
        return (prefixHash[r + 1] - (prefixHash[l] * pow[r - l + 1]) % MODULO + MODULO) % MODULO;
    }

    // Matrix exponentiation
    private long[][] matrixPower(long[][] A, long exponent) {
        long[][] result = {{1, 0}, {0, 1}}; // Identity matrix
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = matrixMultiply(result, A);
            }
            A = matrixMultiply(A, A);
            exponent >>= 1;
        }
        return result;
    }

    private long[][] matrixMultiply(long[][] A, long[][] B) {
        long[][] res = new long[2][2];
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 2; ++j)
                for (int k = 0; k < 2; ++k)
                    res[i][j] = (res[i][j] + A[i][k] * B[k][j]) % MODULO;
        return res;
    }

    public static void main(String[] args) {
        final StringTransformation stringTransformation = new StringTransformation();

        String s = "abcd";
        String t = "cdab";
        long k = 2;
        int result = stringTransformation.numberOfWays(s, t, k);
        System.out.println("Number of ways to transform s to t: " + result);

        s = "ababab";
        t = "ababab";
        k = 1;
        result = stringTransformation.numberOfWays(s, t, k);
        System.out.println("Number of ways to transform s to t: " + result);
    }
}
