package Graphs;
import java.util.*;
import java.util.stream.Collectors;

public class AlienDictionary {

    public static <T> Map<T, Integer> countParents(Map<T, List<T>> graph) {
        Map<T, Integer> counts = new HashMap<>();
        graph.keySet().forEach(node -> {
            counts.put(node, 0);
        });
        // loop through every node and add to the child node 1 parent
        graph.entrySet().forEach(entry -> {
            for (T node : entry.getValue()) {
                counts.put(node, counts.get(node) + 1);
            }
        });
        return counts;
    }

    public static List<Character> topoSort(Map<Character, List<Character>> graph) {

        List<Character> res = new ArrayList<>();
        // make a queue that we will use for our solution
        PriorityQueue<Character> q = new PriorityQueue<>();
        // loop through all nodes and add all nodes that do not have any parent
        Map<Character, Integer> counts = countParents(graph);
        counts.entrySet().forEach(entry -> {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        });

        return null;
    }

    public static String alienOrder(List<String> words) {
        HashMap<Character, List<Character>> graph = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!graph.containsKey(c)) {
                    graph.put(c, new ArrayList<>());
                }
            }
        }

        String prev = words.get(0);
        // derive order from adjacent words
        for (int i = 1; i < words.size(); i++) {
            String cur = words.get(i);
            for (int j = 0; j < prev.length() && j < cur.length(); j++) {
                if (prev.charAt(j) != cur.charAt(j)) {
                    // ignore duplicates
                    if (!graph.get(prev.charAt(j)).contains(cur.charAt(j))) {
                        graph.get(prev.charAt(j)).add(cur.charAt(j));
                    }
                    break;
                }
            }
            prev = cur;
        }

        List<Character> sorted = topoSort(graph);
        return sorted == null ? ""
                : sorted.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

    }

    public static void main(String[] a){
        List<String> words = new ArrayList<>();
     /*   words.add("wrt");
        words.add("wrf");
        words.add("er");
        words.add("ett");
        words.add("rftt");*/
        words.add("z");
        words.add("x");
        words.add("z");

        AlienDictionary.alienOrder(words);
    }
}
