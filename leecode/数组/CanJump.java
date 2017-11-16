/*

leetcode 55:


Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

*/

class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 0 || nums == null) return true;
        int reach = 0;
        for (int i = 0; i <= reach && i < nums.length; i++) {
            reach = Math.max(reach, i + nums[i]);
            if (reach >= nums.length - 1) break;
        }
        if (reach < nums.length - 1) return false;
        return true;
    }
}

//  别人的做法，从后面往前走
class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length < 1)　return false;
        int last = nums.length - 1;
        for(int i = nums.length - 2; i >= 0; i--){
            if(i + nums[i] >= last)　last = i; // 设置可以到达的位置的限制
        }
        if(last <= 0)　return true;
        return false;
    }
}