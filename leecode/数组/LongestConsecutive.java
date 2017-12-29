/*
leetcode  128. Longest Consecutive Sequence
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

*/
//　第一种做法, 数组　hash　的做法
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxLen = 0;
        boolean[] pFlag = new boolean[Integer.MAX_VALUE];
        boolean[] nFlag = new boolean[Integer.MAX_VALUE];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >=  0) pFlag[nums[i]] = true;
            else nFlag[-nums[i]] = true;
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        boolean[] tmp = null;
        int sum = 0;
        for (int i = min; i <= max; i++) {
            tmp = i >= 0 ? pFlag : nFlag;
            int pos = i >= 0 ? i : -i;
            if (tmp[pos]) {
                sum++;
            } else {
                maxLen = Math.max(sum, maxLen);
                sum = 0;
            }
        }
        return maxLen;
    }
}

// O(nlog(n))　的做法，无法满足要求
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        Arrays.sort(nums);
        int sum = 1;
        int maxLen = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;
            if (nums[i] == nums[i - 1] + 1) {
                sum++;
            } else {
                maxLen = Math.max(sum, maxLen);
                sum = 1;
            }
        }
        maxLen = Math.max(sum, maxLen);
        return maxLen;
    }
}

// 比较正确的做法，　用一个 set 集合去除重复，然后遍历元素
// 向左或向右进行扩张，统计连续的长度

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        Set<Integer> rmSet = new HashSet<>();
        int maxLen = 0;
        for (int t : set) {
            if (!rmSet.contains(t)) {
                int sum = 1;
                // 向左进行扩张
                int pos = t + 1;
                while (set.contains(pos)) {
                    sum++;
                    rmSet.add(pos);
                    pos++;
                }
                //　向左进行扩张
                pos = t - 1;
                while (set.contains(pos)) {
                    sum++;
                    rmSet.add(pos);
                    pos--;
                }
                maxLen = Math.max(maxLen, sum);
            }
        }
        return maxLen;
    }
}