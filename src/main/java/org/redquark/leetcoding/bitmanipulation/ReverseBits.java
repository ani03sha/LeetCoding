package org.redquark.leetcoding.bitmanipulation;

public class ReverseBits {

    public int reverseBits(int n) {
        if (n == 0) {
            return 0;
        }
        // Reversed bits
        int reversedBits = 0;
        for (int i = 0; i < 32; i++) {
            reversedBits <<= 1;
            if ((n & 1) == 1) {
                reversedBits++;
            }
            n >>= 1;
        }
        return reversedBits;
    }

    public static void main(String[] args) {
        final ReverseBits reverseBits = new ReverseBits();

        System.out.println(reverseBits.reverseBits(Integer.parseInt("00000010100101000001111010011100", 2)));
        System.out.println(reverseBits.reverseBits(Integer.parseInt("01111111111111111111111111111101", 2)));
    }
}
