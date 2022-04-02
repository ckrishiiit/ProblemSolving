package PriorityQueue;
import java.util.*;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestInASortedMatrix {

    static class ArrayEntry{
        List<Integer> list;
        int headIndex;
        int val;

        public ArrayEntry(int headIndex, int val, List<Integer> list) {
            this.headIndex = headIndex;
            this.val = val;
            this.list = list;
        }
    }

    public static int kthSmallest(List<List<Integer>> matrix, int k) {
        int n = matrix.size();
        if (n == 0)
            return 0;

        PriorityQueue<ArrayEntry> pq = new PriorityQueue<>(k,
                Comparator.comparingInt(entry -> entry.val));

        for (List<Integer> list: matrix) {
            pq.add(new ArrayEntry(0, list.get(0), list));
        }

        int index = 1;

        while (!pq.isEmpty()) {
            ArrayEntry entry = pq.poll();
            if (k ==  index) {
                return entry.val;
            }

            int headIndex = entry.headIndex + 1;
            if (headIndex < entry.list.size()) {
                pq.add(new ArrayEntry(headIndex, entry.list.get(headIndex), entry.list));
            }
            index++;
        }
        return n;
    }

    public static void main(String[] a){
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);l1.add(5);l1.add(9);
        List<Integer> l2 = new ArrayList<>();
        l2.add(10);l2.add(11);l2.add(13);
        List<Integer> l3 = new ArrayList<>();
        l3.add(12);l3.add(13);l3.add(15);

        List<List<Integer>> list = new ArrayList<>();
        list.add(l1); list.add(l2); list.add(l3);

        int element = KthSmallestInASortedMatrix.kthSmallest(list, 8);
        System.out.println(element);

    }
}
