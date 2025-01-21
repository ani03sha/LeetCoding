package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        // Special case
        if (head == null || head.next == null) {
            return head;
        }
        // Dummy node
        final ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // Node to traverse the list
        ListNode current = dummy;
        // Process the list
        while (current.next != null && current.next.next != null) {
            // First and second nodes of the pair
            ListNode first = current.next;
            ListNode second = current.next.next;
            // Swap the nodes
            first.next = second.next;
            second.next = first;
            current.next = second;
            current = current.next.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        final SwapNodesInPairs swapNodesInPairs = new SwapNodesInPairs();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode swappedNodes = swapNodesInPairs.swapPairs(head);
        swappedNodes.printList(swappedNodes);
    }
}
