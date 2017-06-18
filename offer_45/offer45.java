/***
LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,
个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,
看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！
“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。
为了方便起见,你可以认为大小王是0。
**/
import java.util.*;
public class Solution {
    public boolean isContinuous(int [] numbers) {
		/***if(numbers.length == 0 || numbers == null) return false;
        TreeSet<Integer> set = new TreeSet<>();
        int count_0 = 0,max,min;
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 0){
                count_0++;
            }else{
                if(!set.contains(numbers[i])){
                    set.add(numbers[i]);
                }else{
                    return false;
                }
            }
        }
        min = set.first();
        max = set.last();
        if( numbers.length - count_0 >= 3){
            if(max - min + 1 >  numbers.length){
                return false;
            }
        }else{
            if(max - min - 1 > count_0){
                return false;
            }
        }
        return true;
        
        ***/
       
        // 没有重复的数,
        // 最大和最小的数差值小于 5  
        if(numbers.length < 5 || numbers == null) return false;
       	
        // 没有相同的数采用位运算
        int myXor = 0,temp;
        int max = -1,min = 14;
        int flag = 0;
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 0) continue;
            if(max < numbers[i]) max = numbers[i];
            if(min > numbers[i]) min = numbers[i];
            // 判断是否重复,此处也看可以用hash
          	if(((flag >> numbers[i]) & 1) == 1) return false;
            flag |= (1 << numbers[i]);  
            
            if(max - min >= 5) return false;
        }
        return true;
    }
}