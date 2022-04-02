package PriorityQueue;

import java.util.*;

/**
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * Time : O(ans)
 */
public class UglyNumber {

    private static int[] allowedPrime = {2,3,5};

    public int uglyNumber(int n) {
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) ->
                Integer.compare(a,b));

        pq.add(1);
        set.add(1);

        for (int i=0; i <= n-2; i++){
            int res = pq.poll();
            for (int multiplier : allowedPrime){
                int no = res * multiplier;
                if (!set.contains(no)) {
                    set.add(no);
                    pq.offer(no);
                }
            }
        }
        return pq.poll();
    }

    public static void main(String[] a) {
        UglyNumber obj = new UglyNumber();
        int num = obj.uglyNumber(10);
        System.out.println(num);
    }
}
