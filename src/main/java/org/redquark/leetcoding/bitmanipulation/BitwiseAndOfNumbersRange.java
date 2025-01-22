package org.redquark.leetcoding.bitmanipulation;

public class BitwiseAndOfNumbersRange {

    public int rangeBitwiseAnd(int left, int right) {
        while (right > left) {
            right &= (right - 1);
        }
        return left & right;
    }

    public static void main(String[] args) {
        BitwiseAndOfNumbersRange bitwiseAndOfNumbersRange = new BitwiseAndOfNumbersRange();

        System.out.println(bitwiseAndOfNumbersRange.rangeBitwiseAnd(5, 7));
        System.out.println(bitwiseAndOfNumbersRange.rangeBitwiseAnd(0, 0));
        System.out.println(bitwiseAndOfNumbersRange.rangeBitwiseAnd(1, 2147483647));
    }
}
