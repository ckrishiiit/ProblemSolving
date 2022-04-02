package PriorityQueue;
import java.util.*;

public class MergeKSortedLists {

    public static class ArrayEntry{

        int val;
        int headIndex;
        List<Integer> list;

        public ArrayEntry(int val, int headIndex, List<Integer> list) {
            this.val = val;
            this.headIndex = headIndex;
            this.list = list;
        }
    }

    public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        // [1,4,8]
        // [2,3,5]
        // [3,6,8,9]

        List<Integer> res = new ArrayList<>();

        PriorityQueue<ArrayEntry> pq = new PriorityQueue<>(lists.size(), Comparator.comparingInt(e -> e.val));

        for (List<Integer> list: lists) {
            pq.add(new ArrayEntry(list.get(0), 0, list));
        }

        while (!pq.isEmpty()){

            ArrayEntry entry = pq.poll();
            res.add(entry.val);

            int index = entry.headIndex + 1;
            if (index < entry.list.size()){
                pq.add(new ArrayEntry(entry.list.get(index), index, entry.list));
            }
        }
        return res;
    }

    public static void main(String[] a) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);list1.add(4);list1.add(8);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);list2.add(3);list2.add(7);

        List<Integer> list3 = new ArrayList<>();
        list3.add(3);list3.add(9);list3.add(17);

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        List<Integer> res = new MergeKSortedLists().mergeKSortedLists(lists);
        for (Integer r : res)
            System.out.print(r +" ");
    }
}
