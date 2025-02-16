package org.redquark.leetcoding.linkedlist;

import org.redquark.leetcoding.utils.ListNode;

public class IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Special case
        if (headA == null || headB == null) {
            return null;
        }
        // Calculate the lengths of both lists
        int lengthA = 0;
        ListNode temp = headA;
        while (temp != null) {
            lengthA++;
            temp = temp.next;
        }
        int lengthB = 0;
        temp = headB;
        while (temp != null) {
            lengthB++;
            temp = temp.next;
        }
        // Find out the longer list and the difference between
        // their lengths
        if (lengthA > lengthB) {
            int difference = lengthA - lengthB;
            // Move first list difference steps
            while (difference > 0) {
                headA = headA.next;
                difference--;
            }
        } else {
            int difference = lengthB - lengthA;
            while (difference > 0) {
                headB = headB.next;
                difference--;
            }
        }
        // Now, keep moving both lists ahead until we find the
        // common node between the two
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    public static void main(String[] args) {
        final IntersectionOfTwoLinkedLists intersectionOfTwoLinkedLists = new IntersectionOfTwoLinkedLists();

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = new ListNode(8);
        headA.next.next.next = new ListNode(4);
        headA.next.next.next.next = new ListNode(5);

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = headA.next.next;

        System.out.println(intersectionOfTwoLinkedLists.getIntersectionNode(headA, headB).val);

        headA = new ListNode(1);
        headA.next = new ListNode(9);
        headA.next.next = new ListNode(1);
        headA.next.next.next = new ListNode(2);
        headA.next.next.next.next = new ListNode(4);

        headB = new ListNode(3);
        headB.next = headA.next.next.next;

        System.out.println(intersectionOfTwoLinkedLists.getIntersectionNode(headA, headB).val);

        headA = new ListNode(2);
        headA.next = new ListNode(6);
        headA.next.next = new ListNode(4);

        headB = new ListNode(1);
        headB.next = new ListNode(5);

        System.out.println(intersectionOfTwoLinkedLists.getIntersectionNode(headA, headB));
    }
}
