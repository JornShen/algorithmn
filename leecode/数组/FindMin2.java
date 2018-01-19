/*
leetcode 154. Find Minimum in Rotated Sorted Array II
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/

// 在原来的代码上进行修改，　将左右两边的边缘的树去除相同的数
class Solution {
    public int findMin(int[] nums) {
        
        int low = 0, high = nums.length - 1;
        
        while (low <= high) {

            // 左边缘去除相同数
            while (low < nums.length - 2
                    && low <= high
                    && nums[low] == nums[low + 1])
                low++;

            // 有边缘去除相同的数
            while (high >= 1
                    && low <= high
                    && nums[high] == nums[high - 1])
                high--;
            
            int mid = (low + high) / 2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
            
        }
        return nums[high];
    }
}

// 别人的代码
class Solution {
    public int findMin(int[] nums) {
        int l = 0,　r = nums.length -　1;
        while (l　<　r)　{
            int mid =　(l　+　r)　>>>　1;
            if (nums[mid]　> nums[r])　{
                l　=　mid　+　1;
            } else if (nums[mid]　< nums[r])　{
                r　=　mid;
            } else { // nums[mid]　==　nums[r]
                r--;
            }
        }
        return nums[l];
    }
}