package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class ReorderList {

    public void reorderList(ListNode head) {
        // Special case
        if (head == null) {
            return;
        }
        /* Following are the steps, we are going to follow
         * 1. Find the middle of list
         * 2. Detach both halves
         * 3. Reverse the second half of list
         * 4. Take one node from each half and add together
         */

        // Step 1
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // Step 2
        // Head of second half
        ListNode secondHead = slow.next;
        slow.next = null;
        // Step 3
        ListNode previous = null;
        ListNode current = secondHead;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        secondHead = previous;
        // Step 4
        ListNode temp = head;
        ListNode secondTemp = secondHead;
        while (temp != null && secondTemp != null) {
            ListNode temp1 = temp.next;
            ListNode temp2 = secondTemp.next;
            temp.next = secondTemp;
            secondTemp.next = temp1;
            temp = temp1;
            secondTemp = temp2;
        }
    }

    public static void main(String[] args) {
        final ReorderList reorderList = new ReorderList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        reorderList.reorderList(head);
        head.printList(head);

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reorderList.reorderList(head);
        head.printList(head);
    }
}
