package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Special cases
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // Dummy head
        final ListNode head = new ListNode();
        // Pointer to traverse the list
        ListNode temp = head;
        // Traverse through both lists
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                temp.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            temp = temp.next;
        }
        // Process remaining nodes
        while (list1 != null) {
            temp.next = new ListNode(list1.val);
            list1 = list1.next;
            temp = temp.next;
        }
        while (list2 != null) {
            temp.next = new ListNode(list2.val);
            list2 = list2.next;
            temp = temp.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        final MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();

        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode mergedList = mergeTwoSortedLists.mergeTwoLists(list1, list2);
        mergedList.printList(mergedList);

        list2 = new ListNode(1);
        mergedList = mergeTwoSortedLists.mergeTwoLists(null, list2);
        mergedList.printList(mergedList);
    }
}
