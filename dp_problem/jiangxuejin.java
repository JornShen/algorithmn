


小v今年有n门课，每门都有考试，为了拿到奖学金，小v必须让自己的平均成绩至少为avg。每门课由平时成绩和考试成绩组成，满分为r。现在他知道每门课的平时成绩为ai ,若想让这门课的考试成绩多拿一分的话，小v要花bi 的时间复习，不复习的话当然就是0分。同时我们显然可以发现复习得再多也不会拿到超过满分的分数。为了拿到奖学金，小v至少要花多少时间复习。

输入描述:

第一行三个整数n,r,avg(n大于等于1小于等于1e5，r大于等于1小于等于1e9,avg大于等于1小于等于1e6)，接下来n行，每行两个整数ai和bi，均小于等于1e6大于等于1

输入

5 10 9
0 5	
9 1
8 1
0 1
9 100
输出

43

基本思路：

/**** 
     *
     *　贪心算法
     *
     　  　sort(时间花费)；
         for（时间花费从小到大）
              if 当前课程满分后不能获得奖学金
                  复习至满分，累加复习时间，然后复习下一门
             　else if 当前课程满分后能获得奖学金
                 所需时间 += （所需总分 - 当前分数）*在该课程上获得1分所需时间
                 输出时间；
                 退出循环。
*
*/

有点贪心算法。　思路还是比较清晰的。

```java


import java.util.Scanner;
public class Main {
   public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {//注意while处理多个case
            int n = in.nextInt();
            long r = in.nextLong();
            long avg = in.nextLong();
            long[][] arr = new long[n][2];
            long total = avg*n;//需要的分数
            long score = 0;//现在的分数
            for(int i = 0;i<n;i++){
                arr[i][0] = in.nextLong();//平时成绩
                arr[i][1] = in.nextLong();//时间
                score += arr[i][0];
            }
            sort(arr);
            long time = 0;
            int i = 0;
            while(score<total&&i<n){
                if(arr[i][0]<r){
                    //第i门课没有满分
                    time += arr[i][1];
                    score++;
                    arr[i][0] += 1;
                }else{
                    i++;
                }
            }
            System.out.println(time);
             
       }
       in.close();
    }
    //对时间进行排序
    public static void sort(long[][]a){
        //　冒泡排序　经过优化
        for(int i = 0;i<a.length-1;i++){
            boolean flag = true;
            for(int j = 0;j<a.length-1-i;j++){
                if(a[j][1]>a[j+1][1]){
                    long temp = a[j][0];
                    long temp2 = a[j][1];
                    a[j][0] = a[j+1][0];
                    a[j+1][0] = temp;
                    a[j][1] = a[j+1][1];
                    a[j+1][1] = temp2;
                    flag = false; // 如果没有更新的化，提前跳出循环
                }
            }
 			// 优化
            if(flag)
                return;
        }
    }
}

```


我的写法, 但是测例只过了40%,　排序的部分有点问题.

```java


 public static void scholarship() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt(); // n 门课
            long r = scanner.nextLong(); // 满分
            long avg = scanner.nextLong(); // 平均成绩
            Course[] courses = new Course[n];
            long scores = 0;
            for (int i = 0; i < n; i++) {
                courses[i] = new Course(scanner.nextLong(), scanner.nextLong());
                scores += courses[i].a;
            }

            // 排序
            Arrays.sort(courses);
            long time = 0;
            long wholeScores = avg * n;
            for (Course c : courses) {
                if (scores + (r - c.a) < wholeScores) {
                    // 当前分数到满分拿不到奖学金
                    // 复习到满分
                    scores += (r - c.a);
                    time += (r - c.a) * c.b;
                } else {
                    time += (wholeScores - scores) * c.b;
                    break;
                }
            }
            System.out.println(time);
        }
        scanner.close();
    }


    static class Course implements Comparable<Course>{
        public long a; // 平时成绩
        public long b; // 多拿一分的话，小v要花bi 的时间复习
        public Course(long a, long b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Course course) {

            if (b > course.b) {
                return 1;
            } else if (b == course.b) {
                return 0;
            } else {
                return -1;
            }

        }
    }

```




