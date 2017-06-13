import java.util.ArrayList;
import java.util.Arrays; 
import java.util.Comparator;
public class Solution {
     public String PrintMinNumber(int [] numbers) {
		/*
         * 若ab > ba 则 a > b，
 		 * 若ab < ba 则 a < b，
		 * 若ab = ba 则 a = b；
        */
         
        // 排序
        String[] nums = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            nums[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String s, String t) {
				/*
                char[] s1 = s.toCharArray();
                char[] t2 = t1.toCharArray();
                int i = 0;
                while(i < s1.length && i < t2.length){

                    if(s1[i] > t2[i]){
                        return 1;
                    }else if(s1[i] < t2[i]){
                        return -1;
                    }else{
                        i++;
                    }
                }
                if(i < s1.length){
                    return -1;
                }else if(i < t2.length){
                    return 1;
                }else{
                    return 0;
                }*/
                //前后两个拼接起来后进行比较
                String s1 = s + t;
                String t1 = t + s;
                return s1.compareTo(t1);
                
            }
        });
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < nums.length; i++){
            sb.append(nums[i]);
        }
        return sb.toString();
    }
 
}

