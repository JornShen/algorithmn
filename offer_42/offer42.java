/***

输入一个递增排序的数组和一个数字S，在数组中查找两个数，
是的他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

***/ 

import java.util.*;
public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        /***
        int mid = sum / 2;
        int flag = sum % 2 == 0 ? 0 : 1;
        ArrayList<Integer> arr = new ArrayList<>();
        int minOne = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < array.length; i++){
            if(array[i] > sum){
                break;
            }
            if(array[i] <= mid){
                set.add(array[i]);
            }else{
                if(set.contains(sum - array[i])){
                    if(minOne > (sum - array[i])*array[i]){
                        minOne = (sum - array[i])*array[i];
                        arr.clear();
                        arr.add(sum - array[i]);
                        arr.add(array[i]);
                        set.remove(sum - array[i]);
                    }
                }
            }
        }
        return  arr;
        **/
        // 以上为第一次,将小于 sum / 2 放入set集合,然后大于sum / 2 判断互补的数是否在set集合中
        //----------------------
        //左右夹逼
        
        ArrayList<Integer> arr = new ArrayList<>();
       	if(array.length < 2 || array == null) return arr;
        
        int i = 0, j = array.length - 1;
        while( i < j ){
            if(array[i] + array[j] == sum){
                arr.add(array[i]);
                arr.add(array[j]);// 越往内遍历,值越大
                return arr;
            }else if(array[i] + array[j] > sum){
                j--;
            }else{
                i++;
            }
        }
        return arr;     
    }
}