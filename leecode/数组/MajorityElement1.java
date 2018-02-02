/*
leetcode 229. Majority Element II

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
The algorithm should run in linear time and in O(1) space.
*/

class Solution {
    public List<Integer> majorityElement(int[] nums) {
    	// 我的写法，不满足空间要求
        /*Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int limit = (nums.length % 3 == 0) ? nums.length / 3 : nums.length / 3 + 1;
        if (nums.length < 3) {
             for (int i : nums) {
                 set.add(i);
             }
             return new ArrayList<>(set);
        }
        for (int i : nums) {
            if (map.containsKey(i)) {

                int k = map.get(i);
                map.put(i, k+1);
                if (k + 1 >= limit) set.add(i);
            } else {
                map.put(i, 1);
            }
        }
        return new ArrayList<>(set);*/
        // 类似于打擂台的方式
        int m = 0, n = 0, cm = 0, cn = 0;
        
        for (int i : nums) {
            if (i == m) cm++;
            else if (i == n) cn++;
            else if (cm == 0) { m = i; cm++;}
            else if (cn == 0) { n = i; cn++;}
            else {cn--; cm--;}
        }
        
        cn = 0;
        cm = 0;
        
        for (int i : nums) {
            if (i == m) cm++;
            else if (i == n) cn++;
        }
        
        List<Integer> list = new ArrayList<>();
        
        if (cn > nums.length / 3) list.add(n);
        if (cm > nums.length / 3) list.add(m);
        
        return list;
    }
}