package org.redquark.leetcoding.backtracking;

public class FindThePunishmentNumberOfAnInteger {

    public int punishmentNumber(int n) {
        // Punishment number for n
        int punishmentNumber = 0;
        // Process every number from 1 to n (inclusive)
        for (int i = 1; i <= n; i++) {
            final int square = i * i;
            if (canPartition(square, i)) {
                punishmentNumber += square;
            }
        }
        return punishmentNumber;
    }

    private boolean canPartition(int num, int target) {
        // Base case
        if (target < 0 || num < target) {
            return false;
        }
        // If we have found the target
        if (target == num) {
            return true;
        }
        return canPartition(num / 10, target - num % 10)
                || canPartition(num / 100, target - num % 100)
                || canPartition(num / 1000, target - num % 1000);
    }

    public static void main(String[] args) {
        final FindThePunishmentNumberOfAnInteger findThePunishmentNumberOfAnInteger = new FindThePunishmentNumberOfAnInteger();

        System.out.println(findThePunishmentNumberOfAnInteger.punishmentNumber(10));
        System.out.println(findThePunishmentNumberOfAnInteger.punishmentNumber(37));
        System.out.println(findThePunishmentNumberOfAnInteger.punishmentNumber(434));
    }
}
