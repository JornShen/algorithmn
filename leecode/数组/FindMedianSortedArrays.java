
/***
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

nums1 = [1, 3]
nums2 = [2]

The median is 2.0

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
**/

public class Solution {
    private int p1 = 0, p2 = 0;
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int mid = (len % 2 == 0) ? len / 2 - 1: len / 2;
        int temp = 0;
        for (int i = mid; i > 0; i--) {
            minOne(nums1, nums2);
        }
        if (len % 2 == 0) {
            int a = minOne(nums1, nums2);
            int b = minOne(nums1, nums2);
            return (a + b) / 2.0;
        } else {
            return  minOne(nums1, nums2);
        }
    }
    
    public int minOne(int[] nums1, int[] nums2) {
        if (p1 ==  nums1.length) {　//　前一个数组到达末尾了
            return nums2[p2++];
        }
        if (p2 ==  nums2.length) { //　后一个数组到达末尾了
            return nums1[p1++];
        }
        return nums1[p1] > nums2[p2] ? nums2[p2++] : nums1[p1++];
    }
}
