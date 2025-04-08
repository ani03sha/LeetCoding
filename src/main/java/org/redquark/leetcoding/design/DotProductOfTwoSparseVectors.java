package org.redquark.leetcoding.design;

import java.util.ArrayList;
import java.util.List;

public class DotProductOfTwoSparseVectors {

    static class SparseVector {

        // List to store the pair of index and its value.
        // We will store only non-zero values
        private final List<int[]> pairs;

        SparseVector(int[] nums) {
            this.pairs = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    pairs.add(new int[]{i, nums[i]});
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vector) {
            int dotProduct = 0;
            // Pointers to traverse vector1 and vector2;
            int i = 0;
            int j = 0;
            // Process both vectors
            while (i < this.pairs.size() && j < vector.pairs.size()) {
                if (this.pairs.get(i)[0] == vector.pairs.get(j)[0]) {
                    dotProduct += this.pairs.get(i)[1] * vector.pairs.get(j)[1];
                    i++;
                    j++;
                } else if (this.pairs.get(i)[0] < vector.pairs.get(j)[0]) {
                    i++;
                } else {
                    j++;
                }
            }
            return dotProduct;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 0, 0, 2, 3};
        int[] nums2 = new int[]{0, 3, 0, 4, 0};
        SparseVector vector1 = new SparseVector(nums1);
        SparseVector vector2 = new SparseVector(nums2);
        System.out.println(vector1.dotProduct(vector2));

        nums1 = new int[]{0, 1, 0, 0, 0};
        nums2 = new int[]{0, 0, 0, 0, 2};
        vector1 = new SparseVector(nums1);
        vector2 = new SparseVector(nums2);
        System.out.println(vector1.dotProduct(vector2));

        nums1 = new int[]{0, 1, 0, 0, 2, 0, 0};
        nums2 = new int[]{1, 0, 0, 0, 3, 0, 4};
        vector1 = new SparseVector(nums1);
        vector2 = new SparseVector(nums2);
        System.out.println(vector1.dotProduct(vector2));

    }
}
