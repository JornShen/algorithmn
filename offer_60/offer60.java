/***
请实现两个函数，分别用来序列化和反序列化二叉树。
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
import java.util.*;
public class Solution {
    // 编码策略  按层来编码 层之间的间隔采用 a 标示 层内元素采用 
    // b 标示 null 标示为 NULL
    String Serialize(TreeNode root) {
        StringBuffer wholeStr = new StringBuffer(), partStr = new StringBuffer();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode temp = null;
        if(root == null) return wholeStr.toString();
        queue.add(root);

        // add first celling node
        partStr.append(root.val).append('b'); // 间隔每一个节点

        int count = 1, size = 0;
        while(count != 0){
            size = count;
            count = 0;
            wholeStr.append(partStr).append('a'); // a 间隔每一层
            partStr = new StringBuffer();
            for(int i = 0; i < size; i++){
                temp = queue.remove();
                if(temp.left != null){
                    queue.add(temp.left);
                    partStr.append(temp.left.val);
                    count++;
                }else{
                    partStr.append("NULL");
                }
                partStr.append('b');
                if(temp.right != null){
                    queue.add(temp.right);
                    partStr.append(temp.right.val);
                    count++;
                }else{
                    partStr.append("NULL");
                }
                partStr.append('b');
            }
        }

        return wholeStr.toString();

    }
    TreeNode Deserialize(String str) {
        if(str.equals("")) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        String[] strings = str.split("a");
        String[] s1 = strings[0].split("b");
        TreeNode head = new TreeNode(Integer.parseInt(s1[0])), temp = null;
        queue.add(head);
        for(int i = 1; i < strings.length; i++){
            s1 = strings[i].split("b");//定义切割的标示，最好不要用 | 
            for(int j = 0; j < s1.length; j += 2){
                temp = queue.remove();
                temp.left = addNode(queue, s1[j]);
                temp.right = addNode(queue, s1[j+1]);
            }
        }
        return head;
    }

    private TreeNode addNode(Queue<TreeNode> queue, String s){
        if(!s.equals("NULL")){
            TreeNode t = new TreeNode(Integer.parseInt(s));
            queue.add(t);
            return t;
        }else{
            return null;
        }
    }


    /*****
     // 采用中序遍历，用逗号间隔 
     String Serialize(TreeNode root) {
        if(root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        Serialize2(root, sb);
        return sb.toString();
    }

    void Serialize2(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(',');
        Serialize2(root.left, sb);
        Serialize2(root.right, sb);
    }
     
    int index = -1;
     
    TreeNode Deserialize(String str) {
        if(str.length() == 0)
            return null;
        String[] strs = str.split(",");
        return Deserialize2(strs);
    }  
     
    TreeNode Deserialize2(String[] strs) {
        index++;
        if(!strs[index].equals("#")) {
            TreeNode root = new TreeNode(0);     
            root.val = Integer.parseInt(strs[index]);
            root.left = Deserialize2(strs);
            root.right = Deserialize2(strs);
            return root;
        }
        return null;
    }
    */
}