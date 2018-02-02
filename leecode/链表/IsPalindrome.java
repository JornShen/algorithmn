/*
leetcode 234. Palindrome Linked List
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

*/


//　我的写法，将后半段链表进行反向
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode l = head;

        int n = 0;
        // 统计链表的数量，走到链表的中间的节点
        while (l != null) {
            n++;
            l = l.next;
        }
        ListNode q = head, p = head.next;
        for (int i = 1; i < n / 2 + 1; i++) {
            q = q.next;
            p = p.next;
        }
        
        // 将后半部分的链表反向
        int m = n % 2 == 1 ? n / 2 : n / 2 - 1;
        ListNode tmp = null;
        for (int i = 0; i < m; i++) {
            tmp = p.next;
            p.next = q;
            q = p;
            p = tmp;
        }
        
        for (int i = 0; i < n / 2; i++) {
            if (q.val != head.val) return false;
            q = q.next;
            head = head.next;
        }
        
        return true;
    }
}

//　别人的写法，写法非常巧妙,　似乎有点问题

class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) // 单个节点或者没有节点
            return true;
        if(head.val == head.next.val && head.next.next == null) // 两个节点
            return true;
        ListNode slow = head;
        ListNode cur = head.next;
        while(cur.next != null) {
        	//　每次砍掉的是尾部的节点，以 slow 为对比的节点，cur　寻找跟slow　相同的节点，　这种写法是有点问题的。
        	// 1 -> 0 -> 1 -> 2 -> 1 -> 0 -> 1
            if(slow.val == cur.next.val) {
                if(cur.next.next != null) 
                    return false;
                cur.next = null;
                slow = slow.next;
                cur = slow.next;
                if(cur == null || slow.val == cur.val) 
                    return true;
            } else 
                cur = cur.next;
        }
        return false;
    }
}

//　其他人的写法，　显然会更加简洁
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        // 寻找中间节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;   
        }

        if (fast != null) {
            slow = slow.next;
        }
        
        slow = reverse(slow);
        fast = head;
        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head !=null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}