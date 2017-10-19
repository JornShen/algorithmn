
/******************

输入n个整数，找出其中最小的K个数。
例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,


********************/

1.　堆排序取最小的数.

import java.util.*;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        
        // 堆排序
        if(k > input.length||k==0){// 考虑特殊情况！！！
            return new ArrayList<Integer>();
        }
        
        int[] heap =  new int[k];
        
        for(int i=0; i < k; i++){
        	heap[i] = input[i];
        }
        //建堆
        for(int i=(heap.length-1)/2; i >= 0; i--){
            heapSort(heap,i,heap.length);
        }
        
        for(int i=k; i < input.length; i++){
            if(heap[0] > input[i]){	
                heap[0] = input[i];
                heapSort(heap,0,heap.length);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = k-1; i > 0; i--){
            result.add(heap[0]);
            heap[0] = heap[i];
            heapSort(heap,0,i);
        }
        result.add(heap[0]);
        return result;
       
    }
    
    public void heapSort(int[] array,int from,int end){
        // 建立最小堆,　堆顶为最小的数
        int to = 2*from,temp = array[from];
        if(from == 0) to = 1;
        while(to < end){
            // 从左右两边的子树选出最小的节点，和父节点进行比较．
            if((to+1) < end){
               if(array[to] < array[to+1]){
                  to = to+1;// find children samllest one
               } 
            }
            if(array[to] > temp){
                array[from] = array[to];
            }else{
                break;
            }   
            from = to;
            to = to*2;
        }
        array[from] = temp;
    }
}

2. 使用快排的partition的思想

利用快速排序中的获取分割（中轴）点位置函数getPartitiion。





import java.util.*;
public class Solution {
    
    
    
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        
        // 借用 partition 的写法
        
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length) return list;
        int from = 0, to = input.length - 1;
        while (k > 0) {
            
            int pos = partition(input, from, to);
            
            if (pos - from + 1> k) {
                to = pos - 1;
            } else {
                
                for (int i = from; i <= pos; i++) {
                    list.add(input[i]);
                }
                
                k -= pos - from + 1;
                from = pos + 1;
            }
            
            
        }
        
        Collections.sort(list);
        return list;
        
        
        
    }
    
    
    public int partition(int[] arr, int from, int to) {
        // O(n) 复杂复
        int temp = arr[from];
        
        while (from < to) {
            
            while (from < to && temp <= arr[to]) to--;
            arr[from] = arr[to];
            
            while (from < to && temp > arr[from]) from++;
            arr[to] = arr[from];
            
        }
        
        arr[from] = temp;
        return from;
        
    }
    
}

















