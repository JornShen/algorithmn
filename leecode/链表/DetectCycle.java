/*
leetcode 142. Linked List Cycle II
Given a linked list, return the node where the cycle begins.
 If there is no cycle, return null.

Note: Do not modify the linked list.

*/

//　其中一种解法
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode tmp = new ListNode(0);
        tmp.next = head;
        ListNode fast = tmp, slow = tmp;

        // 快指针走两步，　慢指针走一步, 相交的地方或者不存在环的情况下退出
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) break;
        }
        if (fast == null || fast.next == null) return null;

        // 寻找交会点
        fast = tmp;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

//　解析为什么要这样做

a: 　起始点到交会点,  b: 从交会点到相遇的点, c: 从相遇点绕圈到交会点
第一次相遇时slow走过的距离：a+b，fast走过的距离：a+b+c+b。
fast 是　slow  的两倍
2 *　(a + b)　＝　a + b + c + b　得到 a = c

[参考地址](https://www.cnblogs.com/hiddenfox/p/3408931.html)

// 还有其他的解法将前后的之间的 next 指针断开. 

