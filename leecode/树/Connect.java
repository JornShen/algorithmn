/*
leetcode 116. Populating Next Right Pointers in Each Node

Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/

// 非递归广度优先遍历　
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        List<TreeLinkNode> l = new ArrayList<>();
        l.add(root);
        while (!l.isEmpty()) {
            List<TreeLinkNode> tmp = new ArrayList<>();
            TreeLinkNode curr = l.get(0);
            addChildNode(tmp, curr);
            for (int i = 1; i < l.size(); i++) {
                TreeLinkNode t = l.get(i);
                addChildNode(tmp, t);
                curr.next = t;
                curr = t;
            }
            curr.next = null;
            l = tmp;
        }
    }
    
    public void addChildNode(List<TreeLinkNode> list, TreeLinkNode t) {
        if (t.left != null) {
            list.add(t.left);
        }
        if (t.right != null) {
            list.add(t.right);
        }
    }
}