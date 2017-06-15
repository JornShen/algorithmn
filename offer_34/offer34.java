import java.util.*;


/*
在一个字符串(1<=字符串长度<=10000，全部由字母组成)
中找到第一个只出现一次的字符,并返回它的位置

如: "google" 返回4 

*/
public class Solution {
     
    //-----  第一次做的版本-------
     /*class Pos{
        public char s;
        public int pos;
        public Pos(char s,int pos) {
            this.s = s;
            this.pos = pos;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if((o == null) ||(o.getClass() != this.getClass()))
                return false;
            Pos p = (Pos) o;
            return p.s == this.s;
        }
    }
        
    public int FirstNotRepeatingChar(String str) {

           char[] cstr = str.toCharArray();

        // map 集合 处理映射关系
        HashMap<Character,Integer> map1 = new HashMap<>();
        // 统计字符
        List<Pos> list = new ArrayList<>();
        for(int i = 0; i < cstr.length; i++){
            if(!map1.containsKey(cstr[i])) {
                map1.put(cstr[i], 1);
                list.add(new Pos(cstr[i],i));
            }else{
                if(map1.get(cstr[i]) == 1){
                    list.remove(new Pos(cstr[i],1));
                }
                map1.put(cstr[i],map1.get(cstr[i])+1);
            }
        }
        if(list.size() != 0){
            return list.get(0).pos;
        }else{
            return -1;
        }
    }*/
    //------------------------


    // -----其实只需要使用到list就行了 
     
    public int FirstNotRepeatingChar(String str) {
        if(str == null || str.length()==0) return -1;
        List<Character> list = new ArrayList<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if(!list.contains(ch)){
                list.add(Character.valueOf(ch));
            }else{
                list.remove(Character.valueOf(ch));
            }
        }
        if(list.size() <=0) return -1;
        return str.indexOf(list.get(0));
    }

    //----- 简单的hash 思想  

    public int FirstNotRepeatingChar(String str)
    {
        char[] c = str.toCharArray();
        int[] a = new int['z' + 1];
        //直接hash统计
        for (char d : c)
            a[(int) d]++;

        // 再次遍历原来的数组,看是否有出现一次,返回位置
        for (int i = 0; i < c.length; i++)
            if (a[(int) c[i]] == 1)
                return i;
        return -1;
    }

}









