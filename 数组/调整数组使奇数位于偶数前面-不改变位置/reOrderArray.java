/****************

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
并保证奇数和奇数，偶数和偶数之间的相对位置不变。


******************/

// 排序思想
// 或者再开一个数组, 遍历两次, 分别填充
// 或者还可以采用 归并排序 的思想解决较合适, 归并排序是稳定的. 有点麻烦
 
public class Solution {
    public void reOrderArray(int [] array) {
        // 冒泡排序, 排序算法
        boolean flag = false;
        for (int i = array.length - 1; i > 0; i--) {
            flag = false;
            for (int j = 0; j < i; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 != 0) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break; // 优化冒泡排序 
        }
    }
}



