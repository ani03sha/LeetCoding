package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class SortList {

    public ListNode sortList(ListNode head) {
        // Special case
        if (head == null || head.next == null) {
            return head;
        }
        // Divide the list in two halves using slow
        // and fast pointer approach
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // At this point, slow will represent the middle of the list.
        // Heads of left half and right half of the list
        ListNode left = head;
        ListNode right = slow.next;
        // Detach two halves
        slow.next = null;
        // Sort these halves recursively
        left = sortList(left);
        right = sortList(right);
        // Merge both lists
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        // Dummy node
        final ListNode dummy = new ListNode();
        // Pointer to traverse the list
        ListNode temp = dummy;
        // Loop through both lists
        while (left != null && right != null) {
            if (left.val <= right.val) {
                temp.next = left;
                left = left.next;
            } else {
                temp.next = right;
                right = right.next;
            }
            temp = temp.next;
        }
        // Process remaining nodes, if any
        while (left != null) {
            temp.next = left;
            left = left.next;
            temp = temp.next;
        }
        while (right != null) {
            temp.next = right;
            right = right.next;
            temp = temp.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        final SortList sortList = new SortList();

        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        ListNode result = sortList.sortList(head);
        result.printList(result);

        head = new ListNode(-1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(0);
        result = sortList.sortList(head);
        result.printList(result);
    }
}
