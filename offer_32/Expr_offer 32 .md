### Expr_offer 32 

 题目描述:

> 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

思路: 将数字转化为字符串进行比较,进行排序

比较条件如下

> 若ab > ba 则 a > b，
>
> 若ab < ba 则 a < b，
>
> 若ab = ba 则 a = b；

此条件比较清晰,就是比对两个串,谁放前面比较小,就往前排序

本次比较简单的做法是调用java 的Arrays.sort() api 进行排序,比较的条件可以使用可以使用接口:comparable,
或者传入一个继承了的comparetor<>的对象(同时实现了compare 方法)

如下

```java
 Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String s, String t) {
                //前后两个拼接起来后进行比较
                String s1 = s + t;
                String t1 = t + s;
                return s1.compareTo(t1);
            }
        });
```

此处使用了匿名构造函数,比较方便.

返回0表是相等,返回1表示s > t, 返回-1 表示  s < t ,sort方法根据此从小到大进行排序.

具体代码见Exproffer_32.java文件.

---

总结: 一开始使用了如下代码进行比较

```java
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
}
```

因为看到给的范例,所以采用了这个比较方法,比较主观臆断,后面看了别人的参考,才知道了正确的比较规则,

其实只要将两个加起来比较一下,就知道谁在前面了.