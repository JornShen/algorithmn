/***************

用两个栈来实现一个队列，完成队列的Push和Pop操作。 
队列中的元素为int类型。

***************/


import java.util.Stack;

public class Solution {
    
    Stack<Integer> stack1 = new Stack<Integer>();
    
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        
        stack2.push(node);
        
    }
    
    public int pop() {
        
        if (!stack1.isEmpty()) {
           return stack1.pop();
        }
        
        if (stack2.isEmpty()) return 0;
        while (!stack2.isEmpty()) {
           stack1.push(stack2.pop());
        }
        
        return stack1.pop();
        
    }
}


