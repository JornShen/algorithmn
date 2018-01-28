/*
leetcode 215. Kth Largest Element in an Array
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order,
not the kth distinct element.
For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 借助 快排的 partition 思想, 进行排除
        return partition(nums, 0, nums.length - 1, k);
    }

    public int  partition(int[] nums, int start, int end, int k) {
        int tmp = nums[start];
        int l = start, r = end;
        while (l < r) {
            while (l < r && nums[r] < tmp) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] >= tmp) l++;
            nums[r] = nums[l];
        }
        nums[l] = tmp;
        int len = l - start + 1;
        if (len == k) return tmp;
        else if (len > k) return partition(nums, start, l-1, k);
        else return partition(nums, l+1, end, k - len);
    }
}



