/*
leetcode 219. Contains Duplicate II

Given an array of integers and an integer k, find out whether there are two distinct indices i 
and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

*/


//　我的写法，空间没有优化
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j <= i + k; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }
}

// 别人的写法，写法比较巧妙, 控制看 set　的大小同时，直接判断是否有重复的数字。
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        
        if (k > 3000) return false;
        if (nums == null || nums.length <= 1) return false;
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) set.remove(nums[i - k]); 
        }
        return false;
    }
}

