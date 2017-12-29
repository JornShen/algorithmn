/*

leetcode 103:

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

*/

//我的写法，　非递归的写法
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        List<TreeNode> tmp = new ArrayList<>();
        tmp.add(root);
        int i = 0;
        while (!tmp.isEmpty()) {
            List<TreeNode> pre = new ArrayList<>();
            List<Integer> l = new ArrayList<>();

            if (i % 2 == 1) {
                for (int j = tmp.size() -1; j >= 0; j--) {
                    TreeNode t = tmp.get(j);
                    l.add(t.val);
                }
            } else {
                for (TreeNode t : tmp) {
                    l.add(t.val);
                }
            }
            //　计算下一层节点
            for (TreeNode t : tmp) {
                if (t.left != null) {
                    pre.add(t.left);
                }
                if (t.right != null) {
                    pre.add(t.right);
                }
            }
            i++;
            list.add(l);
            tmp = pre;
        }
        return list;
    }
}


//　递归的写法
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        int level = 0;

        if (root != null) {
            List<Integer> listLevel = new ArrayList<>();
            listLevel.add(root.val);
            list.add(listLevel);

            zigzagLevelOrder(root, list, ++level);
        }
        
        return list;
    }

    public void zigzagLevelOrder(TreeNode root, List<List<Integer>> list, int level) {
        if (root == null) {
            return;
        }

        List<Integer> listLevel;
        if (list.size() == level) {
            listLevel = new ArrayList<>();
        } else {
            listLevel = list.get(level);            
        }

        if (level % 2 != 0) {
            if (root.left != null) {
                listLevel.add(0, root.left.val);
            }
            if (root.right != null) {
                listLevel.add(0, root.right.val);
            }
        } else {
            if (root.left != null) {
                listLevel.add(root.left.val);
            }
            if (root.right != null) {
                listLevel.add(root.right.val);
            }
        }
        if (list.size() == level && !listLevel.isEmpty()) {
            list.add(listLevel);
        }
        level++;
        zigzagLevelOrder(root.left, list, level);
        zigzagLevelOrder(root.right, list, level);
    }
}
