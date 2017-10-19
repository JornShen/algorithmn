/************




**************/

public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0) return 0;
        // 快排的partision 算法 
        partision(array, 0, array.length - 1);
		
        if (array[array.length / 2 + 1] == array[0] ||
            array[array.length / 2 - 1] == array[array.length - 1]
           ) return array[array.length / 2];
        
        return 0;
        
        
    }
    
    public int partision(int[] array, int from, int to) {
        
        int temp = array[from];
        
        while (from < to) {
            
            while (array[to] >= temp && from < to) to--;
            array[from] = array[to];
            
            while (array[from] <= temp && from < to) from++;
            array[to] = array[from];
            
        }
        array[from] = temp;
        return from;
    }   
}

// 以上的想法想错了，　解法错误

// 统计数字，当超过数量的时候直接返回

import java.util.*;
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length == 1){
            return array[0];
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        int temp = 0;
        for(int i=0; i < array.length;i++){
            if(map.get(array[i]) == null){
                map.put(array[i],1);
            }else{
                temp = map.get(array[i]);
                if(temp+1 > array.length/2){
                    return array[i];
                }
                map.put(array[i],temp+1);
            }
        }
        return 0;
    }
}


//-----------------------------------------------

采用阵地攻守的思想：
第一个数字作为第一个士兵，守阵地；count = 1；
遇到相同元素，count++;
遇到不相同元素，即为敌人，同归于尽,count--；当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
再加一次循环，记录这个士兵的个数看是否大于数组一般即可。

//-----------------------------------------------
import java.util.*;
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array == null || array.length == 0) return 0;
        int temp = array[0], count = 1;
        for (int i = 1; i < array.length; i++) {
            if (temp == array[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                temp = array[i];
                count = 1;
            }
        }
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == temp) {
                count++;
            }
        }
        return 2 * count > array.length ? temp : 0;
    }
}
