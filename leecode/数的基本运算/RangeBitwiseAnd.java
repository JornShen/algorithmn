/*
leetcode 201. Bitwise AND of Numbers Range

Given a range [m, n] where 0 <= m <= n <= 2147483647,
 return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

*/

//　循环做法，超时，错误的做法
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int res = m;
        for (int i = m + 1; i <= n; i++) {
            res = res & i;
        }
        return res;
    }
}


// 理解错误
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
       	if (m == n) return m;
        int tmp = m & n;
        if (tmp == 0) return 0;

        //　统计位数
        int sum1 = 0, sum2 = 0;

        int t1 = m;
        while ((0x80000000 & t1) == 0) {
            sum1++;
            t1 <<= 1;
        }
        sum1 = 32 - sum1;

        t1  = n;
        while ((0x80000000 & t1) == 0) {
            sum2++;
            t1 <<= 1;
        }
        sum2 = 32 - sum2;

        if (sum1 != sum2) return 0;

        // 将 tmp　进行翻转
        int res = 0;
        boolean flag = false;
        for (int i = 0; i < 32; i++) {
            if (!flag && ((0x80000000 & tmp) != 0)) {
                while ((0x80000000 & tmp) != 0) {
                    res <<= 1;
                    res |= 1;
                    i++;
                    tmp <<= 1;
                }
                flag = true;
                i--;
                continue;
            }  else if (res != 0) {
                res <<= 1;
            }
            tmp <<= 1;
        }
        return res;
    }
}

//　正确做法之一，　两种写法，　寻找 m 和　n 相同的前缀
	
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        /*int mark = 0xffffffff;
        while ((m & mark) != (n & mark)) {
            mark <<= 1;
        }
        return n & mark;*/
        if (m == 0) return 0;
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}