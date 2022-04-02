package Graphs.TopologicalSort;
import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseScheduleII {

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

    private List<Integer> topSort(Map<Integer, List<Integer>> graph) {

        List<Integer> res = new ArrayList<>();

        Deque<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> counts = countParents(graph);

        counts.keySet().forEach(node -> {
            if (counts.get(node) == 0) {
                queue.offer(node);
            }
        });

        while (!queue.isEmpty()){
            int top = queue.poll();
            res.add(top);
            for (Integer child : graph.get(top)) {
                counts.put(child, counts.get(child) - 1);
                if (counts.get(child) == 0)
                    queue.offer(child);
            }
        }

        for (int v : counts.values()) {
            if (v != 0)
                return null;
        }

        return res;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        //build graph
        for (int i=0; i < numCourses; i++){
            graph.put(i, new ArrayList<>());
        }

        for (int i=0; i < prerequisites.length; i++) {
            int[] curr = prerequisites[i];
            graph.get(curr[1]).add(curr[0]);
        }

        List<Integer> res = topSort(graph);
        if (res == null)
            return new int[]{};
        int[] order = new int[res.size()];
        for (int i=0; i < res.size(); i++) {
            order[i] = res.get(i);
        }
        return order;

    }

    public static void main(String[] a){
        int[][] prerequisites = { {1,0}};
        int[] res = new CourseScheduleII().findOrder(2, prerequisites);
        for (int v : res)
            System.out.print(v);
    }
}
