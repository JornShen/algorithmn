/*

leetcode 136. Single Number

Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

*/
//　最简洁的做法，可以采用　set　集合来做，或者其他标志的做法，控制复杂度在 O(n)　的情况下
class Solution {
    public int singleNumber(int[] nums) {
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            tmp ^= nums[i];
        }
        return tmp;
    }
}
