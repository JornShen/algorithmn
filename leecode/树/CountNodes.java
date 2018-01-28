/*

leetcode 222. Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled,
and all nodes in the last level are as far left as possible.
It can have between 1 and 2h nodes inclusive at the last level h.
*/

// 从树的深度入手
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
    
    public int countNodes(TreeNode root) {

    	// 超时做法
        /*if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;*/
        //　第二种尝试,　超时做法
        /*if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int size = 0, len = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < len; i++) {
                TreeNode tmp = queue.poll();
                size++;
                if (tmp.left != null) queue.add(tmp.left);
                else return 2 * size - 1;
                if (tmp.right != null) queue.add(tmp.right);
                else return 2 * size;
            }
            len = queue.size();
        }
        return 0;*/

        /*if (root == null) return 0;
        int ldepth = getTreeDepth(root.left);
        int rdepth = getTreeDepth(root.right);
        if (ldepth == rdepth) {
            // 加上左侧的节点，向右侧进行遍历
            return (1 << ldepth) + countNodes(root.right);
        } else {
            // 加上右侧的节点，向左侧进行遍历
            return (1 << rdepth) + countNodes(root.left);
        }*/
        if (root == null) return 0;
        int height = getTreeDepth(root.left); // 树的深度
        int sum = 0;
        while (root != null) {
            int rHeight = getTreeDepth(root.right);
            sum += (1 << rHeight);
            if (rHeight == height) {
                //　左右两侧的深度相同
                root = root.right;
            } else {
                root = root.left;
            }
            height--;
        }
        return sum;
    }
    
    public int getTreeDepth(TreeNode node) {
        if (node == null) return 0;
        int depth = 1;
        while (node.left != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }
}


