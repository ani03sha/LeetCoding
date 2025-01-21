package org.redquark.leetcoding.bitmanipulation;

public class NumberOf1Bits {

    public int hammingWeight(int n) {
        int setBitCount = 0;
        while (n > 0) {
            n &= (n - 1);
            setBitCount++;
        }
        return setBitCount;
    }

    public static void main(String[] args) {
        final NumberOf1Bits numberOf1Bits = new NumberOf1Bits();

        System.out.println(numberOf1Bits.hammingWeight(11));
        System.out.println(numberOf1Bits.hammingWeight(128));
        System.out.println(numberOf1Bits.hammingWeight(2147483645));
    }
}
