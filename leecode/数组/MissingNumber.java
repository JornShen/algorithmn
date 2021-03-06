/*
leetcode 268. Missing Number
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, 
find the one that is missing from the array.

Example 1

Input: [3,0,1]
Output: 2
Example 2

Input: [9,6,4,2,3,5,7,0,1]
Output: 8

Note:
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant extra space complexity?

*/

//　我的写法，比较正规
class Solution {
    public int missingNumber(int[] nums) {
        
        boolean[] flag = new boolean[nums.length];
        for (int i : nums) {
            if (i > nums.length - 1) continue;
            flag[i] = true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!flag[i]) return i;
        }
        return nums.length;
    }
}



// 别人的写法，　没注意题干的要求
class Solution {
    public int missingNumber(int[] nums) {
        int sum=0;
        for(int i=0;i<=nums.length;i++){
            sum=sum+i;
        }
        for(int i=0;i<nums.length;i++){
            sum=sum-nums[i];
        }
        return sum;
    }
}