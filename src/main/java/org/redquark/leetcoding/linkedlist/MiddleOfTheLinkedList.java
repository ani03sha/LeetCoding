package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        // Special case
        if (head == null) {
            return null;
        }
        // Slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        // Move slow pointer one step and fast pointers two steps ahead
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        final MiddleOfTheLinkedList middleOfTheLinkedList = new MiddleOfTheLinkedList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(middleOfTheLinkedList.middleNode(head).val);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println(middleOfTheLinkedList.middleNode(head).val);
    }
}
