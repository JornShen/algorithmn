/*
leetcode 213. House Robber II

Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself 
a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. 
That means the first house is the neighbor of the last one. Meanwhile, 
the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

*/

// 我的写法，　错误的写法
// 思路错误，　rob[i][] 表示从　i 开始抢劫
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int len = nums.length;
        int[][] rob = new int[len+2][len]; // 没有抢劫的最大值　
        int[][] unrob = new int[len+2][len]; // 抢劫的最大值
        // 从左向右进行抢劫
        unrob[0][0] = 0;
        rob[0][0] = nums[0];
        for (int i = 1; i < len; i++) {
            unrob[i][i] = 0;
            rob[i][i] = nums[i];
            for (int j = 0; j < i; j++) {
                int a = unrob[j][i - 1];
                int b = i - 2 >= j ? rob[j][i-2] : 0;
                rob[j][i] = Math.max(a, b) + nums[i];
                unrob[j][i] = Math.max(unrob[j][i-1], rob[j][i-1]);
            }
        }

        int maxOne = 0;
        for (int i = 0; i < len; i++) {
            maxOne = Math.max(maxOne, unrob[0][i] + rob[i+2][len-1]);
            maxOne = Math.max(maxOne, unrob[0][i] + unrob[i+2][len-1]);
            maxOne = Math.max(maxOne, rob[0][i] + rob[i+2][len-1]);
            maxOne = Math.max(maxOne, rob[0][i] + unrob[i+2][len-1]);
        }
        return maxOne;
    }   
}


// 多了个环的条件
// 从第一个家抢到倒数第二家，　第二家，抢到最后一家
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        // 抢第一家到 n - 1家
        dp[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], (i == 1 ? 0 : dp[i - 2]) + nums[i]);
        }
        int res1 = dp[nums.length - 2];

        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], (i == 2 ? 0 : dp[i - 2]) + nums[i]);
        }
        int res2 = dp[nums.length - 1];
        return Math.max(res1, res2);
    }   
}
