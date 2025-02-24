package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Special case
        if (head == null) {
            return null;
        }
        // Fast and slow pointers
        ListNode slow = head;
        ListNode fast = head;
        // Move fast pointers n steps
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                // If n is equal to the length of the list, we delete
                // the head node
                if (i == n - 1) {
                    head = head.next;
                }
                return head;
            }
            fast = fast.next;
        }
        // Now move slow and fast pointers together until fast
        // reaches the end of the list
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // Detach the n-th node from the list
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        final RemoveNthNodeFromEndOfList removeNthNodeFromEndOfList = new RemoveNthNodeFromEndOfList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int n = 2;
        ListNode updatedHead = removeNthNodeFromEndOfList.removeNthFromEnd(head, n);
        updatedHead.printList(updatedHead);

        head = new ListNode(1);
        head.next = new ListNode(2);
        n = 1;
        updatedHead = removeNthNodeFromEndOfList.removeNthFromEnd(head, n);
        updatedHead.printList(updatedHead);
    }
}
