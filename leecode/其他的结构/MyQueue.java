/*
leetcode 232. Implement Queue using Stacks
Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, 
peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. 
You may simulate a stack by using a list or deque (double-ended queue), 
as long as you use only standard operations of a stack.
You may assume that all operations are valid 
(for example, no pop or peek operations will be called on an empty queue).

*/


class MyQueue {

	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	/** Initialize your data structure here. */
	public MyQueue() {
	    stack1 = new Stack<>();
	    stack2 = new Stack<>();
	}

	/** Push element x to the back of queue. */
	public void push(int x) {
	    stack1.push(x);
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop() {
	    if (stack2.isEmpty()) {
	        while (!stack1.isEmpty()) stack2.push(stack1.pop());
	    }
	    return stack2.pop();
	}

	/** Get the front element. */
	public int peek() {

	    if (stack2.isEmpty()) {
	        while (!stack1.isEmpty()) stack2.push(stack1.pop()); 
	    }
	    return stack2.peek();

	}

	/** Returns whether the queue is empty. */
	public boolean empty() {
	    return  (stack1.size() + stack2.size()) == 0;
	}
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */



／／　别人的写法，代码复合性更高

class MyQueue {

    Stack<Integer> input=new Stack<>();
    Stack<Integer> output=new Stack<>();
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        input.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return output.pop();
    }

    // 此处进行了合并
    /** Get the front element. */
    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return input.empty() && output.empty();
    }
}
