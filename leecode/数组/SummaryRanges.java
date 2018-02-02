/*
leetcode 228. Summary Ranges

Given a sorted integer array without duplicates, return the summary of its ranges.
Example 1:
Input: [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Example 2:
Input: [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]

*/

class Solution {
    public List<String> summaryRanges(int[] nums) {
        int left = 0, right = 0;
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1] + 1) {
                right = i;
            } else {
                if (left == right) {
                    list.add(nums[left]+"");
                } else {
                    list.add(nums[left] + "->" + nums[right]);
                }
                left = i;
                right = i;
            }
        }
        if (left == right) {
            list.add(nums[left]+"");
        } else {
            list.add(nums[left] + "->" + nums[right]);
        }
        return list;
    }
}