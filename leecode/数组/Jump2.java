/******

leetcode 45:

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.

********/

//------------   超时做法 递归的解法 -----------------------

private int minLen = Integer.MAX_VALUE;
public int jump(int[] nums) {
    if (nums.length == 1) return 0;
    everyStep(nums, 0, 0);
    return minLen;
}

public void everyStep(int[] nums, int k, int step) {
    if (nums[k] == 0) return;
    for (int i = nums[k]; i >= 1 ; i--) {
        if (k + i >= nums.length - 1) {
            minLen = minLen > (step + 1) ? (step + 1) : minLen;
            return;
        }
        everyStep(nums, k + i, step + 1);
    }
}

//  ------------------- 解法二 复杂度过高 无法通过最后一个测试例子----------------------

public class Solution {  
    public int jump(int[] nums) {
        int len = nums.length;
        int[] step = new int[len];
        if (len == 1) return 0;
        for (int i = 0; i < len - 1; i++) {
            int nowStep = step[i];
            if (nums[i] == 0) continue;
            if (i + nums[i] >= len - 1)  return nowStep + 1;
            for (int j = 1; j <= nums[i] && (i + j) < len; j++) {
                int k = i + j;
                if (step[k] == 0) {
                    step[k] = nowStep + 1;
                } else {
                    step[k] = Math.min((nowStep + 1), step[k]);
                }
            }
        }
        return step[len - 1];
    }
}

// 经典的解法

public class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1 || nums == null || nums.length == 0) return 0;
        int reach = 0; 
        int lastReach = 0; // 每一步的最大的reach， 每走一步最大的reach
        int step = 0;
        for (int i = 0; i <= reach && i < nums.length; i++) {
            // when last jump can not reach current i, increase the step by 1
            // 增加步数　
            if (i > lastReach) {
                step++;
                lastReach = reach;// 更新每一步的最大reach
                if (lastReach >= nums.length - 1) return step; 
            }
            reach = Math.max(reach, i + nums[i]);
            
        }
        // can not reach 
        if (reach < nums.length - 1) return 0;
        return step;
    }
}
