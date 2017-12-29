/*
leetcode 
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

*/
// 参考别人的算法
class Solution {
    public int candy(int[] ratings) {

        int[] candyNum = new int[ratings.length];
        Arrays.fill(candyNum, 1);
        
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) candyNum[i] = candyNum[i - 1] + 1;
        }
        
        // 从尾到头遍历数组，这回逆过来贪心, 反向进行贪心
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) 
            	// 此处是更新 i - 1 位置的值
            	candyNum[i - 1] = Math.max(candyNum[i - 1], candyNum[i] + 1); 
        }
        
        int total = 0;
        for (int i = 0; i < ratings.length; i++) {
            total += candyNum[i];
        }

        return total;
    }
}

// 原本的想法，也是想到左右两边进行遍历，但是最后合并的时候有问题, 
// 此解法错误。

class Solution {
    public int candy(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        
        left[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = left[i - 1] == 1 ? 1 : left[i - 1] - 1;
            }
        }
        right[ratings.length - 1] = 1;
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = right[i + 1] == 1 ? 1 : right[i + 1] - 1;
            }
        }

        int total = 0;
        for (int i = 0; i < ratings.length; i++) {
            if (i == 0 && i + 1 < ratings.length && ratings[i] <= ratings[i + 1]) { // 开头并且单调递增
                total += 1;
                continue;
            }
            if (i == ratings.length - 1 && i - 1 >= 0 && ratings[i] <= ratings[i - 1]) { //　结尾并且单调递减
                total += 1;
                continue;
            }
            total += left[i] > right[i] ? left[i] : right[i];
        }
        return total;
    }
}

