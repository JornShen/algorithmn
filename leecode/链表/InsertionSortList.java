/*
leetcode 147. Insertion Sort List

Sort a linked list using insertion sort.
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 插入排序
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pos = head;
        while (pos.next != null) { // 尾节点
            int cmp = pos.next.val; // 对 pos 的 next 进行排序
            if (cmp < pos.val) {
                ListNode tmp = pos.next;
                pos.next = tmp.next;
                //　寻找位置
                ListNode l = dummy;
                ListNode r = dummy.next;
                while (r.val < tmp.val) { // 稳定性
                    r = r.next;
                    l = l.next;
                }
                tmp.next = r;
                l.next = tmp;
            } else pos = pos.next;
        }
        return dummy.next;
    }
}