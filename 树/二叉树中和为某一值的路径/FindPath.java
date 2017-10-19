/*************

输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。


************/

import java.util.ArrayList;
/***
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    
    private ArrayList<TreeNode> list = new ArrayList<>();
    
    private int des = 0;
    
    private ArrayList< ArrayList<Integer> > result = new ArrayList<>();
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if (root == null) return result;
        des = target;
        list.add(root);
        if (root.val == des) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(root.val);
            result.add(arr);
        }
        if (root.left != null) {
            list.add(root.left);
            FindTreePath(root.left, root.val);
            list.remove(list.size()-1);
        }
        if (root.right != null) {
            list.add(root.right);
            FindTreePath(root.right, root.val);
            list.remove(list.size()-1);
        }
        return result;
    }
    public void FindTreePath(TreeNode root,int sum) {
        if ((sum + root.val)  == des) {
            if (root.left == null && root.right == null) {
                ArrayList<Integer> arr = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    arr.add(list.get(i).val);
                }
                result.add(arr);
            }
        }
        if (sum + root.val > des) return; // 高于上一层，终止递归
        if (root.left != null) {
            list.add(root.left);
            FindTreePath(root.left, sum + root.val);
            list.remove(list.size()-1);
        }
        if (root.right != null) {
            list.add(root.right);
            FindTreePath(root.right, sum + root.val);
            list.remove(list.size()-1);
        }
    }
}

//　还是　不如下面的代码来得简洁　！！！	

import java.util.*;
/*******************

public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}

*******************/
public class Solution {
    // 这是我见到过最精简的代码！！！ 目前
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    
    private ArrayList<Integer> list = new ArrayList<Integer>();
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
       	if(root == null){
            return listAll;
        }
        list.add(root.val);
		target -=root.val;
        if(target == 0 && root.left == null && root.right == null){
            listAll.add(new ArrayList<Integer>(list));
        }
        FindPath(root.left,target);
        FindPath(root.right,target);
        list.remove(list.size()-1);
        return listAll;
    }
}





