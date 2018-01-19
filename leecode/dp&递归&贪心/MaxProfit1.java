/*

188. Best Time to Buy and Sell Stock IV

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // 对 MLE 进行优化, 避免频繁的交易
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
            return profit;
        }

        int[] local = new int[k + 1]; // 局部最优
        int[] global = new int[k + 1]; // 全局最优
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = k; j >= 1; j--) { // 从后往前进行遍历，避免覆盖
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }

        return global[k];
    }
}

//　在原来的题目的基础上进行优化


public class Solution {
	public int maxProfit(int k, int[] prices) {
	    
	    if ( k < 1 ){
	        return 0; 
	    }
	    
	    if ( prices == null || prices.length <= 1 ){
	        return 0; 
	    }
	    
	    //fix for memory problem in frequent trades 
	    if ( k >= prices.length/2 ){
	        int profit = 0; 
	        for (int i = 1; i < prices.length; i++){
	            if (prices[i] > prices[i-1] ){
	                profit += prices[i] - prices[i-1];
	            }
	        }
	        return profit; 
	    }
	    
	    //DP for at most k trades
	    int[] buy = new int[k+1]; 
	    int[] sell = new int[k+1]; 
	    
	    for (int i = 0; i <= k; i++){
	        buy[i] = Integer.MIN_VALUE;
	        sell[i] = 0;
	    }
	    
	    for (int i = 0; i < prices.length; i++){
	        for (int j = k; j > 0; j--){
	            sell[j] = Math.max(sell[j], prices[i] + buy[j]);
	            buy[j] = Math.max(buy[j], sell[j-1] - prices[i]);
	        }
	    }
	    
	    return sell[k];
	}
}


