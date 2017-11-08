/*

leetcode 11:

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

*/

1. 两层 for 循环暴力解法

2. 　双指针，计算长度，　每次移动高度比较小的指针，因为计算的最大的容量取决于最小的高度．
	只有改变最小的高度才能弥补宽度减小的损失.

class Solution {
    public int maxAreaw(int[] height) {
       int l = 0, r = height.length - 1;
        int max = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                max = Math.max((r - l) * height[l], max);
                l++;
            } else {
                max = Math.max((r - l) * height[r], max);
                r--;
            }
        }
        return max;
    }
}
