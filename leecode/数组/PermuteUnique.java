/*
Given a collection of numbers that might contain duplicates, 
return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

class Solution {
    private Set<List<Integer>> all = new HashSet<>();　// 排除　相同的组
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            l.add(nums[i]);
        }
        permutation(l, 0);
        return new ArrayList<>(all);
    }
    public void permutation(List<Integer> l, int k) {
        if (k == l.size()) {
            all.add(new ArrayList<>(l));
        }
        for (int i = k; i < l.size(); i++) {
            // swap
            swap(l, k, i);

            permutation(l, k + 1);
            // recover
            swap(l, k, i);
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

// 别人的解法，用　boolean 标记

class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> path = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(ret,　path, nums, visited);
        return ret;
    }  
    
    void dfs(List<List<Integer>> ret, List<Integer> path, int[] nums, boolean[] visited){
        if(path.size() == nums.length){
            ret.add(new ArrayList<>(path));
        }
        for(int i = 0; i < nums.length; i++){
            if(!visited[i] && 
            	(i == 0 || nums[i - 1] != nums[i] || nums[i - 1] == nums[i] && visited[i - 1])){
                path.add(nums[i]);
                visited[i] = true;
                dfs(ret,　path, nums, visited);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }
}
