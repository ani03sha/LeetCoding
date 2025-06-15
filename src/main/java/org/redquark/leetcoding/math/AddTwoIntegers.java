package org.redquark.leetcoding.math;

public class AddTwoIntegers {

    public int sum(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        final AddTwoIntegers addTwoIntegers = new AddTwoIntegers();

        System.out.println(addTwoIntegers.sum(12, 5));
        System.out.println(addTwoIntegers.sum(-10, 4));
    }
}
