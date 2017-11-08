/*
leetcode 34:

Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

*/

／／　二分查找到该元素, 然后，　在向左右进行扩展,　查找范围
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[2]; 
		arr[0] = -1;
		arr[1] = -1;
		int left = 0, right = nums.length - 1;
		int index = -1;
		while (left <= right) {
			int mid = (left + right) / 2; 
			if (nums[mid] == target) {
				index = mid;
				break;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		if (index == -1) return arr;
		
		// find left
		left = index - 1;
		while (left >= 0 && nums[left] == target) left--;
		arr[0] = left + 1;
		
		// find right
		right = index + 1;
		while (right < nums.length && nums[right] == target) right++;
		arr[1] = right - 1; 
		return arr;
    }
}

／／　别人的解法

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if(nums　==　null　||　nums.length　==　0){
            return res;
        }
        int pos =　0;
        int low =　0;
        int high = nums.length　-　1;
        while(low<=high){
            int mid = low　+　(high　-　low)　/　2;
            pos = mid;
            if(nums[mid]　==　target){
                res[0]　=　pos;
                res[1]　=　pos;
                break;
            }else if(nums[mid]　>　target){
                high = mid　-　1;
            }else{
                low = mid　+　1;
            }
        }
        if(nums[pos]　!=　target){
            return res;
        }

        ／／　再用二分查找，　查找下限
        int newLow= 0;
        int newHigh = pos;
        while(newLow　<=　newHigh){
            int mid　= newLow　+　(newHigh　-　newLow)　/　2;
            if(target　==　nums[mid]){
                newHigh = mid　-　1;
            }else{
                newLow = mid　+　1;
            }
        }
        res[0]　=　newLow;
        ／／　再用二分查找，　查找下限
        newLow = pos;
        newHigh　=　nums.length　-　1;
        while(newLow　<=　newHigh){
            int mid = newLow　+　(newHigh　-　newLow)　/　2;
            if(target　==　nums[mid]){
                newLow = mid　+　1;
            }else{
                newHigh = mid　-　1;
            }
        }
        res[1]　=　newHigh;
        return res;
    }
}