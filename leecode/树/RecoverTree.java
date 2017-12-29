/*
leetcode 99:

Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

*/

//　我的解法，非递归中序遍历，找出有问题的节点，就是 左边的值大于右边的值　
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
    public void recoverTree(TreeNode root) {
        //中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null, left = null, right = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre == null) {
                pre = root;
            } else {
            	// 记录第一个节点
                if (left == null && root.val < pre.val) {
                    left = pre;
                    right = root;
                }
                //　更改第二个节点
                if (right != null && root.val < pre.val) {
                    right = root;
                }
                pre = root;
            }
            root = root.right;
        }
        // 将节点的值进行调换
        int tmp = left.val;
        left.val = right.val;
        right.val = tmp;
    }
}

// 别人的写法
class Solution {
    
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        inOrderTraversal(root);
        if (firstElement != null && secondElement != null) {
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }
    
    private void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        // 第一个节点 
        if (firstElement == null && (prev == null || prev.val >= root.val)) {
            firstElement = prev;
        }
        // 第二个节点, 向右找下降的节点．
        if (firstElement != null && prev.val >= root.val) {
            secondElement = root;
        }
        prev = root;
        inOrderTraversal(root.right);
    }
}