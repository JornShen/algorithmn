import java.util.*;

/*****
请实现一个函数按照之字形打印二叉树，
即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，
第三行按照从左到右的顺序打印，其他行以此类推。

*/


/*
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
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
		// 变量有点多----------
        // 思路：使用了一个list 存放遍历过的节点 然后分层遍历 每次都是从最后开始遍历 
        // 奇数层从左节点开始遍历，偶数层从右节点开始遍历

        //boolean flag = false;
        int count = 1, i = 0, temp = 0; // 统计每一层的节点个数
        ArrayList<ArrayList<Integer> > array = new ArrayList<>();
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        ArrayList<Integer> list = null, newList = null;
        TreeNode node = null;
        
        if(pRoot == null) return array;
        
        newList = new ArrayList<>();
        newList.add(pRoot.val);
        
        treeNodes.add(pRoot);
        int size = 0;
        while(count != 0){
            count = 0;
            array.add(newList);
            size = array.get(i++).size();
            newList = new ArrayList<>();
            for(int j = temp + size - 1; j >= temp; j--){
                node = treeNodes.get(j);
                if(i % 2 == 0){ // 奇数层
                    count += addNodeAndValue(treeNodes, newList, node.left);
                    count += addNodeAndValue(treeNodes, newList, node.right);
                }else{ //偶数层
                    count += addNodeAndValue(treeNodes, newList, node.right);
                    count += addNodeAndValue(treeNodes, newList, node.left);
                }
            }
            //flag = !flag;
            temp += size;
        }
        return array;
    }
    public int addNodeAndValue(ArrayList<TreeNode> treeNodes, ArrayList<Integer> list, TreeNode node){

        if(node != null){
            list.add(node.val);
            treeNodes.add(node);
            return 1;
        }
        return 0;
    }
    
    /***
    其他解法，使用两个栈来实现分层的遍历
    
    
    */
    
    

}