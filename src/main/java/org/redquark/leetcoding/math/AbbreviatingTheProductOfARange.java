package org.redquark.leetcoding.math;

public class AbbreviatingTheProductOfARange {

    public String abbreviateProduct(int left, int right) {
        final long MAXIMUM_SUFFIX = 100_000_000_000L;
        double product = 1.0;
        long suffix = 1;
        int digitCount = 0;
        int zeroCount = 0;

        for (int number = left; number <= right; ++number) {
            product *= number;
            while (product >= 1.0) {
                product /= 10;
                ++digitCount;
            }
            suffix *= number;
            while (suffix % 10 == 0) {
                suffix /= 10;
                ++zeroCount;
            }
            if (suffix > MAXIMUM_SUFFIX)
                suffix %= MAXIMUM_SUFFIX;
        }

        if (digitCount - zeroCount <= 10) {
            final long tens = (long) Math.pow(10, digitCount - zeroCount);
            return (long) (product * tens + 0.5) + "e" + zeroCount;
        }

        final String first5 = String.valueOf((long) (product * Math.pow(10, 5)));
        String last5 = String.valueOf(suffix);
        last5 = last5.substring(last5.length() - 5);
        return first5 + "..." + last5 + "e" + zeroCount;
    }

    public static void main(String[] args) {
        final AbbreviatingTheProductOfARange abbreviatingTheProductOfARange = new AbbreviatingTheProductOfARange();

        System.out.println(abbreviatingTheProductOfARange.abbreviateProduct(1, 4));
        System.out.println(abbreviatingTheProductOfARange.abbreviateProduct(2, 11));
        System.out.println(abbreviatingTheProductOfARange.abbreviateProduct(371, 375));
        System.out.println(abbreviatingTheProductOfARange.abbreviateProduct(44, 9556));
        System.out.println(abbreviatingTheProductOfARange.abbreviateProduct(4649, 4651));
    }
}
