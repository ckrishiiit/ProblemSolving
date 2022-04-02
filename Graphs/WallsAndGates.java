package Graphs;
import java.util.*;

/**
 * https://leetcode.com/problems/walls-and-gates/
 */
public class WallsAndGates {

    private static final int INF = 2147483647;
    private static final int WALL = -1;
    private static final int GATE = 0;

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int hashCode(){
            return Objects.hash(row, col);
        }

        public boolean equals(Object o) {
            if (o == null)
                return false;

            if (!(o instanceof Coordinate))
                return false;

            Coordinate c = (Coordinate) o;
            if (c.row == row && c.col == col)
                return true;

            return false;

        }
    }

    private static int[][] DIRECTIONS = { {1,0}, {0,1}, {0,-1}, {-1,0}};

    private List<Coordinate> getNeighbours(int[][] rooms, Coordinate grid) {

        int row = grid.row;
        int col = grid.col;

        List<Coordinate> list = new ArrayList<>();

        for (int[] dir: DIRECTIONS) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if (newRow < 0 || newRow >= rooms.length || newCol < 0 || newCol >= rooms[0].length
                    || rooms[newRow][newCol] == WALL) {
                continue;
            }

            list.add(new Coordinate(newRow, newCol));
        }
        return list;
    }

    private void bfs(Deque<Coordinate> queue, int[][] rooms, Set<Coordinate> visited ) {

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i=0; i < size; i++) {
                Coordinate coord = queue.poll();
                int gridVal = rooms[coord.row][coord.col];

                for (Coordinate neighbour : getNeighbours(rooms, coord)) {
                    if (visited.contains(neighbour))
                        continue;

                    rooms[neighbour.row][neighbour.col] = 1 + gridVal;
                    queue.add(neighbour);
                    visited.add(neighbour);
                }
            }
        }
    }

    public void wallsAndGates(int[][] rooms) {


        if (rooms == null || rooms.length == 0)
            return;

        Set<Coordinate> visited = new HashSet<>();
        int rows = rooms.length;
        int cols = rooms[0].length;
        Deque<Coordinate> queue = new ArrayDeque<>();

        for (int r = 0;  r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rooms[r][c] == GATE) {
                    Coordinate coord = new Coordinate(r,c);
                    queue.add(coord);
                    visited.add(coord);
                }
            }
        }

        bfs(queue, rooms, visited);
    }
}
//ugWA6vvePUn278ekFZFs