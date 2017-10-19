
/**********

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。


*********/

思路：

中界点是数组的末尾节点

1.　从左到右寻找中界点的位置,　记录位置

2.　遍历中界点到末尾节点，看是否都大于中界点

3.　向下递归遍历,　分别遍历左右两边的子树．

public boolean VerifySquenceOfBST(int [] sequence) {
    if(sequence.length == 1){ 
   		return true;
    }else if(sequence.length == 0){
        return false;
    }
    return isBST(sequence,0,sequence.length-1);
}
    
public boolean isBST(int[] s, int start,int end){ 
	
    if(start >= end){
        return true;
    }
    // 最后一个为子树的根节点
    int cmp = s[end]; 
    int i;　// 中间节点的位置
    // 寻找左右子树的交界点 以便向下递归
    for(i = start; i < end; i++){ 
    	if(s[i] > cmp){
            break;
        }
    }
    // 判断是否满足要求
    for(int j = i; j < end; j++){
        if(s[j] < cmp){
            return false;
        }
    }
    // 向下递归
    return isBST(s,start,i-1) && isBST(s,i,end-1);
}



//非递归的写法.

public boolean VerifySquenceOfBST(int [] sequence) {
    if(sequence.length == 1){ 
        return true;
    }else if(sequence.length == 0){
        return false;
    }
    Range temp;
    int start,end,cmp,i;
    Queue<Range> q = new LinkedList<>(); 
    q.add(new Range(0,sequence.length-1));
    while(!q.isEmpty()){
        temp = q.remove();
        start = temp.start;
        end = temp.end;
        cmp = sequence[end]; 
        //****************　中间的递归判断 *********************
        // 寻找左右子树的交界点 以便向下递归
        for(i=start; i < end; i++){ 
            if(sequence[i] > cmp){
                break;
            }
        }
        // 判断是否满足要求
        for(int j = i; j < end; j++){
            if(sequence[j] < cmp){
                return false;
            } 
        }
        //************************************************
        if(start < i-1){
            q.add(new Range(start,i-1));
        }
        if(i < end-1){
            q.add(new Range(i,end-1));
        }
    }
    return true; 
}

class Range{
    
    public int start; 
    public int end;
    public Range(int s,int e){
        start = s;
        end  = e;
    }
    
}