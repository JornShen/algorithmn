/*
leetcode 69: 

Implement int sqrt(int x).

Compute and return the square root of x.

x is guaranteed to be a non-negative integer.


Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2

*/

// 暴力解，　需要注意　long　替换 int 进行运算

class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        long x1 = x;
        for (long i = 1; i <= x1 / 2 + 1; i++) {
            if (i * i == x) return (int)i;
            if (i * i > x) return (int) i - 1;
        }
        return 0;
    }
}

//　二分查找，　将 i * i == x, 拆分成　x / i 和　i 进行比较
class Solution {
    public int mySqrt(int x) {
        if (x == 0)　return 0;
        int left = 1, right = Integer.MAX_VALUE;
        int ans = 0;
        while (left <= right) {
            
            int mid = left + (right - left)/2;

            if (mid > x/mid) right = mid - 1;
            else {
                ans = mid;  
                left = mid + 1;
            }
        }
        return ans ;
    }
}