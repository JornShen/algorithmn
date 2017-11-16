/*
leetcode 52.java

Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

*/

class Solution {
    private int[] result;
    public int totalNQueens(int n) {
        int count = 0;
        result = new int[n + 1];
        result[1] = 0;
        int k = 1;
        // **核心代码**,　参考　张德富　的算法设计
        while (k > 0) {
            while (result[k] <= (n - 1)) { // 到 n - 1 步，相当于到了最后一格
                result[k]++;
                if (isValid(k)) {
                    if (k == n) { // 找到结果
                        count++;
                    } else {
                        // 下一层访问
                        k++;
                        result[k] = 0;
                    }
                }
            }
            k--;// 返回上一层
        }
        return count;
    }
    // 验证合法性
    public boolean isValid(int k) {
        for (int j = 1; j <= k - 1; j++) {
            // 同列或对角线
            if (Math.abs(j - k) == Math.abs(result[j] - result[k])
                    || result[j] == result[k])
                return false;
        }
        return true;
    }
}

// 别人的解法，　递归实现，　非常简洁．

class Solution {
    static int count = 0;
    public int totalNQueens(int n) {
        return recursive(0, 0, 0, 0, n);
    }
    public int recursive(int row, int col, int x1, int x2, int n){
        int lst = ((1 << n) - 1) & ~(col | x1 | x2);
        int c = 0;
        while(lst != 0){
            int point = lst & -lst;
            lst ^= point;
            if(row == n - 1) return 1;
            else c += recursive(row + 1, col ^ point, (x1 ^ point) << 1, (x2 ^ point) >> 1, n);
        }
        return c;
    }
}