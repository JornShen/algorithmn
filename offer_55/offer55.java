
/****
一个链表中包含环，请找出该链表的环的入口结点。
**/

/*
 public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
*/

import java.util.*;
public class Solution {
	/***
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = pHead;
        while(temp != null){
            if(set.contains(temp)){
                return temp;
            }else{
                set.add(temp);
            }
            temp = temp.next;
        }
        return null;
    }
    
    ****/
    // 以上为第一次解法
    // ----------------
    
    
    /***
    第一步，找环中相汇点。分别用p1，p2指向链表头部，p1每次走一步，p2每次走二步，直到p1==p2找到在环中的相汇点。
	第二步，找环的入口。接上步，当p1==p2时，p2所经过节点数为2x,p1所经过节点数为x,设环中有n个节点,p2比p1多走一圈有2x=n+x;
    n=x;可以看出p1实际走了一个环的步数，再让p2指向链表头部，p1位置不变，p1,p2每次走一步直到p1==p2; 此时p1指向环的入口。
    **/
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead == null || pHead.next == null) return null;
        
        ListNode p1 = pHead, p2 = pHead;
        
        while(p2 != null || p2.next != null){
            
            p1 = p1.next; 
            p2 = p2.next.next;
            
            if(p1 == p2){//寻找环中的
                p2 = pHead;
                while(p1 != p2){
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            }
        }
        return null;
    }
    
    /** 参考解法   将走过的节点前面的指向断开 
    ListNode* EntryNodeOfLoop(ListNode* pHead)
    {
        if (!pHead->next)
            return NULL;
        ListNode* previous = pHead;
        ListNode* front = pHead ->next;
        while (front)
        {
            previous->next = NULL;
            previous = front;
            front = front->next;
        }
        return previous;
    }
    
    ***/
    
    
}







