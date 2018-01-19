/*
leetcode 206. Reverse Linked List

Reverse a singly linked list.

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode q = head, p = head.next;
        q.next = null;
        while (p !=  null) {
            ListNode tmp = p.next;
            p.next = q;
            q = p;
            p = tmp;
        }
        return q;
    }
}
