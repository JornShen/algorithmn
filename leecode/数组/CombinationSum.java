/*

Given a set of candidate numbers (C) (without duplicates) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]

*/


／／从排序的数组的后面进行统计
class Solution {
    private  Set<List<Integer>> all = new HashSet<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> l = new ArrayList<>();
        int pos = candidates.length - 1;
        // 优化地方，　从后往前加
        while (pos >= 0 && candidates[pos] > target) pos--;
        combinate(candidates, target, pos, 0, l);
        return new ArrayList<>(all);
    }
    public void combinate(int[] cand, int target, int pos, int sum, List<Integer> l) {
        if (target < sum) return;

        if (target == sum) {
            all.add(new ArrayList<>(l));
            return;
        }
        //　加尽可能多的数
        if (pos < 0) return;
        int count = 1;
        while (sum + count * cand[pos] <= target) {
            l.add(cand[pos]);
            count++;
        }
        count--;
        for (int i = count; i >= 0; i--) {
            combinate(cand, target, pos - 1, sum + i * cand[pos], l);
            if (i != 0) l.remove(l.size() - 1);
        }
    }
}
