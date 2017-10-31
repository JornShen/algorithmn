/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.

*/


//  使用双指针的思路,
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        if (head == null) return head;
        
       // 双指针
        ListNode pre = head, tail = null;
        // 前面的指针先走 n 步
        for (int i = 0; i < n - 1; i++) {
            pre = pre.next;
        }
        
        // 前后指针一起走
        while (pre.next != null) {
            pre = pre.next;
            if (tail == null) {
                tail = head;
            } else {
                tail = tail.next;
            }
        }
        
        if (tail == null) { // 删除头节点
            return head.next;
        } else {
            tail.next = tail.next.next;
        }
        return head; 
    }
}








