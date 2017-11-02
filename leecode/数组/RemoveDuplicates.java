/*

leetcode 26:

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

*/

pos 指针保存存放地址, for循环遍历数组
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int pos = 0, pre = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (pre != nums[i]) nums[pos++] = nums[i];
            pre = nums[i];
        }
        return pos;
    }
}
