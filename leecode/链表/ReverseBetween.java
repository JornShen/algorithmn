/*
leetcode 92:

Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.

*/

／／　创建头伪节点 dummy,　至少需要两个指针进行遍历． 

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n || head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head; //设置伪节点
        ListNode start = dummy;
        for (int i = 0; i < m - 1; i++) {
            start = start.next;
        }
        ListNode pre = start.next.next, later = start.next;
        for (int i = 0; i < n - m; i++) {
            ListNode tmp = pre.next;
            pre.next = later;
            later = pre;
            pre = tmp;
        }
        start.next.next = pre;
        start.next = later;
        return dummy.next;
    }
}
