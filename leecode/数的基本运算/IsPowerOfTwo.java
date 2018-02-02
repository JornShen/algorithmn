/*

Given an integer, write a function to determine if it is a power of two.

*/


class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        if (n < 0) return false;
        int tmp = 1;
        int res = n;
        while (n != 0) {
            if ((n & 1) == 0) {
                tmp <<= 1;
            } else break;
            n >>>= 1;
        }
        return tmp == res;
    }
}   
