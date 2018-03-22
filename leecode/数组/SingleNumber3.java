/*
leetcode 260. Single Number III

Given an array of numbers nums, in which exactly two elements appear only once 
and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant space complexity?

*/


class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        int flag = xor & (~(xor - 1)); // 求两个数不同的一位，并设置为1
        int[] result = new int[2];
        for (int i : nums) {
            if ((flag & i) != 0) result[0] ^= i;//两个不同数分隔开
            else result[1] ^= i;
        }
        return result;
    }
}


