/*
leetcode 155. Min Stack
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
*/

// 我的做法，错误的做法，　使用了 map + 双向链表， TreeMap 提供了最小的数，
// 后面发现删除的时候有问题.　

class MinStack {

        class Node {
            int val;
            Node pre;
            Node next;  
            public Node(int val) {
                this.val = val;
                pre = null;
                next = null;
            }
        }

        private Node head, tail;
        private TreeMap<Integer, Node> map;

        /** initialize your data structure here. */
        public MinStack() {
            map = new TreeMap<>();
            head = null;
            tail = null;
        }

        public void push(int x) {
            Node node = new Node(x);
            map.put(x, node);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.pre = tail;
            }
            tail = node;
        }

        public void pop() {
            if (head == null) return;
            int min = tail.val;
            tail = tail.pre;
            if (tail == null) head = null; // 最后只剩一个元素
            else {
                tail.next.pre = null;
                tail.next = null;
            }
            map.remove(min);
        }

        public int top() {
            if (tail == null) return -1;
            else return tail.val;
        }

        public int getMin() {
            if (map.size() == 0) return -1;
            int min = map.firstKey();
            /*Node removeOne = map.get(min);
            map.remove(min);
            if (removeOne == tail) pop(); // 包含只有一个元素的情况
            // 当等于头节点
            else if (removeOne == head) {
                head = head.next;
                // 将节点移除
                removeOne.next = null;
                head.pre = null;
            } else {
                removeOne.next.pre = removeOne.pre;
                removeOne.pre.next = removeOne.next;
                removeOne.next = null;
                removeOne.pre = null;
            }*/
            return min;
        }
    
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

// 使用两个栈的写法 

class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        // 第一个节点，　也就是　stack 中最下面的节点一定会入栈
        if (min.isEmpty() || (!min.isEmpty() && x <= min.peek())) {
            min.push(x);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int tmp = stack.pop();
            if (tmp == min.peek()) {
                min.pop();
            }
        }
    }

    public int top() {
        if (!stack.isEmpty()) return stack.peek();
        return -1;
    }

    public int getMin() {
        if (!min.isEmpty()) return min.peek();
        return -1;
    }
    
}

// 别人的写法, 只用一个栈来进行实现
// 对当前元素比最小的元素小的时候，之前最小元素先入栈，　更新最小元素，　在将元素入栈　
//　写法非常精妙
public class MinStack {

    /** initialize your data structure here. */
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();
        
    public void push(int x) {
        if(x <= min){
            stack.push(min);
            min = x;
        }
       stack.push(x);
    }
    
    public void pop() {
        if(stack.pop() == min) //此步已经将-3pop出去
            min = stack.pop(); //此步将-3之前的最小值-2,从stack中pop出去，并将其赋值给min
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
