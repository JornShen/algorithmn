/*
leetcode 172. Factorial Trailing Zeroes

Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.
*/

//　寻找 5　的个数，　因为里面有个 while 循环，　剩下三个测例过不了
class Solution {
    public int trailingZeroes(int n) {
        int left = n % 10;
        int end = 0;
        if (left < 5) end = n / 10  * 10;
        else end = n / 10 * 10 + 5;
        int sum = 0;
        for (int i = 5; i <= end; i += 5) {
            int c = 0;
            int t = i;
            // 计算 5 的个数
            while (t % 5 == 0) {
                c++;
                t /= 5;
            }
            sum += c;
        }
        return sum;
    }
}


//　递归，从规律中总结。
class Solution {	
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}
