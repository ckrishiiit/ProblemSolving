package PriorityQueue;
/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPoints {

    public static Point[] kClosestPoints(Point[] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, (p1,p2) -> Integer.compare(p2.distanceToOrigin(),
                p1.distanceToOrigin()));

        for (int i = 0; i < k; i++) {
            maxHeap.add(points[i]);
        }
        for (int i = k; i < points.length; i++) {
            Point point = points[i];
            Point furthestPointInHeap = maxHeap.peek();
            if (point.distanceToOrigin() < furthestPointInHeap.distanceToOrigin()) {
                maxHeap.poll();
                maxHeap.add(point);
            }
        }
        Point[] res = new Point[k];
        return maxHeap.toArray(res);
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distanceToOrigin() {
            // "-" for max heap
            return (int) (Math.pow(x, 2) + Math.pow(y, 2));
        }
    }
    public static void main(String[] a){
        Point p1 = new Point(2,2);
        Point p2 = new Point(3,3);
        Point p3 = new Point(1,1);
        Point p4 = new Point(4,4);

        Point[] arr = new Point[4];
        arr[0] = p1;arr[1] = p2;
        arr[2] = p3;arr[3] = p4;

        Point[] res = KClosestPoints.kClosestPoints(arr, 2);
        for (Point p : res) {
            System.out.println(p.x +"," +p.y);
        }
    }
}
