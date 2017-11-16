/*
leetcode 46:

Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/

// 字典序列，递归实现

class Solution {
    private List<List<Integer>> all = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return all;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            l.add(nums[i]);
        }
        permutation(l, 0);
        return all;
    }
    public void permutation(List<Integer> l, int k) {
        if (k == l.size()) {
            all.add(new ArrayList<>(l));
        }
        for (int i = k; i < l.size(); i++) {
            swap(l, k, i);　　// swap
            permutation(l, k + 1);
            swap(l, k, i); 　//recover
        }
    }
    public void swap(List<Integer> l, int f, int t) {
        if (f == t) return;
        int left = l.get(f);
        int right = l.get(t);
        l.set(f, right);
        l.set(t, left);
    }
}

// 别人的做法

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        res.add(cur);
        for ( int i = 0; i < nums.length; i++ ) {
            int size = res.size();
            for ( int j = 0; j < size; j++ ) {
                List<Integer> existing = res.remove(0);
                for ( int k = 0; k <= existing.size(); k++ ) {
                    List<Integer> newList = new ArrayList<>(existing);
                    newList.add(k, nums[i]);
                    res.add( newList );
                }
            }
        }
        return res;
    }
}