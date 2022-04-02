package Graphs.TopologicalSort;
import java.util.*;
/**
 * https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {

    private Map<Character, Integer> countParents(Map<Character, List<Character>> graph){

        Map<Character, Integer> counts = new HashMap<>();
        graph.keySet().forEach( node -> {
            counts.put(node, 0);
        });

        graph.entrySet().forEach(entry -> {

            for (char ch : entry.getValue()){
                counts.put(ch, counts.get(ch) + 1);
            }
        });

        return counts;
    }


    private String topSort(Map<Character, List<Character>> graph){
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Character> queue = new PriorityQueue<>();

        Map<Character, Integer> counts = countParents(graph);
        counts.keySet().forEach(node -> {
            if (counts.get(node) == 0)
                queue.offer(node);
        });

        while (!queue.isEmpty()) {
            Character top = queue.poll();
            sb.append(top);

            for (char ch: graph.get(top)){
                counts.put(ch, counts.get(ch) - 1);
                if (counts.get(ch) == 0)
                    queue.offer(ch);
            }
        }

        //for cyclic check..
        for (int count: counts.values()) {
            if (count != 0)
                return "";
        }
        return sb.toString();
    }

    public String alienOrder(List<String> words) {
        if (words == null || words.size() == 0)
            return "";

        Map<Character, List<Character>> graph = new HashMap<>();
        for (String word: words){
            for (char ch : word.toCharArray()) {
                graph.putIfAbsent(ch, new ArrayList<>());
            }
        }

        int size = words.size();
        for (int i=0; i <= size-2; i++) {
            String curr = words.get(i);
            String next = words.get(i+1);

            for (int j=0; j < curr.length() && j < next.length(); j++) {
                if (curr.charAt(j) != next.charAt(j)) {
                    graph.get(curr.charAt(j)).add(next.charAt(j));
                    break;
                }
            }
        }
        return topSort(graph);
    }

    public static void main(String[] a){
        List<String> words = new ArrayList<>();
        /*words.add("wrt");
        words.add("wrf");
        words.add("er");
        words.add("ett");
        words.add("rftt");*/

        words.add("da");
        words.add("na");
        words.add("hai");
        words.add("hang");
        words.add("sha");
        words.add("si");


        String alienOrder = new AlienDictionary().alienOrder(words);
        System.out.println(alienOrder);
    }
}
