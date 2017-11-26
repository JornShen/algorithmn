/*
leetcode 80:

Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, 
with the first five elements of nums being 1, 1, 2, 2 and 3.
It doesn't matter what you leave beyond the new length.

*/

//　我的写法，统计数的个数．
public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int sum = 1;
    int tmp = nums[0], count = 1;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == tmp) {
            count++;
            if (count < 3) {
              nums[sum++] = nums[i];
            }
        } else {
            nums[sum++] = nums[i];
            tmp = nums[i];
            count = 1;
        }
    }
    return sum;
}

// 没想到写法会有如此的精简！　关键语句在于  n > nums[i-2]　
class Solution { 
    public int removeDuplicates(int[] nums) {
	    int i = 0;
	    for (int n : nums)
	        if (i < 2 || n > nums[i-2])
	            nums[i++] = n;
	    return i;
	}
}