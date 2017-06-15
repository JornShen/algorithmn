
/*

返回第i个 丑数(因子只有2,3,5)

*/
public class Solution {
    public int GetUglyNumber_Solution(int index) {
      
        /*
        max = 1 : 2*1 = 2、 3*1 = 3 、 5*1 = 5 ，因为 2 最小，所以第二个丑数arr[ 1 ] 是 2 .
		max = 2： 2*1 = 2 <= 2 ,  2*2 = 4、 3*1 = 3 ，5*1 = 5 ，最小的数 是 3 ,所以第三个丑数 arr[ 2 ]是 3 .
		max = 3：2*1 = 2 < 3 , 2 *2 = 4, 3*1 = 3 <= 3 , 3*2 = 6 , 5*1 = 5，最小的数是 4 ，所以第四个丑数 arr[ 3 ] 是 4.
        */
        if(index <= 0){
            return 0;
        }
        
        int temp2 = 0;
        int temp3 = 0; 
        int temp5 = 0;
       	int[] arr = new int[index];
        arr[0] = 1;
        int count = 1;
        while(count < index){
            int min =  Math.min(Math.min(arr[temp2]*2,arr[temp3]*3),arr[temp5]*5);
            arr[count] = min;
            
            while(arr[temp2]*2 <= min)
                temp2++;
            while(arr[temp3]*3 <= min)
                temp3++;
            while(arr[temp5]*5 <= min)
                temp5++;
            
            count++;
        }
        return arr[count - 1];
    }


    //判断是否是丑数, 可以暴力破解
    boolean isUglyNum(int num){
        while(num % 2 == 0){
            num /= 2; 
        }
        while(num % 3 == 0){
            num /= 3; 
        }
        while(num % 5 == 0){
            num /= 5; 
        }
        return num == 1 ? true:false;
    }
}