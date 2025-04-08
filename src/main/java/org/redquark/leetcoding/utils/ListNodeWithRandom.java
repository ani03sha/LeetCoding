package org.redquark.leetcoding.utils;

public class ListNodeWithRandom {

    public int val;
    public ListNodeWithRandom next;
    public ListNodeWithRandom random;

    public ListNodeWithRandom(int val) {
        this.val = val;
    }

    public static void printList(ListNodeWithRandom head) {
        ListNodeWithRandom temp = head;
        while (temp != null && temp.next != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println(temp.val);
    }
}
