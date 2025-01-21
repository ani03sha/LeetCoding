package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Special case
        if (head == null) {
            return null;
        }
        // First of all traverse until the left - 1 nodes
        ListNode previous = null;
        ListNode current = head;
        for (int i = 0; i < left - 1; i++) {
            previous = current;
            current = current.next;
        }
        // Last node before left
        ListNode leftPrevious = previous;
        // Next node
        ListNode next;
        // Reference to the current node
        ListNode temp = current;
        // Reverse list between left and right pointers
        while (left <= right) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            left++;
        }
        if (leftPrevious != null) {
            leftPrevious.next = previous;
        } else {
            head = previous;
        }
        temp.next = current;
        return head;
    }

    public static void main(String[] args) {
        final ReverseLinkedListII reverseLinkedListII = new ReverseLinkedListII();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        int left = 2;
        int right = 4;
        ListNode reversedHead = reverseLinkedListII.reverseBetween(head, left, right);
        reversedHead.printList(reversedHead);
    }
}
