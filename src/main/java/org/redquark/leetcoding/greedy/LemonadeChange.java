package org.redquark.leetcoding.greedy;

public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        // Count of $5 and $10 bills
        int fives = 0;
        int tens = 0;
        // Process the array
        for (int bill : bills) {
            if (bill == 5) {
                fives++;
            } else if (bill == 10) {
                fives--;
                tens++;
            } else if (tens > 0) {
                tens--;
                fives--;
            } else {
                fives -= 3;
            }
            if (fives < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final LemonadeChange lemonadeChange = new LemonadeChange();

        int[] bills = new int[]{5, 5, 5, 10, 20};
        System.out.println(lemonadeChange.lemonadeChange(bills));

        bills = new int[]{5, 5, 10, 10, 20};
        System.out.println(lemonadeChange.lemonadeChange(bills));
    }
}
