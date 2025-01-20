package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        // Special case
        if (head == null) {
            return false;
        }
        // Slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        // Move both pointers until fast reaches the end before meeting slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // Check if both slow and fast are equal
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final LinkedListCycle linkedListCycle = new LinkedListCycle();

        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        System.out.println(linkedListCycle.hasCycle(head));

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = head;
        System.out.println(linkedListCycle.hasCycle(head));

        head = new ListNode(1);
        System.out.println(linkedListCycle.hasCycle(head));
    }
}
