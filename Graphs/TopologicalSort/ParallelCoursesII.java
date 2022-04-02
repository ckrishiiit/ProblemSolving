package Graphs.TopologicalSort;
import java.util.*;

public class ParallelCoursesII {
    private Map<Integer, Integer> countParents(Map<Integer, List<Integer>> graph) {

        Map<Integer, Integer> counts = new HashMap<>();

        graph.keySet().forEach(node -> {
            counts.put(node, 0);
        });

        graph.keySet().forEach(node -> {
            for (Integer v: graph.get(node)){
                counts.put(v, counts.get(v) + 1);
            }
        });
        return counts;
    }

    public int minNumberOfSemesters(int n, int[][] relations, int k) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i=1; i<=n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i=0; i < relations.length; i++) {
            int[] curr = relations[i];
            graph.get(curr[0]).add(curr[1]);
        }

        Map<Integer, Integer> counts = countParents(graph);

        int size = counts.size();
        int zeroCount = 0;

        for (Map.Entry<Integer, Integer> entry: counts.entrySet()){
            if (entry.getValue() == 0)
                zeroCount++;
        }

        int total = size - zeroCount;
        int temp = zeroCount;
        total += temp/k;
        total += temp % k;
        return total;
    }
    public static void main(String[] a) {
        int[][] relations =  { {2,1}, {3,1}, {4,1}, {1,5}};
        int res = new ParallelCoursesII().minNumberOfSemesters(5, relations, 2);
        System.out.println(res);
    }
}
