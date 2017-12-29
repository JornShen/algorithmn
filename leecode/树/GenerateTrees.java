/*
leetcode 95:

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

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
    public List<TreeNode> generateTrees(int n) {
        // 1. 每一次都在一个范围内随机选取一个结点作为根。
        // 2. 每选取一个结点作为根，就把树切分成左右两个子树，直至该结点左右子树为空。
        //  自底向上的求解过程
        if (n < 1) return new ArrayList<>();
        return generateTrees(1, n);
    }
    
    public List<TreeNode> generateTrees(int left, int right){

        ArrayList<TreeNode> res = new ArrayList<>();

        if (left > right) {
            res.add(null); //此步骤不能省略
            return res;
        }

        for (int i = left; i <= right; i++) {

            List<TreeNode> lefts = generateTrees(left, i - 1); // 切分成左子树
            
            List<TreeNode> rights = generateTrees(i + 1, right); // 切分成右子树
            
            for (int j = 0; j < lefts.size(); j++) {
                for (int k = 0; k < rights.size(); k++) {
                	// 根据左右子树类型，分别交叉匹配
                    TreeNode root = new TreeNode(i); // 以i为中心点的树
                    root.left = lefts.get(j);
                    root.right = rights.get(k);
                    res.add(root);
                }
            }
        }
        return res;
    }
}

