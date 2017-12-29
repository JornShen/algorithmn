/*
leetcode 77:

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

*/

// 思想比较简单 
class Solution {
    private List<List<Integer>> list = new ArrayList<>(); // 全局变量
    //  递归来写
    public List<List<Integer>> combine(int n, int k) {
        if (k > n) {
            return list;
        }
        combination(n, 1, k, new ArrayList<>());
        return list;
    }

    public void combination(int n, int pos, int k, List<Integer> l) {
        if (n - pos + 1 < k - l.size()) return; // 优化的地方
        if (l.size() == k) {
            list.add(new ArrayList<>(l));
            return;
        }
        if (pos > n) return;
        l.add(pos);
        combination(n, pos + 1, k, l);
        l.remove(new Integer(pos));
        combination(n, pos + 1, k, l);
    }
}
