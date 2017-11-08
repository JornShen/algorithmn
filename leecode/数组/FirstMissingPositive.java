/*****

leetcode 42:

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

****/

public class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        boolean[] flag = new boolean[nums.length + 2]; // 标记出现的正数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 ) continue;
            if (nums[i] <= nums.length) flag[nums[i]] = true;
        }
        int i = 0;
        for (i = 1; i <= nums.length; i++) {
            if (!flag[i]) return i;   
        }
        return i;
    }
}


