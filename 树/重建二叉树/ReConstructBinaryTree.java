/*******

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
则重建二叉树并返回。

*******/


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        
        return recursiveTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        
    }
    
    
    public TreeNode recursiveTree(int[] pre, int startPre, int endPre, 
                                  int[] in, int startIn, int endIn) {
        if(startPre > endPre || startIn > endIn)   return null;
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (pre[startPre] == in[i]) {
                // 切分 左右子树,向下递归
                // 比如 前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
                // 找到 1 (相当于中序遍历的支点)后, 前序遍历 2,4,7 和 4,7,2 向下递归
                root.left = recursiveTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = recursiveTree(pre, startPre + i - startIn + 1, endPre, in, i + 1, endIn);
            }
            
        }
        return root;
    }
}








