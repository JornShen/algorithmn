
/*************

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
要求不能创建任何新的结点，只能调整树中结点指针的指向。

*************/

/******
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/

import java.util.*;
public class Solution {
    
    
    public TreeNode Convert(TreeNode pRootOfTree) {
        
       /* Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = pRootOfTree;
        TreeNode head = null, pre = null;
		//　代码有问题 == 
        while (!stack.isEmpty()) {
            
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            
            temp = stack.pop();
            // 处理
            
            if (head == null) {
                head = temp;
            }
            
            if (pre == null) {
                pre = temp;
            } else {
                pre.right = temp;
                temp.left = pre;
                pre = temp;
            }
            temp = temp.right;
        }
        
        return head;*/
        // 中序遍历
        if (pRootOfTree == null) {
          	return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = pRootOfTree,　pre　=　null,　head　=　null;
        while (true) {    
        	//　中序遍历，　非递归做法   
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            temp = stack.pop();//pop的顺序就是中序遍历
            // 排序处理
            if(pre == null) {
               head = temp;
               pre = temp;
            } else { 
               pre.right = temp;
               temp.left = pre;
               pre = temp;
            }

            temp = temp.right;
        }
        return head;
    }
}
