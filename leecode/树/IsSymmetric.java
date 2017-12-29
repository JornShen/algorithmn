/*

leetcode 101:

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.

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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return judgeSymmetic(root.left, root.right);
    }

    public boolean judgeSymmetic(TreeNode left, TreeNode right) {
        if (left == null) return right == null;
        if (right == null) return left == null;
        if (left.val != right.val) return false;
        return judgeSymmetic(left.left, right.right) && judgeSymmetic(left.right, right.left);
    }
}

// 对比别人的代码

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return func(root.left, root.right);
    }
    public boolean func(TreeNode t1, TreeNode t2) {
        if(t1 == null && t2 == null)
            return true;
        else if(t1 != null && t2 != null && t1.val == t2.val) {
            return func(t1.left, t2.right) && func(t1.right, t2.left);
        }
        else
            return false;
    }
}
