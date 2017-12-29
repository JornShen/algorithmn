/*
leetcode  121. Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction 
(ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.

*/

class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1 || prices.length == 0 || prices == null) return 0;
        int minLeft = prices[0];
        int maxOne = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minLeft) {
                maxOne = Math.max(prices[i] - minLeft, maxOne);
            }
            minLeft = Math.min(minLeft, prices[i]); // 当前数与左边最小的数相减
        }
        return maxOne;
    }
}


class Solution {
    public int maxProfit(int[] prices) {
        // 超时做法　　O(n ^ 2)
        int maxOne = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length;j++) {
                if (prices[j] - prices[i] > maxOne) {
                    maxOne = prices[j] - prices[i];
                }
            }
        }
        return maxOne;
    }
}

