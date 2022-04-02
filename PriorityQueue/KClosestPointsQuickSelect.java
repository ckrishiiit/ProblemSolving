package PriorityQueue;
import java.util.*;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointsQuickSelect {

    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distance(){
            return x*x + y*y;
        }
    }

    private Point[] helper(List<Point> points, int start, int end, int position) {

        while (true) {
            int pivot = start;
            int left = start+1;
            int right = end;

            int pivotDistance = points.get(pivot).distance();
            while (left <= right) {
                int leftPointDistance = points.get(left).distance();
                int rightPointDistance = points.get(right).distance();

                if (leftPointDistance > pivotDistance && rightPointDistance < pivotDistance){
                    Collections.swap(points, left, right);
                }

                if (leftPointDistance <= pivotDistance)
                    left++;

                if (rightPointDistance >= pivotDistance)
                    right--;
            }
            if (right == position) {
                Point[] res = new Point[position+1];
                for (int i=0; i < res.length; i++) {
                    res[i] = points.get(i);
                }
                return res;
            }else if (position > right) {
                start = right + 1;
            }else
                end = right - 1;
        }
    }

    public  Point[] kClosestPoints(List<Point> points, int k) {
        int position = k-1;
        int left = 0;
        int right = points.size()-1;
        return helper(points, left, right, position);
    }

    public static void main(String[] a) {
        Point p1 = new Point(2,2);
        Point p2 = new Point(3,3);
        Point p3 = new Point(1,1);
        Point p4 = new Point(4,4);

        List<Point> points = new ArrayList<>();
        points.add(p1); points.add(p2);
        points.add(p3); points.add(p4);

        Point[] res = new KClosestPointsQuickSelect().kClosestPoints(points, 2);
        for (Point p : res){
            System.out.println(p.x +","+p.y);
        }
    }
}
