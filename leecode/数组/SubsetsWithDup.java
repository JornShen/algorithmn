/*
leetcode 90:

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

*/

class Solution {    
    private Set<List<Integer>> all = new HashSet<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        genSubSet(new ArrayList<Integer>(), 0, nums);
        return new ArrayList<>(all);
    }
    public void genSubSet(List<Integer> l, int pos, int[] nums) {
        all.add(new ArrayList<>(l));
        if (pos == nums.length) return;
        l.add(nums[pos]);
        genSubSet(l, pos + 1, nums);
        l.remove(l.size() - 1);
        genSubSet(l, pos + 1, nums);
    }
}