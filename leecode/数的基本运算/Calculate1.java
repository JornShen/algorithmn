/*
leetcode 227. Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.


*/
// 我的写法，上一题的改造
class Solution {
    public int calculate(String s) {
        char[] c = s.toCharArray();
        Stack<Character> sign = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= '0' && c[i] <= '9') {
                int t = c[i++] - '0';
                while (i < c.length && c[i] >= '0' && c[i] <= '9') {
                    t = t * 10 + (c[i] - '0');
                    i++;
                }
                i--;
                if (!sign.isEmpty()) {
                    char tmp = sign.peek();
                    if (tmp == '*') {
                        sign.pop();
                        t = t * nums.pop();
                    }
                    if (tmp == '/') {
                        sign.pop();
                        t = nums.pop() / t;
                    }
                }
                nums.add(t);
            } else if (c[i] != ' ') {
                sign.push(c[i]);
            }
        }

        if (!sign.isEmpty())  return calcuteSum(sign, nums); // 计算结果
        return nums.pop();
    }
    
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

// 别人的写法，比较简洁, 写法非常精妙
class Solution {
    public int calculate(String s) {
        int sum=0;
        int val=0;
        int prev=0;
        char op='+';
        for(int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);
            if(c=='-'||c=='+'||c=='*'||c=='/')
            {
                if(op=='-')
                {
                    sum-=val;
                    prev=-val;
                }
                else if(op=='+')
                {
                    sum+=val;
                    prev=val;
                }
                else if(op=='*')
                {
                    sum-=prev;
                    sum+=prev*val;
                    prev=prev*val;
                }
                else
                {
                    sum-=prev;
                    sum+=prev/val;
                    prev=prev/val;
                }
                
                op=c;
                val=0;
            }
            else if(c!=' ')
            {
                val=val*10+(c-'0');
            }
        }
        
        if(op=='-')
        {
            sum-=val;
            prev=-val;
        }
        else if(op=='+')
        {
            sum+=val;
            prev=val;
        }
        else if(op=='*')
        {
            sum-=prev;
            sum+=prev*val;
            prev=prev*val;
        }
        else if(op=='/')
        {
            sum-=prev;
            sum+=prev/val;
            prev=prev/val;
        }
        return sum;
    }
}