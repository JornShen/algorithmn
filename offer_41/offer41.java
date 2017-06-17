
/***

输出所有和为S的连续正数序列。序列内按照从小至大的顺序，
序列间按照开始数字从小到大的顺序

*/

import java.util.ArrayList;
public class Solution {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) { 
        
         // 参照如下例子:
         // 100    40 * 5  200  20 * 10    
         //25 * 8:  5 6 7 8 9 10 11 12 13 14 15
         // 40 * 5  18,19,20,21,22
         // 20 * 10 无法成功
        ArrayList<ArrayList<Integer>> array = new ArrayList<>();
        // 找规律
        if(sum < 3) return array;
        int num,value;
        for(int i = 2; i < Math.round(Math.sqrt(2*sum))+1; i++){ //此处需要用 sum 的两倍
            if((2*sum) % i == 0){
                value = 2 * sum / i; // value 表示值
                num = i;
                if(value > num){
                    if(num % 2 != value % 2){ // 满足条件
                        int from = value / 2 - num / 2 + (num % 2 == 0 ? 1 : 0);
                        ArrayList<Integer> a = new ArrayList<>();
                        for(int k = 0; k < num; k++){
                            a.add(from++);
                        }
                        array.add(0,a);
                    }
                }
            }
        }
        return array;
    }
}