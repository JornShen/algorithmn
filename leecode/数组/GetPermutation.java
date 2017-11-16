/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

1. "123" 0,  0 * 2 + 0 * 1
2. "132" 1, 0 * 2 +  1 * 1 
3. "213" 2, 1 * 2 + 0 * 1 
4. "231"
5. "312"
6. "321"

Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

*/

// 根据规律写算法，　算法的复杂 O(N*2)  
class Solution {
    public String getPermutation(int n, int k) {
        boolean[] flag = new boolean[n + 1];　// 标记是否已经填充过
        if (n == 1) return "1";
        StringBuffer buffer = new StringBuffer();
        k--;
        int div = 1, step = n - 1;
        for (int i = 1; i <= n - 1; i++) {
            div *= i;
        }
        for (int i = 0; i < n; i++) {
            int pos = k / div;　// 从左到右确定第 i 位的数在所有还没有填充的数的位置．
            k -=  pos * div;　// 求下一个数的基数
            for (int j = 1; j <= n; j++) {
                if (!flag[j]) {
                    if (pos == 0) { // 0 表示从左到右边，　第一个没有填充的数．
                        buffer.append(j);
                        flag[j] = true;
                        break;
                    }
                    pos--;
                }
            }
            if (div != 1) {
                div /= step;
                step--;
            }
        }
        return buffer.toString();
    }
}
