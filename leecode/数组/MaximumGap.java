/*
leetcode 164. Maximum Gap
Given an unsorted array, find the maximum difference between 
the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers 
and fit in the 32-bit signed integer range.

*/

// 第一次的做法：　利用 归并排序,　最后归并的时候，统计最大的距离
class Solution {
    private int maxDiff = 0;
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        // 归并排序
        sort(nums, 0, nums.length - 1);
        return maxDiff;
    }

    public void sort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        sort(nums, start, mid);
        sort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    public void merge(int[] nums, int left, int mid, int right) {
        if (left >= right) return;
        int len = right - left + 1;
        int[] temp = new int[len];
        int l = left, r = mid + 1;
        int pos = 0;
        while (l <= 	mid || r <= right) {
            if (l <= mid && (r > right || nums[l] < nums[r])) {
                temp[pos++] = nums[l++];
            } else {
                temp[pos++] = nums[r++];
            }
            if (left == 0 && right == nums.length - 1 && pos != 1) 
                maxDiff = Math.max(maxDiff, temp[pos - 1] - temp[pos - 2]);
        }
        System.arraycopy(temp, 0, nums, left, len); //将结果进行复制
    }
}

// 桶排序
// 步骤: 求　最大值　和　最小值，　计算平均桶间距
// 计算桶的数目. 左闭右开的实数区间[min，max)划分为n-1个等长的子区间（桶）
// 除去max和min，剩下的n-2个数，每个数都属于其中一个桶。
// 对于同一个桶的两个数，因为桶是左闭右开的，所以他们的距离肯定是小于len的。
//　n - 2 个元素放在 n - 1 个桶中，一定会有空的桶，即两个元素的最大距离一定是大于 len 
// 所以　最大的的间距　是前一个桶的最大值和后一个桶的最小值的最大的间距
// 算法　核心是映射进行排序，　这里会有空的桶，为最大间距的计算方法提供了条件。

class Solution {

    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int n : nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }
        if (max == min) return 0;
        // 左闭右开
        int gap  = (max - min) / (nums.length - 1); // 设置 n - 1 个痛
        gap = gap == 0 ? 1 : gap;
        int len = (max - min) / gap + 1;
        int[] maxBucket = new int[len];
        int[] minBucket = new int[len];
        // 映射桶
        for (int n : nums) {
            int tmp = (n - min) / gap;
            maxBucket[tmp] = Math.max(maxBucket[tmp], n);
            if (minBucket[tmp] == 0 || minBucket[tmp] > n) minBucket[tmp] = n;
        }
        // 计算最大的值
        int maxDiff = 0;
        for (int i = 0; i < len; i++) {
            maxDiff = Math.max(maxDiff, minBucket[i] - min);
            if (maxBucket[i] != 0) min = maxBucket[i];
        }
        return maxDiff;
    }
}

// 桶排序工作的原理是将数组分到有限数量的桶子里。每个桶子再个别排序(有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序)。
// 桶排序利用函数的映射关系，减少了几乎所有的比较工作。实际上，桶排序的f(k)值的计算，
// 其作用就相当于快排中划分，已经把大量数据分割成了基本有序的数据块(桶)。然后只需要对桶中的少量数据做先进的比较排序即可。
// 桶排序的时间复杂度是 O(N+C)
// [](https://www.cnblogs.com/bywallance/p/5761269.html)


