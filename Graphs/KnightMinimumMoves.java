package Graphs;
import java.util.*;

/**
 *
 * On an infinitely large chessboard, a knight is located on [0, 0].
 * A knight can move in eight directions
 * Given a destination coordinate [x, y],
 * determine the minimum number of moves from [0, 0] to [x, y].
 */
public class KnightMinimumMoves {

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(int row, int col){
            this.row = row;
            this.col = col;
        }

        public int hashCode() {
            return Objects.hash(row, col);
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (!(o instanceof Coordinate))
                return false;
           Coordinate c = (Coordinate) o;
           if (c.row == row && c.col == col)
               return true;

           return false;
        }
    }

    private static int[][] DIRECTIONS = { {2,-1}, {2,1}, {-2,-1},{-2,1}, {1,-2}, {1,2},
            {-1,-2}, {-1,2}};

    private static List<Coordinate> getNeighbours(Coordinate grid){
        int row = grid.row;
        int col = grid.col;
        List<Coordinate> list = new ArrayList<>();
        for (int[] dir: DIRECTIONS) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            list.add(new Coordinate(newRow, newCol));
        }
        return list;
    }

    private static int bfs(Coordinate coordinate, int x, int y) {
        int steps = 0;
        Deque<Coordinate> queue = new ArrayDeque<>();
        queue.add(coordinate);

        Set<Coordinate> visited = new HashSet<>();
        visited.add(coordinate);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i < size; i++){
                Coordinate grid = queue.poll();

                if (grid.row == x && grid.col == y)
                    return steps;

                for (Coordinate neighbour : getNeighbours(grid)) {
                    if (visited.contains(neighbour))
                        continue;

                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
            steps++;
        }
        return steps;
    }

    public static int getKnightShortestPath(int x, int y) {
        return bfs(new Coordinate(0,0), x, y);
    }

    public static void main(String[] a) {
        int steps = KnightMinimumMoves.getKnightShortestPath(3,3);
        System.out.println(steps);
    }
}
