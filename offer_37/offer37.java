
/*
统计一个数字在排序数组中出现的次数
*/

public class Solution {
    public int GetNumberOfK(int [] array , int k) {
        
       // 最右算法 二分查找后向两边统计 数字是聚集的
       if(array.length == 0){
           return 0;
       }
       int max = array.length - 1, min = 0, mid, count = 0;
       
       if(array[max] == k){
            for(int i = max; i >= 0; i--){
                if(array[i] == k){
                    count++;  
                }else{
                    break;
                }
            }
       }else if(array[min] == k){
            for(int i = min; i < array.length; i++){
                if(array[i] == k){
                   count++;
                }else{
                    break;
                }
            }
       }else{
           while(max > min){
               mid = (max + min) / 2;
               if(array[mid] > k){
               
                    max = mid-1;//注意
               
               }else if(array[mid] < k){
                   
                    min = mid+1;//注意
               
               }else{// find -> count in both directions
                   int i = mid+1;
                   while(i < array.length){
                       if(array[i] == k){
                           count++;
                           i++;
                       }else{
                           break;
                       }
                   }
                   i = mid;
                   while(i >= 0){
                       if(array[i] == k){
                           count++;
                           i--;
                       }else{
                           break;
                       }
                   }
                   return count; // 此处直接返回或者break
               }
           }    
       }
       return count;
    }
}