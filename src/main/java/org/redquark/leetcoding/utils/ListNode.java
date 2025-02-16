package org.redquark.leetcoding.utils;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println(temp.val);
    }
}
