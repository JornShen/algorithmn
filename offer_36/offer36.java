/*
输入两个链表，找出它们的第一个公共结点。
*/

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
import java.util.*;
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
       // n * n 
       
       /*
       Set<Integer> set = new HashSet<>();
       ListNode temp = pHead2; 
       while(temp != null){
           set.add(temp.val);
           temp = temp.next;
       }
       temp = pHead1;
       while(temp != null){
           if(set.contains(temp.val)){
               return temp;
           }else{
               temp = temp.next;
           }
       }
       return null; 
       //找第一个公共节点,从末尾开始寻找,从后往前找
	   // 以上是一种比较简答的写法
       */
       
       // --------------------------------------
       // 解法 2
       // 重点在理解 公共的节点  比如链表 1 2 3 4 5  ; 0 3 4 5 
       // 计算两个链表的长度  让长的那个链表先走之间的差值,然后一起走
        
       int len1 = linkedListLength(pHead1),len2 = linkedListLength(pHead2); 
       if(len1 > len2){
          for(int i = 0; i < len1 - len2; i++){
              pHead1 = pHead1.next; 
          } 
       }else{
          for(int i = 0; i < len2 - len1; i++){
              pHead2 = pHead2.next; 
          } 
       }
       
       while(pHead1 != pHead2){
           pHead1 = pHead1.next;
           pHead2 = pHead2.next;
       }
       
	   return pHead1;
    }
    
    public int linkedListLength(ListNode pHead){
        int count = 0;
        while(pHead != null){
            count++;
            pHead = pHead.next;
        }
        return count;
    }

}