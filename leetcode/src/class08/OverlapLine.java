package class08;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合
 * man-min
 * 
 */
public class OverlapLine {
    //以每条线段的左边界为开始位置的贯穿数
    //开始位置排序，右边记录结束位置
    public static class Line {
        public int start;
        public int end;
        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * 按照起始点升序
     */
    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line l1, Line l2) {
            // TODO Auto-generated method stub
            return l1.start - l2.start;
        }


    }

    //先写暴力解法
    public int overlapLine1(Line[] lines) {
        if (lines == null || lines.length == 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        //选出线段最短的start和最长的end
        for (int i = 0; i < lines.length; i++) {
           min = Math.min(min, lines[i].start);
           max = Math.max(max, lines[i].end);     
        }
        int res = 0;
        //从头到尾过一遍
        for (double i = 0.5; i < max; i++) {
            int cur = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j].start < i && lines[j].end > i) {
                    cur++;
                }
            }
            res = Math.max(res, cur);
        }
        return res;
    }

    /**
     * 堆解法
     * @param lines
     * @return
     */
    public int overlapLine2(Line[] lines) {
        Arrays.sort(lines, new StartComparator());
        //定义end的小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int res = 0;
        for (int i = 0; i < lines.length; i++) {
            while(!heap.isEmpty() && lines[i].start >= heap.peek()) {
                //保证start比堆内部的
                heap.poll();
            }
            heap.add(lines[i].end);
            res = Math.max(res, heap.size());
        }
        return res;
    }

    
}

