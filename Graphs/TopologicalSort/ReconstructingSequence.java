package Graphs.TopologicalSort;
import java.util.*;

/**
 * Construct toplogical Order sequence then compare with the original seq.
 * If both same return true else return false..
 */
public class ReconstructingSequence {

    private Map<Integer, Integer> countParents(Map<Integer, Set<Integer>> graph) {

        Map<Integer, Integer> counts = new HashMap<>();
        graph.keySet().forEach( node ->{
            counts.put(node, 0);
        });

        graph.entrySet().forEach(entry -> {
            for(Integer child : entry.getValue()) {
                counts.put(child, counts.get(child) + 1);
            }
        });
        return counts;
    }

    private List<Integer> topSort(Map<Integer, Set<Integer>> graph) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> counts = countParents(graph);
        counts.keySet().forEach( seq -> {
            if (counts.get(seq) == 0)
                queue.offer(seq);
        });

        while (!queue.isEmpty()) {
            if (queue.size() > 1)
                return res;
            Integer node = queue.poll();
            res.add(node);

            for (Integer child: graph.get(node)) {
                counts.put(child, counts.get(child) - 1);
                if (counts.get(child) == 0)
                    queue.offer(child);
            }
        }
        return res;
    }

    public boolean sequenceConstruction(List<Integer> original, List<List<Integer>> seqs) {

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (Integer seq : original) {
            graph.put(seq, new HashSet<>());
        }

        for (List<Integer> seq : seqs) {
            int size = seq.size();
            for (int i = 0; i <= size - 2; i++) {
                int curr = seq.get(i);
                int next = seq.get(i + 1);
                if (!graph.get(curr).contains(next)) {
                    graph.get(curr).add(next);
                }
            }
        }

        List<Integer> sequence = topSort(graph);
        return sequence.equals(original);
    }

    public static void main(String[] a) {
        List<List<Integer>> seqs = new ArrayList<>();
        List<Integer> s1 = new ArrayList<>(Arrays.asList(1,2));
        List<Integer> s2 = new ArrayList<>(Arrays.asList(1,3));
        List<Integer> s3 = new ArrayList<>(Arrays.asList(2,3));
        seqs.add(s1);seqs.add(s2);seqs.add(s3);

       List<Integer> original = new ArrayList<>(Arrays.asList(1,2,3));
       boolean seq = new ReconstructingSequence().sequenceConstruction(original, seqs);

       System.out.println(seq);
    }
}
