# algorithmn
some  test for algorithmn 

from offer code  or other website


33 丑数, 算法参考他人,比较精妙

34 第一个只出现一个字符的位置  hash统计思想 

35 逆序数 排序的变种,之前采用了插入排序,结果过不了,后面参考了他人,采用了归并排序 
          在归并的时候计算逆序数的对数,由于测试数据实在是太大,所以在某些地方加模才可通过 

36 求公共节点 需要正确 理解第一个公共节点 详细算法见文件 

37 统计有序数组中出现的次数 二分查找+向两边统计

38 树的深度 递归和非递归(用队列实现)版本 递归主要判断左右两边子树的深度谁更大

39 判断树是否是平衡二叉树 递归版本在38中做点改进 非递归版本详细见文件

40 寻找不同的两个数 注释是采用hash思想借用set集合进行判断,取数不是很好取 之后一种是参考别人的算法
                    采用异或的特性,两个数相同,异或为0,0异或任何数还是那个数,之后找出两个数不同的
		    位置(用一个1的移位的数)进行进行分组.


41 求出所有和为某个数的连续正数序列  根据例子找规律,从而得到算法  详细例子见文件 

42 有序队列中求和为sum的两个数  夹逼 详细见文件 

43 字符串移位操作 使用三次翻转实现 巧解2 也很巧妙 详细见文件 

44 单词切割 翻转 需要注意特殊的情况的处理

45 大小王顺子  满足两个条件:没有重复的数字, 最大和最小的值差小于 5
               判断是否有重复的数,可以使用hash方法,或者使用巧妙的位运算:本质上也是hash

46 约瑟夫环问题 游戏圈中最后一个出来的人的编号 递归求解该问题 详细见文档

47 不用乘除法,以及条件判断实现1+...n  采用递归实现, 终止条件采用了&&的特性,前面为0时候不再执行下面代码

48　不用加减乘除实现加法　采用了与产生进位，异或产生值，移位来实现，从不同的角度，有不同的写法，
			第一次写的时候从二进制位的角度，另一种写法是从十进制位写法

49  将字符串转化为数字

50　数组从 0 到n - 1 看是否有重复数字，用hash来做，详细见文件

51  不使用除法实现 B[i]= A[0] * A[1] * ... A[i - 1] * A[i + 1]...A[n - 1] 
    用矩阵的思想去做，先算下三角，后算上三角
 



53 判断是否是数  采用普通算法  文件最上方有正则匹配式子

54 返回串中出现一次的第一个字符 注释为自己做的 非注释为一种解法，比较巧妙的统计数字

55 求链表中环的入口。解法一，采用统计的方式，第一次重复出现的节点是环的起点
                     解法二，比较精妙，需要在好好理解
		     解法三，采用了更精妙的解法，断开每次走过的节点与前一个节点指向


56 删除链表重复的节点 略微有点复杂 没有想到一个步骤（关键步骤） 见文件

57 求中序遍历的下一个节点 没有右边节点不好处理，是题目的难点，注释是自己做达到错误想法


58 判断树是否是镜像  递归和非递归。文件中 贴了其他更好看的代码

59 之字形打印二叉树  一个list遍历实现 或者采用两个栈和实现

60 序列化和反序列化二叉树   本人采用了广度优先遍历的方法 层之间和节点之间采用了不同符号进行区分 
			    文件中注释是更普通的解法，使用了先序遍历（递归的解法）的方法序列化和反序列化


61 二叉搜索树中第k大的节点  中序遍历 递归和非递归版本

62 从数据流中找到中位数 插入排序 动态扩充数组 详细见文件 








