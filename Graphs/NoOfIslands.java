package Graphs;
import java.util.*;

/**
 * https://leetcode.com/problems/number-of-islands/
 */
public class NoOfIslands {

    private static class Coordinate{

        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int[][] DIRECTIONS = { {1,0}, {0,1}, {0,-1}, {-1,0}};

    private static List<Coordinate> getNeighbours(List<List<Integer>> grid, Coordinate coord) {

        List<Coordinate> list = new ArrayList<>();
        int row = coord.row;
        int col = coord.col;

        for (int[] dir : DIRECTIONS) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if( newRow < 0 || newRow >= grid.size() || newCol < 0 || newCol >= grid.get(0).size()
                    || grid.get(newRow).get(newCol) == 0) {
                continue;
            }
            list.add(new Coordinate(newRow, newCol));
        }
        return list;
    }

    private static void bfs(List<List<Integer>> grid, int row, int col, boolean[][] visited) {
        visited[row][col] = true;//0, 0
        Coordinate coord = new Coordinate(row, col);
        Deque<Coordinate> queue = new ArrayDeque<>();
        queue.add(coord);

        while (!queue.isEmpty()) {

            coord = queue.poll();
            //get all the neighbours for the node
            // if already visited do not do anything.
            // if not visited then add to queue & mark as visited.

            for(Coordinate neighbour: getNeighbours(grid, coord)) {

                if (visited[neighbour.row][neighbour.col])
                    continue;

                visited[neighbour.row][neighbour.col] = true;
                queue.add(new Coordinate(neighbour.row,neighbour.col));
            }
        }
    }

    public static int countIslands(List<List<Integer>> grid) {

        int noOfIslands = 0;
        int rows = grid.size();
        int cols = grid.get(0).size();

        boolean[][] visited = new boolean[rows][cols];

        for (int row=0; row < rows; row++) {
            for (int col=0; col < cols; col++) {
                if (grid.get(row).get(col) == 1 && !visited[row][col]) {
                    noOfIslands++;
                    bfs(grid, row, col, visited);
                }
            }
        }
        return noOfIslands;
    }

    public static void main(String[] a) {
        List<List<Integer>> grid = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,1,1,0,0,0));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(1,1,1,1,0,0));
        List<Integer> l3 = new ArrayList<>(Arrays.asList(1,1,1,0,0,0));
        List<Integer> l4 = new ArrayList<>(Arrays.asList(0,1,0,0,0,0));
        List<Integer> l5 = new ArrayList<>(Arrays.asList(0,0,0,0,1,0));
        List<Integer> l6 = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));

        grid.add(l1);
        grid.add(l2);
        grid.add(l3);
        grid.add(l4);
        grid.add(l5);
        grid.add(l6);

        int noOfIslands = NoOfIslands.countIslands(grid);
        System.out.println(noOfIslands);
    }
}
