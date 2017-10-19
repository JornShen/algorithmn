/************************

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

********************/
/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
public class Solution {
    /*  public RandomListNode Clone(RandomListNode pHead)
    {
        
        if(pHead == null){
           return null;
        } 
       	RandomListNode temp = pHead.next,
        			   head = new RandomListNode(pHead.label),
        			   now = head;
        // 创建链表
        while(temp != null){
            now.next = new RandomListNode(temp.label); 
            now = now.next;
            temp = temp.next;
        }
        // 创建特殊指针指向
        temp = pHead;// temp 用来遍历原来的链表
        now = head;
        RandomListNode ori = null,cur = null;
       	while(temp != null){
            cur = head;
            ori = pHead;
            while(temp.random != ori){
               cur = cur.next;
               ori = ori.next;
            }         
            now.random = cur;
            now = now.next;
            temp = temp.next;
        }
        return head;
    }*/
    public RandomListNode Clone(RandomListNode pHead){
        // 复制链表
        // 遍历链表，确定random的值
        // 拆分旧的链表和新的链表
        if(pHead == null){
        	return null;
        }
        // 复制链表
        RandomListNode temp = pHead,newOne,head,now;
        while(temp != null){
            newOne  = new RandomListNode(temp.label); 
            newOne.next = temp.next;
            temp.next = newOne;
            temp = newOne.next;
        }
        // 遍历链表
        temp = pHead;
        while(temp != null){
            if(temp.random != null){	
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        } 
        // 拆分链表，注意链表拆分的时候，　需要注意两个链表都要拆分.
        temp = pHead;
        head = pHead.next;
        now = head;
        while(temp != null){
            temp.next = temp.next.next;
            if(now.next != null){
               now.next = now.next.next;         
            }
            temp = temp.next;
            now  = now.next;
        }	
        return head;
    }
}










