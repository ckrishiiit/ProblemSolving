package Graphs.TopologicalSort;
import java.util.*;

/**
 * For this problem, given a list of tasks and a list of requirements, compute a
 * sequence of tasks that can be performed, such that we complete
 * every task once while satisfying all the requirements.
 *
 * tasks = ["a", "b", "c", "d"]
 * requirements = [["a", "b"], ["c", "b"], ["b", "d"]]
 *
 * Time -: O(N + M)
 * N -- Nodes
 * M -- Edges
 */

public class TaskScheduling {

    private Map<String, Integer> countParents(Map<String, List<String>> graph) {
        Map<String, Integer> counts = new HashMap<>();
        graph.keySet().forEach(node ->{
            counts.put(node, 0);
        });

        graph.entrySet().forEach(node -> {
            for(String child: node.getValue()){
                counts.put(child, counts.get(child) + 1);
            }
        });
        return counts;
    }

    private List<String> topSort(Map<String, List<String>> graph) {

        List<String> res = new ArrayList<>();

        Map<String, Integer> counts = countParents(graph);
        Deque<String> queue = new ArrayDeque<>();
        counts.keySet().forEach(node ->{
            if (counts.get(node) == 0)
                queue.add(node);
        });

        while (!queue.isEmpty()) {
            String node = queue.poll();
            res.add(node);

            for (String child : graph.get(node)){
                counts.put(child, counts.get(child) - 1);
                if (counts.get(child) == 0)
                    queue.add(child);
            }
        }
        return res;
    }

    public List<String> taskSchedule(List<String> tasks, List<List<String>> requirements) {

        //build graph
        Map<String, List<String>> graph = new HashMap<>();
        for (String task: tasks) {
            graph.put(task, new ArrayList<>());
        }

        for (List<String> requirement: requirements) {
            graph.get(requirement.get(0)).add(requirement.get(1));
        }

        //topological sort
        return topSort(graph);
    }

    public static void main(String[] a) {
        List<String> tasks = new ArrayList<>();
        tasks.add("A");tasks.add("B");
        tasks.add("C");tasks.add("D");

        List<List<String>> requirements = new ArrayList<>();
        List<String> r1 = new ArrayList<>();
        r1.add("A"); r1.add("B");
        List<String> r2 = new ArrayList<>();
        r2.add("C"); r2.add("B");
        List<String> r3 = new ArrayList<>();
        r3.add("B"); r3.add("D");

        requirements.add(r1);
        requirements.add(r2);
        requirements.add(r3);

        List<String> res = new TaskScheduling().taskSchedule(tasks, requirements);
        for (String r : res) {
            System.out.print(r +" ");
        }
    }
}
