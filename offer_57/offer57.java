

/*****
给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
****
/*
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null; //父节点指针

    TreeLinkNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
          if(pNode == null) return null; // 根节点
          if(pNode.right == null){
              
              /****  ---- 错误做法 没有理解思想
              if(pNode.next.left == pNode){
                  return pNode.next; //左边子节点
              }else{
                  
                  if(pNode.next.next.next == null){
                     //根节点
                     if(pNode.next.next.right == pNode.next){
                         return null;
                     }
                  }
                  return pNode.next.next;
                  
                  
              }
              
              **/
              // ------ 关键步骤 --------
              while(pNode.next != null){ // 沿父节点向上找 左边一定在其后 右边在其之前
                  if(pNode.next.left == pNode) return pNode.next;
                  pNode = pNode.next;
              }
              
              return null;
                 
          }else{
              
              TreeLinkNode temp = pNode.right;
              
              while(temp.left != null){
                  temp = temp.left;
              }
              return temp;
              
          }
       
        
        
        
    }
}