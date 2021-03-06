package Graphs;

public class CycleInGraph {
    public boolean cycleInGraph(int[][] edges) {

        int numberOfNodes = edges.length;
        boolean[] visited = new boolean[numberOfNodes];
        boolean[] currentlyInStack = new boolean[numberOfNodes];

        for (int node = 0; node < numberOfNodes; node++) {
            if (visited[node])
                continue;

            boolean containsCycle = isNodeInCycle(node, edges, visited, currentlyInStack);
            if (containsCycle)
                return true;
        }
        return false;
    }


    private boolean isNodeInCycle(int node, int[][] edges, boolean[] visited, boolean[] currentlyInStack){

        visited[node] = true;
        currentlyInStack[node] = true;

        boolean containsCyle = false;
        int[] neighbors = edges[node];
        for (int neighbor: neighbors) {
            if (!visited[neighbor]) {
                containsCyle = isNodeInCycle(neighbor, edges, visited, currentlyInStack);
            }

            if (containsCyle)
                return true;
            else if (currentlyInStack[neighbor])
                return true;
        }

        currentlyInStack[node] = false;
        return false;
    }
}

