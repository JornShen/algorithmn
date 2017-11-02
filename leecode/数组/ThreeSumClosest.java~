/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/

// 在原来的基础上, 单层循环 + 双指针 思路上进行改进. 
public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int closeOne = 10000000; // 设置初始值
    for (int i = 0; i < nums.length - 2; i++) { // 固定一个 数字
//            if (Math.abs(closeOne - target) < Math.abs(nums[i] - target)) break; 这处优化失败

    	// 优化
        if (nums[i] > target && Math.abs(closeOne - target) < Math.abs(nums[i] - target)) break;

        if (i != 0 && nums[i] == nums[i - 1]) continue;
        // 建立双指针
        int left = i + 1, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right] + nums[i];
            if (sum == target) {
                //  往两边扩展
                return target;
            } else if (sum > target) {
                closeOne = Math.abs(sum - target) < Math.abs(closeOne - target) ? sum : closeOne;
                do{
                    right--;
                }while(left < right && nums[left] == nums[right + 1]);

            } else {
                closeOne = Math.abs(sum - target) < Math.abs(closeOne - target) ? sum : closeOne;
                do{
                    left++;
                }while(left < right && nums[left] == nums[left - 1]);
            }
        }
    }
    return closeOne;
}


// 注意看别人的优化的地方.
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[2];
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
        	// 优化地方, 关注一些特殊情况的最值, 

        	// 最小值
            if (3 * nums[i] > target) {
            	// 到达临界区
                int temp = nums[i] + nums[i + 1] + nums[i + 2];
                return Math.abs(res - target) < temp - target ? res : temp;
            }

            // 最大值
            if (nums[i] + 2 * nums[len - 1] < target) {
            	// 先看所有加和的最大值 
                res = nums[i] + nums[len - 2] + nums[len - 1];
                continue;
            } 

            int left = i + 1, right = len - 1;

            while (left < right) {
                int temp = nums[i] + nums[left] + nums[right];
                if (temp == target) {
                    return temp;
                } else if (temp < target) {
                    res = Math.abs(res - target) < target - temp ? res : temp;
                    left++;
                } else {
                    res = Math.abs(res - target) < temp - target ? res : temp;
                    right--;
                }
            }
        }
        return res;
    }
}

