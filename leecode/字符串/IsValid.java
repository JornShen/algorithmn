/*

Given a string containing just the characters 
'(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order,
 "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/

// 简单的栈的应用
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); //  左括号 加入栈
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '[' || c[i] == '{' || c[i] == '(') {
                stack.push(c[i]);
            } else {
                if (stack.size() == 0) return false;
                char temp = stack.pop();

                if (c[i] == '}') {
                    if (temp != '{') return false;
                    continue;
                }

                if (c[i] == ')') {
                    if (temp != '(') return false;
                    continue;
                }

                if (c[i] == ']') {
                    if (temp != '[') return false;
                }
            }
        }
        if (stack.size() != 0) return false;
        return true; 
    }
}






