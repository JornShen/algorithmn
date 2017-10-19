/*******************

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

********************/
// 二分查找 数组中最小的数.
// 二分查找在有序数组的定位中很重要, 
import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) {
            return 0;
        } else {
            // 二分查找 
            int left = 0;
            int right = array.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                // {3,4,5,1,2} 
                // 第一次mid 定位到 5, 比右边大,显然需要将 left 调整为 mid + 1
                // 比右边小, right 定位到 mid
                if (array[mid] > array[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return array[left];    
        }
    }
}