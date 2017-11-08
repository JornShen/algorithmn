
/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Note:
The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.
*/

class Solution {
    public int reverse(int x) {
        int sum = 0;
        int temp = x > 0 ? x : -x;
        int last = 0;
        while (temp != 0) {
            last = sum;
            sum = sum * 10 + temp % 10;
            // 我的判断溢出的方法,　比较土
            if (last != (sum - temp % 10) / 10 ) return 0; // 1073741823
            temp = temp / 10;
        }
        return x > 0 ? sum : -sum;
    }
}

//　别人的做法
class Solution {
    public int reverse(int x) {
        long res = 0; //　使用　long 进行防止溢出, 同时进行判断
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) res;
    }
}

