/*
leetcode 238. Product of Array Except Self

Given an array of n integers where n > 1, nums, return an array output 
such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? 
(Note: The output array does not count as extra space for the purpose 
of space complexity analysis.)

*/


// 我的写法，空间用得过多了
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int[] left = new int[nums.length]; // 从左向右进行乘法
        int[] right = new int[nums.length]; //　从右向左进行乘法

        left[0] = nums[0];
        right[nums.length-1] = nums[nums.length-1];

        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i];
            right[nums.length-i-1] = nums[nums.length-i-1] * right[nums.length-i];
        }
        
        int l = 1, r = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i - 1 < 0) res[i] = right[i+1];
            else if (i + 1 == nums.length) res[i] = left[i-1];
            else res[i] = left[i-1] * right[i+1];
        }
        
        return res;
    }
}


// 别人的写法

class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null)
        {
            return new int[0];
        }
        int length = nums.length;
        if (length == 0)
        {
            return new int[0];
        }
        //　仅仅用 结果的数组就足够了
        int[] result = new int[length];
        int temp = 1;
        for(int i = 0; i <length; i++)
        {
            result[i] = temp;
            temp *= nums[i];
        }
        
        temp = 1;
        for(int i = length - 1; i >= 0; i--)
        {
            result[i] *= temp;
            temp *= nums[i];
        }
        return result;
    }
}



