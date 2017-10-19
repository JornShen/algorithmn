/*************

对于一个数字序列，请设计一个复杂度为O(nlogn)的算法，返回该序列的最长上升子序列的长度，这里的子序列定义为这样一个序列U1，U2...，其中Ui < Ui+1，且A[Ui] < A[Ui+1]。
给定一个数字序列A及序列的长度n，请返回最长上升子序列的长度。

测试样例：
[2,1,4,3,1,5,6],7

返回：4

************/

// B[i] 长度为 i 的递增子串的最小的末尾, 采用二分查找查找比 A[i] 大的最小的数进行替换. 维护递增序列最小.
// B 的数组长度为所求的结果. 

import java.util.*;

public class AscentSequence {
    public int findLongest(int[] A, int n) {
        // write code here
        // 归并排序 在进行排序的过程中
        int[] B = new int[n + 1];
        int maxLen = 1;
        B[1] = A[0];
        for (int i = 1; i < n; i++) {
            // 二分查找, 比 A[i] 大的最小数
            int pos = findPosition(B, 1, maxLen, A[i]);
            B[pos] = A[i];
            if (pos > maxLen) maxLen = pos;
        }
        return maxLen;
    }

    public int findPosition(int[] B, int from, int to, int key) {
        if (B[to] <= key)
            return to + 1;
        while (from < to) {
            int mid = (from + to) / 2;
            if (B[mid] <= key) {
                from = mid + 1;
            } else {
                to = mid;
            }
        }
        return from;
    }
}