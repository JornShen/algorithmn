/*
一个整型数组里除了两个数字之外，其他的数字都出现了两次。
请写程序找出这两个只出现一次的数字。
*/

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
import java.util.*;
public class Solution {
    
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
     /*   
        Set<Integer> set = new HashSet<>();
        int i = 0;
        for(i = 0; i < array.length; i++){

            if(!set.contains(array[i])){
                set.add(array[i]);
            }else {
                set.remove(array[it
            }
        }
        int[] num = new int[2];
        i = 0;
        for(int s:set){
            num[i++] = s;
        }
        num1[0] = num[0];
        num2[0] = num[1];

    }
    */
    // ------ 以上的算法 采用了set的hash来计算 --------
	// 参考解析 采用了异或的特性 
    
    // a xor a = 0  将所有的数异或完之后就剩下两个出现一次的数的异或
    // 找到两个数异或等于1的最低位进行区分,再次遍历用该数分成两组数,并异或,得到结果 x xor 0 = x
    // 算法很巧妙 用到异或特性
    
    if(array.length < 2) return;
    
    int allXor = 0;
    for(int i = 0; i < array.length; i++){
        allXor ^= array[i];
    }
        
    int flag = 1;
    
    //找两个数不同的最底位 异或 不同的地方 为 1
    while((allXor & flag) == 0){
        flag <<= 1;// 左移 
    }
        
    num1[0] = 0;
    num2[0] = 0;
    // 利用flag进行区分
    for(int i = 0; i < array.length; i++){
        if((array[i] & flag) != 0){
            num1[0] ^= array[i];
        }else{
            num2[0] ^= array[i];
        }
    }
   }
}