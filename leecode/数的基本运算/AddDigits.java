/*
leetcode 258. Add Digits
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

*/

class Solution {
    public int addDigits(int num) {
        while (num > 9) {
            
            int sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        
        return num;
    }
}

// 非递归，经验写法
public class Solution {  
    public int addDigits(int num) {  
        if (num == 0) return 0;  
        return (num-1)%9+1;  
    }  
}