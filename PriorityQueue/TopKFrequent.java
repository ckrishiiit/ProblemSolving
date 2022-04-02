package PriorityQueue;
import java.util.*;

public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.merge(n, 1, Integer::sum);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>( (a,b) -> Integer.compare(map.get(a), map.get(b)));

        pq.addAll(map.keySet());
        int size = map.size() - k;

        while (size-- > 0) {
            pq.poll();
        }

        int[] res = new int[k];
        for (int i=0; i < k; i++){
            res[i] = pq.poll();
        }
        return res;
    }

    public static void main(String[] a){
        int[] arr = {1,1,1,2,2,3};
        int[] res = new TopKFrequent().topKFrequent(arr, 2);
        for (int n: res)
            System.out.println(n);
    }
}
