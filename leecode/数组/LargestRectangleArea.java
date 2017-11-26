/*

leetcode 84:

Given n non-negative integers representing the histogram's bar height 
where the width of each bar is 1, find the area of largest rectangle in the histogram.

*/

// 最后一个超时了，　两层　for　循环暴力解

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            int  min = heights[i];
            for (int j = i; j < heights.length; j++) {
                min = Math.min(min, heights[j]);
                sum = Math.max(sum, min * (j - i + 1));
            }
        }
        return sum;
    }
}

// 改进：　只对合适的右边界（峰顶），往左遍历面积。

class Solution {
    public int largestRectangleArea(int[] heights) {
       	if (heights == null || heights.length == 0) return 0;
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {

            int  min = heights[i];

            if (i + 1 < heights.length && heights[i] <= heights[i + 1]) continue;

            for (int j = i; j >= 0; j--) {
                min = Math.min(min, heights[j]);
                sum = Math.max(sum, min * (i - j + 1));
            }

        }
        return sum;
    }
}

// 更高效的写法：
// 维护一个递增的栈，每次比较栈顶与当前元素。
// 如果当前元素大于栈顶元素，则入站，否则合并现有栈，直至栈顶元素小于当前元素（弹栈进行计算）。
// 结尾入站元素0，重复合并一次。

class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int[] h = Arrays.copyOf(heights, heights.length + 1);
        int i = 0;
        while (i < h.length) {
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i++);
            } else {
            	// 弹栈计算高度．
                int tmp = stack.pop();
                maxArea = Math.max(maxArea, h[tmp] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return maxArea;
    }
}