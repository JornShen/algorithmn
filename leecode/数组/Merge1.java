/*
leetcode 88:

Given two sorted integer arrays nums1 and nums2,
merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to 
hold additional elements from nums2. The number of elements initialized in nums1 and
 nums2 are m and n respectively.


*/

//　写法有点违背原来的意思

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = new int[m];
        System.arraycopy(nums1, 0, tmp, 0, m);
        int i = 0, j = 0;
        int pos = 0;
        while (i < m && j < n) {
            nums1[pos++] = tmp[i] < nums2[j] ? tmp[i++] : nums2[j++];
        }
        while (i < m) {
            nums1[pos++] = tmp[i++];
        }
        while (j < n) {
            nums1[pos++] = nums2[j++];
        }
    }
}

／／　反向遍历, 设计得非常好

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        for (; i >= 0 && j >= 0; k--){
            if(nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
        }
        while (j >= 0){
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }
}

