/*
leetcode 148. Sort List
Sort a linked list in O(n log n) time using constant space complexity.

*/

// 归并排序实现
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // find end
        return sort(head);
    }
    // 这里和数组的归并不太一样，　默认　 null 为链表的结尾ｉ
    public ListNode sort(ListNode left) {
        if (left == null || left.next == null) return left;
        // 寻找中位数, 双指针寻找中位数
        ListNode dummy = new ListNode(0);
        dummy.next = left;
        ListNode fast = dummy, slow = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast 指针已经没有用，所以作为临时的一个变量来存放
        fast = slow.next;
        slow.next = null;
        ListNode l = sort(fast);
        ListNode r = sort(left);
        return merge(l, r);
    }
    
    public ListNode merge(ListNode left, ListNode right) {
        //　归并排序
        ListNode dummy = new ListNode(0);
        ListNode pos = dummy;
        while (left != null || right != null) {
            if (left != null && (right == null || left.val < right.val)) {
                pos.next = left;
                pos = left;
                left = left.next;
            } else {
                pos.next = right;
                pos = right;
                right = right.next;
            }
        }
        return dummy.next;
    }
}

//　别人的代码，快排序，　链表实现，　写得非常好，用快慢，相等指针对链表重新排序

class Solution {
    private ListNode tail;
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        else if (head.next == null) {
            tail = head;
            return head;
        }
        ListNode biggerListHead = null;　// 相当于　partion 函数的右指针 
        ListNode smallerListHead = null;　// 相当于　partion 函数的左指针
        ListNode equalListHead = head; // 中间的比较节点
        ListNode node = head.next;
        head.next = null;
        while (node != null) {
            ListNode next = node.next;
            if (node.val == head.val) { // 前插方法
                node.next = equalListHead; 
                equalListHead = node;
            } else if (node.val > head.val) {
                node.next = biggerListHead;
                biggerListHead = node;
            } else {
                node.next = smallerListHead;
                smallerListHead = node;                
            }
            node = next;
        }
        node = head;
        // 合并
        if (smallerListHead != null) {
            head = sortList(smallerListHead);　//　最小的数 
            tail.next = equalListHead;
        } else {
            head = equalListHead;
        }
        if (biggerListHead != null) {
            node.next = sortList(biggerListHead);
        } else {
            tail = node;
        }
        return head;
    }
}