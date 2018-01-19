/*
leetcode 190. Reverse Bits
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary 
as 00000010100101000001111010011100), return 964176192 
(represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

*/

// 解法太废话了
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
    	
        boolean[] flag = new boolean[32];
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                flag[i] = true;
            }
            n >>= 1;
        }

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            if (flag[i]) {
                res |= 1;
            }
        }
        return res;
    }
}

// 事实上只需要这样写就行
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ret = 0;
        for(int i = 0;i<32;i++){
            ret = (ret << 1) | (1 & n);
            n = n >>> 1; 
        }
        return ret;
    }
}

