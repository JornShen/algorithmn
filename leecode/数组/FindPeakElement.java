/*
leetcode 162. Find Peak Element

A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element 
and your function should return the index number 2.

click to show spoilers.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.

*/


// 我的写法，比较简单，O(n) 的时间复杂度
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1 || nums[0] > nums[1]) return 0;
        for (int i = 1; i < nums.length - 1; i++) {

            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
                return i;

        }
        return nums.length - 1;
    }
}


// 二分查找
public class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;　// mid 在下坡的位置，　右边为mid,  有可能是最大的值
            else
                l = mid + 1;
        }
        return l;
    }
}