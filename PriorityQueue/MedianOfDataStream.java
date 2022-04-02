package PriorityQueue;
/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianOfDataStream {

    PriorityQueue<Integer> minPq = new PriorityQueue<>();
    PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

    public void addNum(int num) {
       maxPq.add(num);

       minPq.add(maxPq.poll());

       if (minPq.size() > maxPq.size())
           maxPq.add(minPq.poll());
    }

    public double getMedian() {

        if (minPq.size() == maxPq.size())
            return (minPq.peek() + maxPq.peek()) * 0.5;
        return maxPq.poll();
    }
}
