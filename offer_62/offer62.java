/***

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
那么中位数就是所有数值排序之后中间两个数的平均值。

***/
public class Solution {
    private int max = 100;
    private int[] nums = new int[100];
    private int length = 0;
    public void Insert(Integer num) {
        // 扩充数组的长度
        if(length > max - 1){
            max *= 2;
            int[] des = new int[max];
            System.arraycopy(nums, 0, des, 0, nums.length);
            nums = des;
        }
        //插入排序 
        int j = length - 1;
        while(j >= 0){
            if(nums[j] > num){
                nums[j + 1] = nums[j];
                j --;
            }else{
                break;
            }
        }
        nums[j+1] = num;
        length++;
    }
    public Double GetMedian() {
        if(length == 0) return null;
        return length % 2 != 0?new Integer(nums[length / 2]).doubleValue()
                : (nums[length / 2] + nums[length / 2 - 1]) / 2.0;
    }

}