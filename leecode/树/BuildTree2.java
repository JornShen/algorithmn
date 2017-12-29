/*
leetcode 106: Construct Binary Tree from Inorder and Postorder Traversal

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder.length == 0) return null;
        return generateTreeNode(inorder,0, inorder.length - 1, postorder, 0,  postorder.length - 1);
    }
    // 后序找中序, 思路和之前前序中序恢复成树是差不多的
    public TreeNode generateTreeNode(int[] in, int ileft, int iright, int[] post, int pleft, int pright) {
        if (ileft > iright || pleft > pright) return null;
        TreeNode root = new TreeNode(post[pright]);
        int flag = ileft;
        for (int i = ileft; i <= iright; i++) {
            if (post[pright] == in[i]) {
                flag = i;
            }
        }
        TreeNode left = generateTreeNode(in, ileft, flag - 1, post, pleft, pleft + flag - ileft - 1);
        TreeNode right = generateTreeNode(in, flag + 1, iright, post, pleft + flag - ileft, pright - 1);
        root.left = left;
        root.right = right;
        return root;
    }
}