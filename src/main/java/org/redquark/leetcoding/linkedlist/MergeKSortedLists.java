package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        // Special case
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int start, int end) {
        // Base case
        if (start >= end) {
            return lists[start];
        }
        // Find the middle index
        final int middle = start + (end - start) / 2;
        // Recursive call to the left portion of lists
        final ListNode left = mergeKLists(lists, start, middle);
        // Recursive call to the right portion of lists
        final ListNode right = mergeKLists(lists, middle + 1, end);
        // Merge both left and right portions
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        // Dummy node
        final ListNode dummy = new ListNode();
        // Pointer for traversing the list
        ListNode temp = dummy;
        // Process both lists
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
        // Process remaining nodes
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
        final MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();

        final ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        final ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        final ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        final ListNode[] lists = new ListNode[]{head1, head2, head3};

        final ListNode mergedList = mergeKSortedLists.mergeKLists(lists);
        mergedList.printList(mergedList);

    }
}
