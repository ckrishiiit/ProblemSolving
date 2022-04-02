package PriorityQueue;
import java.util.*;

/***
 * https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {

    public static String reorganizeString(String s) {

        if (s == null || s.length() == 0)
            return "";

        Map<Character, Integer> map = new HashMap<>();
        for (char ch: s.toCharArray()){
            map.merge(ch, 1, Integer::sum);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a,b) ->
                Integer.compare(b.getValue(), a.getValue()));

        pq.addAll(map.entrySet());

        int n = s.length();
        if (pq.peek().getValue() > (n+1)/2)
            return "";

        char[] res = new char[n];
        int pointer = 0;

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            for (int i=0; i < entry.getValue(); i++) {
                res[pointer] = entry.getKey();
                pointer += 2;

                if (pointer >= n){
                    pointer = 1;
                }
            }
        }
        return new String(res);
    }

    public static void main(String[] a){
        String s = ReorganizeString.reorganizeString("aabc");
        System.out.println(s);
    }
}
