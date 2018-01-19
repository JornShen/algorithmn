/*
leetcode 198. House Robber

You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping 
you from robbing each of them is that adjacent houses have security system 
connected and it will automatically contact the police 
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

*/

// 递归的做法，超时！！
class Solution {
    
    private int maxprofit = 0;

    public int rob(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
        robSearch(nums, 0, 0);
        return maxprofit;        
    }

    public void robSearch(int[] nums, int i, int sum) {
        if (i > nums.length - 1) {
            maxprofit = Math.max(maxprofit, sum);
            return;
        }
        // 抢劫
        robSearch(nums, i + 2, sum + nums[i]);
        // 不抢劫
        robSearch(nums, i + 1, sum);
    }

}

// 动态规划，　一维数组 dp 问题，　两个数组,　一个是 rob[i] 表示　到　i 屋抢劫最大值，　unrob 表示到　i 屋未抢劫的最大值

class Solution {

    public int rob(int[] nums) {
    
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] rob = new int[len]; // 没有抢劫的最大值　
        int[] unrob = new int[len]; // 抢劫的最大值
        unrob[0] = 0;
        rob[0] = nums[0];
        
        for (int i = 1; i < len; i++) {

            int a = unrob[i - 1];
            int b = i - 2 >= 0 ? rob[i-2] : 0;

            rob[i] = Math.max(a, b) + nums[i];　
            unrob[i] = Math.max(unrob[i-1], rob[i-1]);
        }
        
        return Math.max(rob[len-1], unrob[len-1]);
    }
}

// f(n) = max{f(n - 1), f(n-2) + A[n]}，　跟我想的不太一样
class Solution {
    public int rob(int[] nums) {
        int rob = 0, not_rob = 0;
        for(int i = 0; i < nums.length; i++){
            int temp = rob; // 上一轮抢劫
            rob = not_rob + nums[i];
            not_rob = Math.max(temp, not_rob);
        }
        return Math.max(rob, not_rob);
    }
}

// 理解参考自　[](http://blog.csdn.net/pistolove/article/details/47680663)