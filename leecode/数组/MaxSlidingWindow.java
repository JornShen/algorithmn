/*
leetcode 239. Sliding Window Maximum

Given an array nums, there is a sliding window of size k 
which is moving from the very left of the array to the very right.
 You can only see the k numbers in the window. Each time the sliding 
 window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

*/

// 双向队列的做法
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        if (nums == null || nums.length == 0) return new int[0];
        Deque<Integer> queue = new LinkedList<>(); // 双向队列
        int[] res = new int[nums.length-k+1];

        for (int i = 0; i < k - 1; i++) {
            if (queue.size() == 0) queue.add(i);
            else {
                while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]) {
                    queue.removeLast();
                }
                queue.add(i);
            }
        }

        int index = 0;

        for (int i = k-1; i < nums.length; i++) {
        	//　判断队列头是否已经在滑动区域外
            if (!queue.isEmpty() && queue.getFirst() <= i - k) queue.removeFirst();

            // 清空从右到左比当前数小的数的　index
            while (!queue.isEmpty() && nums[queue.getLast()] < nums[i]) {
                queue.removeLast();
            }
            queue.add(i);
            res[index++] = nums[queue.getFirst()];
        }
        return res;
    }
}


// 别人的写法

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int len = nums.length;
        int left = 0, right = k - 1;
        int[] result = new int[len - k + 1];
        int max = -1;
        while (right < nums.length) {
            if (max < left) {
                max = left;
                //每当max过期过后重新，重新寻找nums[left~right]的最大者。在最坏情况下（nums降序排列），复杂度是n*k
                for (int i = left + 1; i <= right; i++) if (nums[i] >= nums[max]) max = i;
            }
            else if (nums[right] >= nums[max]) max = right;
            result[left] = nums[max];
            left++;
            right++;
        }
        return result;
    }
}