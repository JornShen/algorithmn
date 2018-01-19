/*
leetcode 149. Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

// 我写法，错误，斜率考虑错误

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    class Slope {
        // 表示斜率
        private int h, w;
        Slope(int h, int w) {
            this.h = h;
            this.w = w;
        }

        // 重写 equal 方法
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Slope slope = (Slope) o;
            if (h != slope.h) return false;
            return w == slope.w;
        }

        @Override
        public int hashCode() {
            int result = h;
            result = 31 * result + w;
            return result;
        }
    }
    
    private Map<Slope, Set<Point>> map;
    private int maxNum = 0;
    
    public int maxPoints(Point[] points) {
        map  = new HashMap<>();
        // 斜率的问题, 如何表示
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                collectData(points[i], points[j]);
            }
        }
        return maxNum;
    }
    
    public void collectData(Point p1, Point p2) {
        if (p1.x == p2.x && p1.y == p2.y) return; // 当两个点相同的时候
        Slope slope = null;
        if (p1.x == p2.x)  slope = new Slope(p1.x, 0);
        else if (p1.y == p2.y) slope = new Slope(0, p1.y);
        else {	
            int x = Math.abs(p1.y - p2.y);
            int y = Math.abs(p1.x - p2.x);
            int a = Math.max(x, y), b = Math.min(x, y), c = 1;
            // 辗转相除法
            while(b != 0) {
            /* 余数不为0，继续相除，直到余数为0 */
                c = a % b;
                a = b;
                b = c;
            }
            slope = new Slope(x / a, y / a);
        }
        // 当垂直或平行的时候
        Set<Point> set = null;
        if (map.containsKey(slope)) set = map.get(slope);
        else {
            set = new HashSet<>();
            map.put(slope, set);
        }
        set.add(p1);
        set.add(p2);
        maxNum = Math.max(maxNum, set.size+-
    }
}

//　另一种错误的做法, 不要用 double 的做法来表示斜率，　因为有精度问题.
class Solution {
	public int maxPoints(Point[] points) {
        if (points.length < 3) return points.length;
        // 用　double 存斜率有问题
        Map<String, Integer> map = new HashMap<>(); // 记录到某个点的相同斜率的统计
        int maxSum = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int dup = 1;
            int maxOne = 0;
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                double slope = 0.0;
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    dup++;
                    continue;
                } else if (points[i].x == points[j].x) {
                    slope = Integer.MAX_VALUE; //　在同一条直线上
                } else {
                    slope = 1.0 * (points[j].y - points[i].y) / (points[j].x - points[i].x); // 计算斜率
                }
                int sum = map.containsKey(slope) ? map.get(slope) + 1 : 1;
                maxOne = Math.max(maxOne, sum);
            }
            maxSum = Math.max(maxOne + dup, maxSum);
        }
        return maxSum;
    }
}

//　参考讨论区里面的写法, 用 String 来表示斜率的方法，　double 会有精度问题
class Solution {
    public int maxPoints(Point[] points) {
        if (points.length < 3) return points.length;
        // 用　double 存斜率有问题
        Map<String, Integer> map = new HashMap<>(); // 记录到某个点的相同斜率的统计
        int maxSum = 0;
        for (int i = 0; i < points.length - 1; i++) {

            map.clear();
            int dup = 1;
            int maxOne = 0;

            for (int j = i + 1; j < points.length; j++) {
                //　此处可以从 i + 1 开始遍历
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    dup++;
                    continue;
                }
                // 辗转相除法, 迭代计算
                int x = points[i].x - points[j].x;
                int y = points[i].y - points[j].y;
                int m = x, n = y, c;
                while (n != 0) {
                    c = m % n;
                    m = n;
                    n = c;
                }
                x /= m;
                y /= m;
                String slope = String.valueOf(x) + String.valueOf(y);　// 用　String 来代替斜率
                int count = map.getOrDefault(slope, 0); // 此句值得学习
                count++;
                map.put(slope, count);
                maxOne = Math.max(maxOne, count);
            }
            maxSum = Math.max(maxOne + dup, maxSum);
        }
        return maxSum;
    }
}
