package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        // Special case
        if (head == null) {
            return null;
        }
        // Find the length of the list
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            n++;
        }
        // Effective value of k
        k %= n;
        // Fast and slow pointers
        ListNode slow = head;
        ListNode fast = head;
        // Move fast pointers k steps ahead
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // Now, move both pointers together the fast pointer
        // reaches the end of the list
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // At this point, fast.next will represent the current head
        fast.next = head;
        // Update the current head as slow.next
        head = slow.next;
        // Detach the list
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {
        final RotateList rotateList = new RotateList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int k = 2;
        ListNode rotatedList = rotateList.rotateRight(head, k);
        rotatedList.printList(rotatedList);

        k = 1;
        rotatedList = rotateList.rotateRight(head, k);
        rotatedList.printList(rotatedList);
    }
}
