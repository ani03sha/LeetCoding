package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Special cases
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // Dummy node for the final output
        final ListNode dummy = new ListNode();
        // Pointer to keep track of the current node
        ListNode temp = dummy;
        // Variable to store carry
        int carry = 0;
        // Process both lists
        while (l1 != null && l2 != null) {
            final int sum = carry + l1.val + l2.val;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        // Process remaining nodes, if any
        while (l1 != null) {
            final int sum = carry + l1.val;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            final int sum = carry + l2.val;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;
            temp = temp.next;
            l2 = l2.next;
        }
        // Handle residual carry
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        final AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);
        result.printList(result);

        l1 = new ListNode(0);

        l2 = new ListNode(0);

        result = addTwoNumbers.addTwoNumbers(l1, l2);
        result.printList(result);

        l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);

        l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);

        result = addTwoNumbers.addTwoNumbers(l1, l2);
        result.printList(result);
    }
}
