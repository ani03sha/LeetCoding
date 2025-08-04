package org.redquark.leetcoding.math;

public class BulbSwitcher {

    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }

    public static void main(String[] args) {
        final BulbSwitcher bulbSwitcher = new BulbSwitcher();

        // Test case 1
        int n1 = 3;
        System.out.println("Test Case 1: " + bulbSwitcher.bulbSwitch(n1)); // Output: 1

        // Test case 2
        int n2 = 0;
        System.out.println("Test Case 2: " + bulbSwitcher.bulbSwitch(n2)); // Output: 0

        // Test case 3
        int n3 = 99999999;
        System.out.println("Test Case 3: " + bulbSwitcher.bulbSwitch(n3)); // Output: 9999
    }
}
