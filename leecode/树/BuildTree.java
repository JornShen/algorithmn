/*
leetcode 105. Construct Binary Tree from Preorder and Inorder Traversal

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

// 递归的写法
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        return generateTreeNode(preorder,0, preorder.length - 1, inorder, 0,  inorder.length - 1);
    }

    public TreeNode generateTreeNode(int[] pre, int pleft, int pright, int[] in, int ileft, int iright) {
        if (pleft > pright || ileft > iright) return null;
        TreeNode root = new TreeNode(pre[pleft]);
        int flag = ileft;
        for (int i = ileft; i <= iright; i++) { //找中间的节点对序进行切分
            if (pre[pleft] == in[i]) {
                flag = i;
                break;
            }
        }
        TreeNode left = generateTreeNode(pre, pleft + 1, pleft + flag - ileft,in, ileft, flag - 1);
        TreeNode right = generateTreeNode(pre, pleft + flag - ileft + 1, pright, in,flag + 1, iright);
        root.left = left;
        root.right = right;
        return root;
    }
}

// 别人的写法，更加高效．
class Solution {
    private int pre = 0;
    private int in = 0;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, Integer.MIN_VALUE);
    }

    // 先序找中序
    public TreeNode helper(int[] preorder, int[] inorder, int rootVal){
        if(pre >= preorder.length 
            || in >= inorder.length 
            || rootVal == inorder[in]) 
            return null;
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = helper(preorder, inorder, node.val);
        in++;
        node.right = helper(preorder, inorder, rootVal);
        return node;
    }
}