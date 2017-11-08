/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1]
*/
import java.util.*;
public class solution {
    //　复杂度比较高的题目,　两层 for 循环　进行判断
    /*public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i =  0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
        }
        return result;
    }*/
     // map 进行判断, 其中map的key 存放值, value 存放 index, 这个思想值得学习
     public int[] twoSum(int[] nums, int target) {
         int[] result = new int[2];
         Map<Integer, Integer> map = new HashMap<>();
         for (int i = 0; i < nums.length; i++) {
             int temp = target - nums[i];
             if (map.containsKey(new Integer(temp))) {// 判断另一个是否在 map 里面
                 result[0] = map.get(temp);
                 result[1] = i;
                 return result;
             } else {
                 map.put(nums[i], i);
             }
         }
         return result;
    }
}

