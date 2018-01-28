/*
leetcode 217. Contains Duplicate

Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array,
and it should return false if every element is distinct.

*/

// 查找是否有重复的数字, 比较简单粗暴,  set　集合映射

class Solution {
    public boolean containsDuplicate(int[] nums) {
        
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) return true;
            set.add(i);
        }
        return false;
    }
}

//　别人的写法，自己做映射
class Solution {
    public boolean containsDuplicate(int[] nums) {
        
        if(nums == null || nums.length <= 1 ){
            return false;
        }
        
        int min = nums[0]; 
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < min){
                min = nums[i];
            }else if(nums[i] > max){
                max = nums[i];
            }
        }
        
        if((max - min + 1 ) < nums.length){
            return true;
        }

        boolean[] results = new boolean[max - min + 1];// 设置映射的数组
        for(int i = 0; i < nums.length; i++){
            int index = nums[i] - min;
            if(results[index]){
                return true;
            }
            results[index] = true;
        }
        
        return false;
    }
}
