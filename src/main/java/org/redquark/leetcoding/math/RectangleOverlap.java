package org.redquark.leetcoding.math;

public class RectangleOverlap {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return rec1[0] < rec2[2]
                && rec2[0] < rec1[2]
                && rec1[1] < rec2[3]
                && rec2[1] < rec1[3];
    }

    public static void main(String[] args) {
        final RectangleOverlap rectangleOverlap = new RectangleOverlap();

        int[] rec1 = new int[]{0, 0, 2, 2};
        int[] rec2 = new int[]{1, 1, 3, 3};
        System.out.println(rectangleOverlap.isRectangleOverlap(rec1, rec2));

        rec1 = new int[]{0, 0, 1, 1};
        rec2 = new int[]{1, 0, 2, 1};
        System.out.println(rectangleOverlap.isRectangleOverlap(rec1, rec2));

        rec1 = new int[]{0, 0, 1, 1};
        rec2 = new int[]{0, 0, 1, 1};
        System.out.println(rectangleOverlap.isRectangleOverlap(rec1, rec2));
    }
}
