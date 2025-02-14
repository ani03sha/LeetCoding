package org.redquark.leetcoding.prefixsum;

import java.util.ArrayList;
import java.util.List;

public class ProductOfTheLastKNumbers {

    static class ProductOfNumbers {

        private final List<Integer> products;

        public ProductOfNumbers() {
            // Initialize the list with 1
            this.products = new ArrayList<>();
            this.products.add(1);
        }

        public void add(int num) {
            // If the current element is zero, we reinitialize
            // the list
            if (num == 0) {
                this.products.clear();
                this.products.add(1);
            }
            // Maintain the last prefix product
            else {
                this.products.add(num * this.products.getLast());
            }
        }

        public int getProduct(int k) {
            // Given constraint ensures that there are at least k
            // elements in the stream before calling this method,
            // so we don't have to worry about that
            return k < this.products.size() ? this.products.getLast() / this.products.get(this.products.size() - k - 1) : 0;
        }
    }

    public static void main(String[] args) {
        final ProductOfNumbers productOfNumbers = new ProductOfNumbers();

        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        System.out.println(productOfNumbers.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        System.out.println(productOfNumbers.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        System.out.println(productOfNumbers.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        System.out.println(productOfNumbers.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32
    }
}
