/*************************

求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
可以很快的求出任意非负整数区间中1出现的次数。

*****************************/

public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        // 分拆成 前面部分+中间位+后面部分，或front+mid+back
        /*
        	当mid > 1时，1出现的次数为10^(back的位数) * (front+1) ；
			当mid == 1时，1出现的次数为10^(back的位数)* front + (back + 1) ；	
			当mid == 0时，1出现的次数为10^(back的位数) * front ；
        */
       int front = n,m = 0 ,mid = 0, back = 0;
       int count = 10 , sum = 0;
       while(front != 0){
           front = n / count; 	
           m = n % count;
           mid = m / (count / 10) ;
           back = m % (count / 10);
           if(mid ==0){
              sum += (count / 10) * front;
           }else if(mid == 1){
              sum += (count / 10) * front + back + 1;
           }else{
              sum += (count / 10) * (front + 1);
           }
           count = count * 10;
       }
       return sum; 
    }
}











