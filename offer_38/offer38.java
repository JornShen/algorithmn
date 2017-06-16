/*
输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）
形成树的一条路径，最长路径的长度为树的深度。
*/

/***
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    
    public TreeNode(int val) {
        this.val = val;
    }
}
***/

import java.util.*;
public class Solution {
    public int TreeDepth(TreeNode root) {
        // 递归实现
        
        /*if(root == null){
            return 0;
        }
        int left = TreeDepth(root.left)+1, right = TreeDepth(root.right)+1;
        return left > right ? left:right;
        */
        
        // 非递归实习 队列实现
        
        int count = 0;
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null) return  0;
        q.add(root);
        q.add(new TreeNode(Integer.MAX_VALUE));//作为间隔用
        TreeNode node = null;
        while(!q.isEmpty()){
            node = q.remove();
            if(node.val == Integer.MAX_VALUE){// 上一层遍历完
                count++;
                if(!q.isEmpty()) q.add(new TreeNode(Integer.MAX_VALUE));
                continue;
            }
            if(node.left != null){
                q.add(node.left);
            }
            if(node.right != null){
                q.add(node.right);
            }
        }
        return count;   
        
    }
}