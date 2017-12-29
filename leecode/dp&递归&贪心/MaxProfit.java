/*
leetcode 123. Best Time to Buy and Sell Stock III

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).

*/




class Solution {
    public int maxProfit(int[] prices) {
    	// 第一次解法，求所有的利润的增长的最大值，然后取前两个，这样只能做到局部最优的解
        /*List<Integer> l = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                res += prices[i + 1] - prices[i];
            } else {
                if (res != 0) {
                    l.add(res);
                    res = 0;
                }
            }
        }
        if (res != 0) l.add(res);
        Collections.sort(l);
        if (l.size() == 0) return 0;
        if (l.size() == 1) return l.get(0);
        return l.get(l.size() - 1) + l.get(l.size() - 2);*/

        // local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优
        // 我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。
        // local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
        // global[i][j] = max(local[i][j], global[i - 1][j])
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int[][] global = new int[n][3];
        int[][] local = new int[n][3];
        for (int i = 1; i < n; i++){
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= 2; j++) {
                // local[i - 1][j] + diff  表示昨天没有卖，今天卖
                // global[i - 1][j - 1]　前一天并少交易一次的全局最优加上大于0的差值
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);
                // 前一天完成的所有的交易和今天完成的交易　
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[n - 1][2];
    }
}




