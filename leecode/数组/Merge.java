/*

leetcode 56:	

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

*/

／／　我的做法，　比较简单，　根据 start 排序，　后进行归并
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {

        if (intervals.size() == 0 || intervals == null) return intervals;
        if (intervals.size() == 1) return intervals;

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval interval, Interval t1) {
                if (interval.start <  t1.start) return -1;
                if (interval.start > t1.start) return 1;
                return 0;
            }
        });

        Interval tmp = intervals.get(0);
        int start = tmp.start;
        int end = tmp.end;
        List<Interval> list = new ArrayList<>();
        // 归并操作
        for (int i = 1; i < intervals.size(); i++) {
            Interval t = intervals.get(i);
            if (t.start > end) {　// 如果　start 大于　end　记录间隔，　否则扩大 end 区域．
                list.add(new Interval(start, end));
                start = t.start;
                end = t.end;
            } else {
                end = Math.max(end, t.end);
            }
        }

        list.add(new Interval(start, end));
        return list;
    }
}


／／　别人的做法

class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        // Collections.sort(intervals, new Comparator<Interval>() {
        //     public int compare(Interval i1, Interval i2) {
        //         return i1.start - i2.start;
        //     }
        // });
        List<Interval> li = new ArrayList<>();
        if(intervals.size() == 0) return li;
        
        int[] istart = new int[intervals.size()];
        int[] iend = new int[intervals.size()];
        
        for(int i = 0; i < intervals.size(); i++) {
            Interval temp = intervals.get(i);
            istart[i] = temp.start;
            iend[i] = temp.end;
        }

        Arrays.sort(istart);
        Arrays.sort(iend);
        
        int currentStart = istart[0];
        int currentEnd = iend[0];

        for(int j = 1; j < istart.length; j++) {
            if(currentEnd < istart[j]) {
                li.add(new Interval(currentStart, currentEnd));
                currentStart = istart[j];
                currentEnd = iend[j];
            }　else 
                currentEnd = Math.max(currentEnd, iend[j]);　// 扩大右边的边界
        }
        li.add(new Interval(currentStart, currentEnd));
        return li;
    }
}


public List<Interval> merge(List<Interval> intervals) {
    // sort start　&　end
    int n = intervals.size();
    int[] starts = new int[n];
    int[] ends = new int[n];
    for (int i = 0; i < n; i++) {
        starts[i] = intervals.get(i).start;
        ends[i] = intervals.get(i).end;
    }
    Arrays.sort(starts);
    Arrays.sort(ends);
    // loop through
    List<Interval> res = new ArrayList<Interval>();
    for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
        if (i == intervals.size() - 1 || starts[i + 1] > ends[i]) {
            res.add(new Interval(starts[j], ends[i]));
            j = i + 1;
        }
    }
    return res;
}
