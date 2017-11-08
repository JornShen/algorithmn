/******

leetcode 32:

Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

******/ 

// 使用栈的方法
public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] str = s.toCharArray();
        int maxAns = 0;
        stack.push(-1); //留一个数作为基准值，　这个初始基低非常重要
        for (int i = 0; i < str.length; i++) {
        	//代码核心部分 ()()())()()()
            if (str[i] == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i); // 作为下一次计算的基底  
                } else {
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            }
        }
        return maxAns;
    }
}

/****
Dp 解决

s[i] = '('：　DP[i] = 0

找i前一个字符的最长括号串DP[i]的前一个字符　j = i-2-DP[i-1]
如果j >=0，且　s[j] = '('，　DP[i] = DP[i-1] + 2 + DP[j]
如果j　<　0，或　s[j] = ')'，　DP[i] = 0

****/

public class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') { 
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}

//以右括号为记录的标志
