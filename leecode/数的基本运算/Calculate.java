/*
leetcode 224. Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ),
 the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

*/

// 我的写法, 非递归，用栈的写法
class Solution {
    public int calculate(String s) {
        Stack<Character> sign = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= '0' && c[i] <= '9') {
                // 延长计算数字
                int sum = c[i++] - '0';
                while (i < c.length && c[i] >= '0' && c[i] <= '9') {
                    sum = sum * 10 + c[i] - '0';
                    i++;
                }
                i--;
                nums.push(sum);
            } else if (c[i] == ')') {
                nums.push(calcuteSum(sign, nums));
            } else if (c[i] != ' ') {
                sign.push(c[i]);
            }
        }
        if (!sign.isEmpty()) nums.push(calcuteSum(sign, nums));
        return nums.pop();
    }
    //　出栈运算
    public int calcuteSum(Stack<Character> sign, Stack<Integer> nums) {
        Stack<Character> t1 = new Stack<>();
        Stack<Integer> t2 = new Stack<>();
        t2.add(nums.pop());
        char tmp = sign.pop();
        while (tmp != '(') {
            t1.add(tmp);
            t2.add(nums.pop());
            if (sign.isEmpty()) break;
            tmp = sign.pop();
        }
        // 计算机结果
        int a = t2.pop();
        while (!t1.isEmpty()) {
            tmp = t1.pop();
            int b = t2.pop();
            if (tmp == '+') {
                a += b;
            } else {
                a -= b;
            }
        }
        return a;
    }
    
}

// 
class Solution {
    int i = 0;　//全局变量
    public int calculate(String s) {
        int number1 = 0, number2 = 0;
        char ope = '+';
        // 实现非常巧妙
        while(i < s.length()){
            char val = s.charAt(i++);          
            if(val == '+' || val =='-'){
                number1 = compute(number1,number2,ope);
                ope = val;
                number2 = 0;             
            }
            else if(val >= '0' && val <='9'){
                number2 = number2 * 10 + val - '0';
            }
            else if(val == '('){
                number2 = calculate(s);
            }
            else if(val == ')') break;
        }
        return compute(number1,number2,ope);
    }
    
    public int compute(int lhs, int rhs, char op) {
         if (op == '+') return lhs + rhs;
         if (op == '-') return lhs - rhs;
         return 0;
    }
}
