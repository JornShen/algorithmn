/*
leetcode 264. Ugly Number II

Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, and n does not exceed 1690.

*/

// 参考部分别人的代码，　找规律
class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) return 1;
        int min2 = 2, min3 = 3, min5 = 5;
        int cur = 1, p2 = 1, p3 = 1, p5 = 1;
        int[] num = new int[n + 1];
        num[1] = 1;
        for (int i = 2; i <= n; i++) {
            cur = Math.min(num[p2] * 2, Math.min(num[p3] * 3, num[p5] * 5));
            if (cur == num[p2] * 2) p2++;
            if (cur == num[p3] * 3) p3++;
            if (cur == num[p5] * 5) p5++;
            num[i] = cur;
        }
        return num[n];
    }
}
