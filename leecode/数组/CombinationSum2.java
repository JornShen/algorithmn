/*
leetcode 40:

Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

*/
／／递归方法，　剪枝，　有点类似于　0, 1 背包问题.
class Solution {
	
   private  Set<List<Integer>>   all = new HashSet<>();

   public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> l = new ArrayList<>();
        Arrays.sort(candidates); // 排序
        int pos = candidates.length - 1;
        while (pos >= 0 && candidates[pos] > target) pos--;
        combinate2(candidates, target, pos, 0, l);
        return new ArrayList<>(all);
   }

   public void combinate2(int[] cand, int target, int pos, int sum, List<Integer> l) {
        if (target < sum) return;
        if (target == sum) {
            all.add(new ArrayList<>(l));
            return;
        }
        //　加尽可能多的数
        if (pos < 0) return;
        l.add(cand[pos]);
        combinate2(cand, target, pos - 1, sum + cand[pos], l);
        l.remove(l.size() - 1); // no add
        combinate2(cand, target, pos - 1, sum, l);
    }
}
