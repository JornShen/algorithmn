/*
leetcode 75:

Given an array with n objects colored red, white or blue, 
sort them so that objects of the same color are adjacent, 
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

*/

/*

三个指针：一个前指针begin，一个中指针current，一个后指针end，current指针遍历整个数组序列，当+

current指针所指元素为0时，与begin指针所指的元素交换，而后current++，begin++ ；
current指针所指元素为1时，不做任何交换（即球不动），而后current++ ；
current指针所指元素为2时，与end指针所指的元素交换，而后，current指针不动，end-- 。 

注意第三步，交换之后的 current 的值不能加1，因为它得判断交换过来的值的类型
而第一步，交换过来的值一定是　1, 所以直接加，　因为　current 是从　begin 方向的起步的

*/
class Solution {
    public void sortColors(int[] nums) {
        int curr = 0, begin = 0, end = nums.length - 1;
        while (curr <= end) {
            if (nums[curr] == 0) {
                swap(nums, curr++, begin++);
            } else if (nums[curr] == 2) {
                swap(nums, curr, end--);
            } else {
                curr++;
            }
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}


