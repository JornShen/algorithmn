/*********

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

*********/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {  
        List<Interval> temp = new ArrayList<>();
        int start = newInterval.start, end = newInterval.end;
        for (Interval i : intervals) {
            if (start > i.end) temp.add(i);
            if (end < i.start) {
                if (newInterval != null) {
                    temp.add(new Interval(start, end));
                    newInterval = null;
                }
                temp.add(i);
            }
            if (i.start <= start && start <= i.end) {
                start = i.start;
            }
            if (i.start <= end && i.end >= end) {
                end = i.end;
            }   
        }
        if (newInterval != null) temp.add(new Interval(start, end));
        return temp;
    }
}


// 本题关键在于确定start 和 end 并在合适的位置添加

