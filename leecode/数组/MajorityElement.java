/*
leetcode 169. Majority Element

Given an array of size n, find the majority element. 
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
*/

// 一开始的做法，　可能是和另一道题目混淆了，　借用快排的思想，寻找中位数，　中间的数一定是解
// 但是遇到大的集合就有问题.　后面根据　测试的样例，　做了改进
class Solution {
    public int majorityElement(int[] nums) {
        return partition(nums, 0, nums.length - 1, nums.length / 2);
    }
    
    public int partition(int[] nums, int start, int end, int mid) {
        if (start >= end) return nums[end];
        int from = start, to = end;
        // 此处　如果选用　start　处的数作为比较节点，遇到大的集合会超时
        int tmp = nums[(start + end) / 2];
        nums[(start + end) / 2] = nums[start];
        while (start < end) {
            while (start < end && tmp <= nums[end]) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && tmp > nums[start]) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = tmp;
        if (start == mid) return tmp;
        else if (start > mid) return partition(nums, from, start - 1, mid);
        else return partition(nums, start + 1, to, mid);
    }
    
}

// 标准的做法，　类似于打擂台的思想，　count 记录当前出现最多的数
// 当数和出现次数最多的数不同的时候，　count 减一，　当 count 等于　１　的时候 换擂主　　

class Solution {
    public int majorityElement(int[] nums) {
        int main = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == main) {
                count++;
            } else {
                if (count == 0) {
                    main = nums[i];
                    count = 1;
                } else {
                    count--;
                }
            }
        }
        return main;
    }
    
}