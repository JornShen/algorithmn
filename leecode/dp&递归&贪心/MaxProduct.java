/*
leetcode 152. Maximum Product Subarray

Find the contiguous subarray within an array (containing at least one number) 
which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

*/

//　这道题目和连续的数组的和比较类似，　所不同的是需要记录最小的乘积
//　因为这道题目有 正数，负数　和　零
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        // 相乘的最大值
        int maxPro = nums[0], minPro = nums[0];
        int maxRes = maxPro;
        for (int i = 1; i < nums.length; i++) {
            int end1 = maxPro * nums[i];
            int end2 = minPro * nums[i];
            maxPro = Math.max(nums[i], Math.max(end1, end2)); // 注意此处是和 nums[i]　进行比较, 因为要求连续
            minPro = Math.min(nums[i], Math.min(end1, end2));
            maxRes = Math.max(maxRes, maxPro);
        }
        return maxRes;
    }
}

// 当不要求连续的时候
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        // 相乘的最大值
        int maxPro = nums[0], minPro = nums[0];
        int maxRes = maxPro;
        for (int i = 1; i < nums.length; i++) {
            int end1 = maxPro * nums[i];
            int end2 = minPro * nums[i];
            maxPro = Math.max(maxPro, Math.max(end1, end2));
            minPro = Math.min(minPro, Math.min(end1, end2));
            maxRes = Math.max(maxRes, maxPro);
        }
        return maxRes;
    }
}
