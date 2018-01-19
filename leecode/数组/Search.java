/*
leetcode 33:

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

*/ 

／／　错误, 写得有点乱
public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) return -1;
    if (nums.length == 1) return nums[0] == target ? 0 : -1;
    int left = 0, right = nums.length - 1;
    while (left  <= right) {
        int mid = (left + right) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[left] > nums[right]) {
        	if (target >= nums[left] && target < nums[mid]) {
        		right = mid - 1;
        	} else {
        		left  = mid + 1;
        	}

        } else {
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
    return -1;
}　

／／改正　

class Solution {
   public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int left = 0, right = nums.length - 1;
        while (left  <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] > nums[right]) {
                //　之前错误的写法
                /*if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left  = mid + 1;
                }*/
                /*if (target > nums[mid]
                        &&  target <= nums[right]
                        && nums[mid] < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }*/
                //　判断 mid 属于哪个区域
                if (nums[mid] <= nums[right]) {
                    if (target > nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                if (nums[mid] >= nums[left]) {
                    if (target < nums[mid] && target >= nums[left]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }

            } else {
            	// 在左半边的区域, 或者右半边的区域, 
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

}

// 另一种思路 
class Solution {
   public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int left = 0, right = nums.length - 1;
        while (left  <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
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
        return -1;
    }
}
