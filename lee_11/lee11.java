
/****
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

*****/


public class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int len = height.length;
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) { // 从左边看最大值
            leftMax[i] = leftMax[i - 1] > height[i] ? leftMax[i - 1] : height[i];
        }
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) { // 从右边看最大值
            rightMax[i] = rightMax[i + 1] > height[i] ? rightMax[i + 1] : height[i];
        }

        int result = 0;
        for (int i = 1; i < len - 1; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i]; // 取决于左右看的最小的最大值
        }
        return result;
    }
}

//-------------- 两边指针 ----------------

int trap(vector<int>& height)
{
    int left = 0, right = height.size() - 1;
    int ans = 0;
    int left_max = 0, right_max = 0;
    while (left < right) {
        if (height[left] < height[right]) { // 如果左边小于右边，那么给水肯定取决于左边的最大值
            height[left] >= left_max ? (left_max = height[left]) : ans += (left_max - height[left]);
            ++left;
        } else { // 同理右边的类似
            height[right] >= right_max ? (right_max = height[right]) : ans += (right_max - height[right]);
            --right;
        }
    }
    return ans;
}






