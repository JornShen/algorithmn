/*
leetcode 191. Number of 1 Bits
Write a function that takes an unsigned integer and 
returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 
00000000000000000000000000001011, so the function should return 3.

*/

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        if (n == 0) return 0;
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) cnt++; // 这里没必要用 if 语句，　改成　cnt += n & 1;
            n >>>= 1;
        }
        return cnt;
    }
}

／／　>>　和 >>>　的区别
／／　>>：带符号右移。正数右移高位补0，负数右移高位补1
／／　>>>：无符号右移。无论是正数还是负数，高位通通补0。