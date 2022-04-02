package PriorityQueue;
import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 */
public class TopKFrequentWords {

    public static List<String> topKFrequent(String[] words, int k){
        List<String> res = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        for (String w: words) {
            map.merge(w, 1, Integer::sum);
        }

        Map<Integer, List<String>> wordMap = new HashMap<>();
        for (String w: map.keySet()) {
            int count = map.get(w);
            if (!wordMap.containsKey(count))
                wordMap.put(count, new ArrayList<>());
            wordMap.get(count).add(w);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>( (a,b) -> Integer.compare(b,a));
        pq.addAll(wordMap.keySet());

        int totalWords = 0;
        for (int i=0; i < k && totalWords < k; i++) {
            int max = pq.poll();
            List<String> list = wordMap.get(max);
            if (list.size() > 1)
                Collections.sort(list);
            for (int j=0; j < list.size() && totalWords < k; j++) {
                res.add(list.get(j));
                totalWords++;
            }
        }
        return res;
    }

    public static void main(String[] a){

        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        List<String> w = TopKFrequentWords.topKFrequent(words, 4);
        for (String s: w)
            System.out.println(s);
    }
}
