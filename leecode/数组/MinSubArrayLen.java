/*

leetcode 209. Minimum Size Subarray Sum

Given an array of n positive integers and a positive integer s,
 find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution,
 try coding another solution of which the time complexity is O(n log n).

*/

// O(N) 的写法，　常规的写法。
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null) return 0;
        int left = 0, right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right];
            if (sum >= s) {
                // 左指针向右进行移动, sum 减去相应值
                while (sum >= s && left <= right) {
                    minLen = Math.min(minLen, right - left + 1);
                    sum -= nums[left];
                    left++;
                }
            }
            right++;
        }
        if (minLen == Integer.MAX_VALUE) return 0;
        return minLen;
    }
}


// O(nlog(n))　的写法

class Solution {

	private int binarySearch(int left, int right, int sums[], int key)　{  
		//二分查找大于sums[i]+s的位置
        while(left <= right) {  
            int mid = (left　+　right)　/　2;  
            if(sums[mid] < key)   
                left = mid + 1;  
            else   
                right = mid - 1;  
        }  
        return left;  
    }

    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.size(), sums[len+1] = {0}, minLen = INT_MAX;  
        for(int i = 1; i　<= len; i++)  
            sums[i] = sums[i-1] + nums[i-1];  
        for(int i = 0; i < len; i++)　{  
            //找到从i+1位置开始的大于s的区间右边界  
            // 求大于 s[i] + s 的值，　间接去除了 sums[i] 的值
            int pos = binarySearch(i+1, len, sums, sums[i]+s);  
            if(pos == len　+　1) continue;//如果没有找到这个边界，则不更新长度  
            minLen = min(minLen, pos-i);//更新长度  
        }  
        return minLen　==　INT_MAX　?　0　:　minLen;  
    }
}


