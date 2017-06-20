public class Solution {
     public int Add(int num1,int num2) {
        
         /***
         int sum = 0, flag = 0, carry = 0, value = 0;
        while(flag < 32){// 小心此处
            int left =  num1 & 1;
            int right = num2 & 1;
            value = left ^ right ^ carry;
            if((left & right) == 1 || (left & carry) == 1 | (right & carry) == 1){
                carry = 1;
            }else{
                carry = 0;
            }
            value <<= flag;
            sum |= value;
            num1 >>= 1;
            num2 >>= 1;
            flag++;
            if((num1 | 0) == 0 && (num2 | 0) == 0){
                if(carry != 0){
                    carry <<= flag;
                    sum |= carry;
                }
                break;
            }
        }
        return sum; // 按位运算　
        // --------------以上为第一次----------
        ****/
       /***
       第一步：相加各位的值，不算进位，得到2。
		第二步：计算进位值，得到10. 如果这一步的进位值为0，那么第一步得到的值就是最终结果。

		第三步：重复上述两步，只是相加的值变成上述两步的得到的结果2和10，得到12。
		
        // 太精妙了!!!
       ***/
        while (num2 != 0) {
            int temp = num1 ^ num2;//求相加结果
            num2 = (num1 & num2)<<1;   
            num1 = temp;
        }
        return num1;
    }
}