package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        // Special case
        if (head == null) {
            return head;
        }
        // Previous, current and next pointers
        ListNode previous = null;
        ListNode current = head;
        ListNode next;
        // Traverse the list
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    public static void main(String[] args) {
        final ReverseLinkedList reverseLinkedList = new ReverseLinkedList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode reversedHead = reverseLinkedList.reverseList(head);
        reversedHead.printList(reversedHead);

        head = new ListNode(1);
        head.next = new ListNode(2);
        reversedHead = reverseLinkedList.reverseList(head);
        reversedHead.printList(reversedHead);
    }
}
