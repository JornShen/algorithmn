/*

Implement pow(x, n).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100

*/
// 移位　的　乘积　来判断时候是否有
class Solution {
    public double myPow(double x, int n) {
        if (x == 1.0) return x;
        double result = 1;
        double base = x;
        long n1 = n;
        if (n1 < 0) {
            n1 = -n1;
            base = 1 / x;
        }
        while (n1 != 0) {
            if ((n1 & 1) == 1) {
                result *= base;
            }
            n1 >>= 1;
            base *= base;

        }
        return result;
    }
}
