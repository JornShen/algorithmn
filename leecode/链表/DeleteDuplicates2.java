/*
leetcode 83:

Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode l = head;
        while (l != null) {
            ListNode tmp = l.next;
            while (tmp != null && l.val == tmp.val) { //　　向右探寻，直到寻找到一个不同的数
                tmp = tmp.next;
            }
            l.next = tmp;
            l = tmp;
        }
        return head;
    }
}
