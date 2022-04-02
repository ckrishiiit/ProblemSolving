package PriorityQueue;
import java.util.*;

public class KthLargestElementInArray {

    public static int findKthLargest(List<Integer> nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(nums.size(),
                (n1, n2) -> Integer.compare(n1, n2));

        for (int i=0; i < k; i++){
            pq.add(nums.get(i));
        }

        for (int i=k; i < nums.size(); i++){
            if(nums.get(i) > pq.peek()) {
                pq.poll();
                pq.add(nums.get(i));
            }
        }

        return pq.peek();
    }
}
