
/*********

求两个节点的最近的公共祖先

*********/

暴力解法

1. 二叉查找树

结合二叉查找树的特点，　比对二叉树查找的值，左右两个节点的值位于树的节点的两侧．则该节点最近的公共祖先

//copyright@eriol 2011  
//modified by July 2014  
public int query(Node t, Node u, Node v) {    
    //　保证 left是值小的节点, right 是值最大的值
    int left = u.value;    
    int right = v.value;    

    //二叉查找树内，如果左结点大于右结点，不对，交换  
    if (left > right) {    
        int temp = left;    
        left = right;    
        right = temp;    
    }    
    //　
    while (true) {    
        //如果t小于u、v，往t的右子树中查找  
        if (t.value < left) {    
            t = t.right;    

        //如果t大于u、v，往t的左子树中查找  
        } else if (t.value > right) {    
            t = t.left;    
        } else {    
            return t.value;    
        }    
    }    
}

2.　不是二叉查找树


递归解决


public TreeNode getLCA(TreeNode root, TreeNode node1, TreeNode node2) {

	if (root == null) return null;

	if (root == node1 || root == node2) return root;

	//　向下进行递归

	TreeNode left = getLCA(root.left, node1, node2);
	TreeNode right = getLCA(root.right, node1, node2);

	if (left != null && right != null) {
		return root; //　找到该节点
	} else if (left != null) {
		return left;
	} else if (right != null) {
		return right;
	} else {
		return null;
	}

}



离线算法

1. Tarjin 算法

设置u号节点的祖先为u
        若u的左子树不为空，dfs（u - 左子树）；
        若u的右子树不为空，dfs（u - 右子树）；
        访问每一条与u相关的询问u、v
            -若v已经被访问过，则输出v当前的祖先t（t即u,v的LCA）
        标记u为已经访问，将所有u的孩子包括u本身的祖先改为u的父亲



[参考博文](http://www.cnblogs.com/JVxie/p/4854719.html)
