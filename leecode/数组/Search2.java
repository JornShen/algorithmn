/*
leetcode 81:

Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.

*/

// 在原来的基础上进行改进.
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) return nums[0] == target ? true : false;
        int left = 0, right = nums.length - 1;
        if (nums[left] == target || nums[right] == target) return true;
        // 去除首尾重复
        while (left < nums.length - 1 && nums[left] == nums[left + 1]) left++;
        while (right > 0 && nums[right] == nums[right - 1]) right--;

        while (left  <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // 新添加的代码, 在调试的过程中发现，发现问题出在当左右两边相同的时候，出现问题．
            if (nums[left] == nums[right]) {
                if (left < right)　left++;
                //　剔除 左右两边的相同的数字
                while (left < right && left < nums.length && nums[left] == nums[right]) left++;
            }
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] > target && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }
}


// 别人的做法 
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int low = 0, high = nums.length - 1;
        // 相差１跳出
        while (low + 1 < high) {
            int mid = low + (high - low)/2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[low] < nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid;　// 此处它没有 加　1 
                }　else {
                    low = mid;
                }
            }　else if (nums[low] > nums[mid]) {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid;
                }　else {
                    high = mid;
                }
            }　else {
                low++;
            }
        }
        return (nums[low] == target) || (nums[high] == target);
    }
}
