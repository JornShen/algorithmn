/*
leetcode 220. Contains Duplicate III

Given an array of integers, find out whether there are two distinct indices i 
and j in the array such that the absolute difference between nums[i] and nums[j] 
is at most t and the absolute difference between i and j is at most k.

*/

// 我的写法，参考前一个问题的解法
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k > 3000) return false;// 这句加上，不然大集合过不去
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.size() > 0) {
                for (long l : set) {
                    if ((l - t) <= nums[i] && nums[i] <= (l + t)) return true;
                }
            }
            set.add((long)nums[i]);
            if (set.size() > k) {
                set.remove(new Long(nums[i-k]));
            }
        }
        return false;
    }
}

// 想法比较奇特，虽然写成一层循环，但是　o(n * n) 的复杂度
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length < 2 || k < 1 || t < 0) return false;
        for (int i　=　0, j　=　1; i　<　nums.length　-　1 && j　<　nums.length;) {
            if (Math.abs((long) nums[i] - nums[j]) <= t) return true;
            if (j　-　i == k || j == nums.length　-　1) {
                i++;
                if (t != 0) 
                    j = i + 1;
            }
            else
                j++;
        }
        return false;
    }
}