/*
leetcode 78:

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

*/

// 我的写法，递归的方法
class Solution {
    private Set<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length; 
        genSubSet(nums, 0, new ArrayList<>());
        return new ArrayList<>(set);
    }
    
    public void genSubSet(int[] nums, int pos, List<Integer> l) {
        set.add(new ArrayList<>(l));
        if (pos == nums.length) return;　// 注意这一句话的位置
        l.add(nums[pos]);
        genSubSet(nums, pos + 1, l);
        l.remove(l.size() - 1);
        genSubSet(nums, pos + 1, l);
    }
}

// 非递归的写法．　　方法非常巧妙
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int num : nums){
        	//每一轮都在已经有的数组的基础上添加元素 
            int size = res.size();
            for (int i = 0; i < size; i++){
                List<Integer> temp = new ArrayList<Integer>(res.get(i)); //　创建新的list的集合
                temp.add(num);
                res.add(temp);
            }
        }
        return res;
    }
}