/***
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are+,-,*,/. Each operand may be an integer or another expression.
Some examples:
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
**/

import java.util.Stack;
public class Solution {
    public int evalRPN(String[] tokens) {
       Stack<Integer> nums = new Stack<>();
        for (String s : tokens) {
            if (s.compareTo("+") == 0) {
                nums.push(nums.pop() + nums.pop());
            } else if (s.compareTo("-") == 0) {
                int a = nums.pop();
                int b = nums.pop();
                nums.push(b - a);
            } else if (s.compareTo("*") == 0) {
                nums.push(nums.pop() * nums.pop());
            } else if (s.compareTo("/") == 0) {
                int a = nums.pop();
                int b = nums.pop();
                nums.push(b / a);
            } else {
                nums.push(Integer.parseInt(s));
            }
        }
        return nums.pop();   
    }
}

// 有趣的解法 使用异常

import java.util.Stack;
public class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0;i<tokens.length;i++){
            try{
                int num = Integer.parseInt(tokens[i]);
                stack.add(num);
            }catch (Exception e) { //------------------
                int b = stack.pop();
                int a = stack.pop();
                stack.add(get(a, b, tokens[i]));
            }
        }
        return stack.pop();
    }
    private int get(int a,int b,String operator){
        switch (operator) {
        case "+":
            return a+b;
        case "-":
            return a-b;
        case "*":
            return a*b;
        case "/":
            return a/b;
        default:
            return 0;
        }
    }
}

