/*
leetcode 31:
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/

／／　解法一：
/*
	从这个序列中从右至左找第一个左邻小于右邻的数， 如果找不到，则所有排列求解完成，如果找得到则说明排列未完成。
	如果找到则从右边到左找到比这个数大的第一个数，　交换这两个数，　然后排序这个数之后的数
*/
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int j = 0;
                for (j = nums.length - 1;  j > (i - 1); j--) {
                    if (nums[j] > nums[i - 1]) break;
                }
                int temp = nums[i - 1];
                nums[i - 1] = nums[j];
                nums[j] = temp;
                // 排序 i 之后的数组
                Arrays.sort(nums, i, nums.length); // 从　fromIndex　到　toIndex　-　1　的元素排序
                return;
            }
        }
        // 数组反序
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return;
    }
}

／／　解法二：　在解法一的基础上，用反序代替排序 

class Solution {
    public void nextPermutation(int[] nums) {
        /*
        think about how do you get this example
        1　　2　　7　　4　　3　　1
        1　　3　　1　　2　　4　　7
        
        (1) find first decreasing value, A (from right to left)
        (2) find the first value B that greater A (from right to left)
        (3) swap A and B
        (4) reverse　　因为调换之后，A 之后的节点倒序，所以只需要反序列
        */
        for (int i = nums.length - 2; i >=0;i--) {
            if (nums[i+1] > nums[i]) {
                for (int j = nums.length-1; j>=0;j--) {
                   if (nums[j] > nums[i]) {
                       swap(nums,　i,　j);
                       reverse(nums,　i+1,　nums.length-1); // 优化的地方　
                       return;
                   } 
                }
            }
        }
        reverse(nums,0,nums.length-1);
    }
    private void swap (int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    private void reverse(int[]nums, int i, int j) {
        while (i<=j) {
            swap(nums,i,j);
            i++;
            j--;
        }
    }
}
