/*
leetcode 150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

*/

// 使用栈的写法
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String  s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                stack.push(operate(stack.pop(), stack.pop(), s));
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
    
    public int operate(int a, int b, String s) {
        switch (s) {
            case "+" : return a + b;
            case "-" : return b - a;
            case "*" : return a * b;
            case "/" : return b / a;
        }
        return 0;
    }
}

// 递归的写法, 值得学习

class Solution {

    private int curr;
    public int evalRPN(String[] tokens) {
        curr = tokens.length - 1;
        return eval(tokens);
    }
    
    private int eval(String[] tokens) {
        String token = tokens[curr--];
        char c = token.charAt(0);
        if (token.length() == 1 && isOp(c)) {
            int b = eval(tokens);
            int a = eval(tokens);
            return applyOp(c, a, b);
        } else {
            return Integer.parseInt(token);
        }
    }
    
    private boolean isOp(char c) {
        switch (c) {
            case '+': case '-': case '*': case '/': return true;
        }
        return false;
    }
    
    private int applyOp(char op, int a, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return a;
    }
}