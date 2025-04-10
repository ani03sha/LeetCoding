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

    public void printCircularLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        ListNode current = head;
        do {
            System.out.print(current.val + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(back to head: " + head.val + ")");
    }
}
