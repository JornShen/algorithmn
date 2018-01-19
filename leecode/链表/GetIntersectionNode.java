/*
leetcode 160. Intersection of Two Linked Lists

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

*/

// 大神，　思路差不多一样，但是做了一些必要的优化
// coding 得非常棒
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    
    public ListNode getIntersectionNode(ListNode ha, ListNode hb) {
    	
        if (ha==null || hb==null) return null;
        if (ha == hb) return ha;　// 优化点

        int stepA = 0;
        ListNode ta = ha;
        while (ta.next != null) {
            ta = ta.next;
            stepA++;
        }

        int stepB = 0;
        ListNode tb = hb;
        while (tb.next != null) {
            tb = tb.next;
            stepB++;
        }

        if (ta != tb) return null;　// 肯定不会相交

        // 第一次见这样的写法
        ta = ha; tb = hb;
        if (stepA > stepB) while (stepA > stepB) {
            ta = ta.next;
            --stepA;
        } else if (stepA < stepB) while (stepA < stepB) {
            tb = tb.next;
            --stepB;
        }

        while (ta != tb) {
            ta = ta.next;
            tb = tb.next;
        }
        return ta;
    }
}

// 我的写法

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        if (headA == null || headB == null) return null;
        // 统计链表的长度
        int a = 0, b = 0;

        ListNode head = headA;
        while (head != null) {
            a++;
            head = head.next;
        }

        head = headB;
        while (head != null) {
            b++;
            head = head.next;
        }

        int diff = 0;
        if (a > b) {
            diff = a - b;
            while (diff != 0) {
                headA = headA.next;
                diff--;
            }
        } else if (b > a) {
            diff = b - a;
            while (diff != 0) {
                headB = headB.next;
                diff--;
            }
        }

        while (headA != null && headB != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
}