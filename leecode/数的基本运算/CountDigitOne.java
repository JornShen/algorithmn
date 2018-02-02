/*
leetcode 233. Number of Digit One
Given an integer n, count the total number of digit 1 appearing 
in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
*/

/*

分析 

31256

百位数字　>= 2 的时候，　a = 312, b = 56
那么　百位数字为 1 的情况有　 

100 ~ 199
往左边添加 数 
1100 ~ 1199
2100 ~ 2199
....
31100 ~ 31199
总共　32 * 100 个数字 也就是 (a / 10 + 1) * 100

31156

百位数字 == 1 的时候
100 ~ 199
....
31100 ~ 31156

总共 31 * 100 + 57 也就是　(a / 10) * 100 + (b + 1)


31056 
当百位数字 == 0 的时候
100　~ 199
....
30100 ~ 30199
总共　30 * 100 个数字 也就是 (a / 10) * 100

总结起来就是　 [(a+8)/10]*100+(a%10==1)?(b+1):0

*/
class Solution {
    public int countDigitOne(int n) {
        int  ones = 0;
        for (long m = 1; m <= n; m *= 10) {
            long a = n/m, b = n%m; // 划分为数字
            ones += (a + 8) / 10 * m + ((a % 10 == 1)? (b + 1) : 0);
        }
        return ones;
    }
}



