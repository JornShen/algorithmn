/*
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 
即输出P%1000000007 

例如 1 2 3 4 5 6 7 0

*/



public class Solution {
   	 private int count = 0;
     public int InversePairs(int [] array) {
        // 逆序对: 前面数大于后面的
        // 插入排序,加统计步骤   
        /*
        int sum = 0;
        int count = 0, j = 0, temp = 0;
        for(int i = 1; i < array.length; i++){
            count = 0;
            temp = array[i];
            for(j = i-1; j >= 0; j--){
                if(array[j] > temp){
                    array[j+1] = array[j];
                    count++;
                }else{
                    break;
                }
            }
            array[j+1] = temp;
            sum += count;
        }
        return sum%1000000007;
        */
        // --- 以上的代码过不了,算法的时间复杂度太大----- 
        /*
        **归并排序的改进** ，把数据分成前后两个数组(递归分到每个数组仅有一个数据项)，
		合并数组，合并时，出现前面的数组值array[i]大于后面数组值array[j]时；则前面
		数组array[i]~array[mid]都是大于array[j]的，count += mid+1 - i
		参考剑指Offer，但是感觉剑指Offer归并过程少了一步拷贝过程。
		还有就是测试用例输出结果比较大，对每次返回的count mod(1000000007)求余
        */ 
         
        if(array.length == 1){
            return 0;
        }else{
            Msort(array, 0, array.length - 1);
            
            return count;
        }
       
    }
    
    public void Msort(int[] num,int from,int to){
        if(from >= to){
            return;
        }else{
            int mid = (from + to)/2;
            Msort(num, from, mid);
            Msort(num, mid + 1, to);
            merge(num, from, to, mid);
        }
    }

    //归并排序,修改地方有加注释
    public void merge(int[] array,int from, int to,int mid){
        int[] temp = new int[to - from + 1];
        int k = 0,i = from, j = mid + 1;
        while(i <= mid && j <= to){
            if(array[i] < array[j]){
                temp[k++] = array[i++];
            }else{
                //array[i]~array[mid]都是大于array[j]的，count += mid+1 - i
                count += mid - i + 1;
                temp[k++] = array[j++];
                count %= 1000000007;// 需要注意这一行,加错了过不了 
            }
        }
        while(i <= mid){
            temp[k++] = array[i++];
        }
        while(j <= to){
            temp[k++] = array[j++];
        }
        k =0;
        for(int l = from; l <= to; l++){
            array[l] = temp[k++];
        }
    }
}