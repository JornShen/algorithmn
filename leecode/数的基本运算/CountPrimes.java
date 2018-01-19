/*

leetcode 204. Count Primes

Count the number of prime numbers less than a non-negative number, n.

*/
//　思路：　从２开始， 先去除所有的偶数（标记）, 然后向后进行遍历
//　3 没有被标记过，然后向后标记 3 的倍数, 之后向后进行　寻找　第一没有被标记过的数字，　去除的他的倍数
// 这种写法实际上是去除质数的倍数。
// 相反你将数和　小于数的开根号的质数　进行取模运算来进行判断这个数是不是质数。
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        // 厄拉多塞筛法
        boolean[] flag = new boolean[n];
        flag[2] = false;

        for (int i = 3; i < n; i++) {
            if (i % 2 == 0) flag[i] = true;
        }

        int count = 0;
        for (int i = 3; i < n; i += 2) {// 这里加 2 是因为 偶数不可能的
            if (!flag[i]) {
                for (int j = i * 2; j < n; j += i) {
                    flag[j] = true;
                }
            }
        }

        for (int i = 2; i < n; i++) if (!flag[i]) count++;
        return count;
    }
}