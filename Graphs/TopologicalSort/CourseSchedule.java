package Graphs.TopologicalSort;
import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {
    private Map<Integer, Integer> countParents(Map<Integer, List<Integer>> graph) {

        Map<Integer, Integer> counts = new HashMap<>();
        graph.keySet().forEach(node -> {
            counts.put(node, 0);
        });

        graph.entrySet().forEach(entry -> {
            for (Integer child : entry.getValue()){
                counts.put(child, counts.get(child) + 1);
            }
        });
        return counts;
    }

    private boolean topSort(Map<Integer, List<Integer>> graph) {

        Deque<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> counts = countParents(graph);

        counts.keySet().forEach(node -> {
            if (counts.get(node) == 0) {
                queue.offer(node);
            }
        });

        while (!queue.isEmpty()){
            int top = queue.poll();
            for (Integer child : graph.get(top)) {
                counts.put(child, counts.get(child) - 1);
                if (counts.get(child) == 0)
                    queue.offer(child);
            }
        }

        for (int v : counts.values()) {
            if (v != 0)
                return false;
        }

        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        //build graph
        for (int i=0; i < numCourses; i++){
            graph.put(i, new ArrayList<>());
        }

        for (int i=0; i < prerequisites.length; i++) {
            int[] curr = prerequisites[i];
            graph.get(curr[1]).add(curr[0]);
        }
        return topSort(graph);
    }
}
