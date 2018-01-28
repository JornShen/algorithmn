/*
leetcode 216. Combination Sum III

Find all possible combinations of k numbers that add up to a number n,
given that only numbers from 1 to 9 can be used 
and each combination should be a unique set of numbers.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
Credits:
Special thanks to @mithmatt for adding this problem and creating all test cases.

*/

// 递归的写法
class Solution {
    private Set<List<Integer>> allSum = new HashSet<>();
    private int limit;

    public List<List<Integer>> combinationSum3(int k, int n) {
        // 用递归的方法
        limit = Math.min(n, 10);
        combinate3(k, n, 0, 1, new ArrayList<>());
        return new ArrayList<>(allSum);
    }

    public void combinate3(int k, int target, int sum, int pos, List<Integer> l) {

        if (target < sum || pos > limit) {
            return;
        }

        if (target == sum && k == 0) {
            List<Integer> tmp = new ArrayList<>(l);
            allSum.add(tmp);
        }
        if (k == 0) return;
        // 添加这个数
        l.add(pos);
        combinate3(k-1, target, sum+pos, pos+1, l);
        l.remove(l.size()-1);
        combinate3(k, target, sum, pos+1, l);
    }
}


// 别人的写法

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList();
        backTrack(res, new ArrayList<Integer>(), k, n, 1);
        return res;
    }
    
    private void backTrack(List<List<Integer>> list, List<Integer> tempList, int k, int remain, int begin) {
        if(k == 0 && remain == 0) list.add(new ArrayList(tempList));
        else if(k > 0 && remain > 0) {
            for(int i = begin; i <= 9; i++) {
                tempList.add(i);
                backTrack(list, tempList, k-1, remain-i, i+1);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}
