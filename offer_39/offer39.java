/*
输入一棵二叉树，判断该二叉树是否是平衡二叉树。
*/
/*
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
    /*private boolean judge = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        // 递归版本 在上一题的基础上进行改进,比较左右两边是否差距大于1 (根据定义得到) 
        // judge 是全局变量
        TreeDepth(root);
        return judge;
    }
    
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = TreeDepth(root.left)+1, right = TreeDepth(root.right)+1;
        if(Math.abs(left - right) > 1) judge = false;
        return left > right ? left:right;
    }*/
   public boolean IsBalanced_Solution(TreeNode root) {
        // 非递归版本 
        boolean judge = true;
        if(root == null) return true;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode node = null;
        //广度优先遍历队树
        for(int i = 0; i < q.size(); i++){
            node = q.get(i);
            if(node.left != null){
                q.add(node.left);
            }
            if(node.right != null){
                q.add(node.right);
            }
        }
        int left,right;
        
        // 从队列尾部开始遍历,判断树的深度差值是否符合条件

        while(!q.isEmpty()){
            node = q.pollLast();
            left = node.left == null? 0 : node.left.val;
            right = node.right == null? 0 : node.right.val;
            if(Math.abs(left - right) > 1){
                judge = false;
                return judge;
            }
            left++;
            right++;
            node.val = left > right? left: right;
        }
        return judge;
    }
}