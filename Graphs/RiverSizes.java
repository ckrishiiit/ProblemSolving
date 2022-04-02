package Graphs;
import java.util.*;

public class RiverSizes {

    private static int[][] DIRECTIONS = { {1,0}, {0,1}, {0,-1}, {-1,0}};

    private static int bfs(int[][] matrix, int row, int col, boolean[][] visited) {

        Deque<int[]> queue = new ArrayDeque<>();
        visited[row][col] = true;
        queue.add(new int[]{row, col});

        int riverSize = 1;
        int rows = matrix.length;
        int cols = matrix[0].length;

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();
            row = curr[0];
            col = curr[1];

            for (int[] dir : DIRECTIONS){
                int newRow = dir[0] + row;
                int newCol = dir[1] + col;

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols
                        || visited[newRow][newCol] || matrix[newRow][newCol] == 0) {
                    continue;
                }

                riverSize++;
                queue.add(new int[]{newRow, newCol});
                visited[newRow][newCol] = true;
            }
        }
        return riverSize;
    }

    public static List<Integer> riverSizes(int[][] matrix) {

        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return res;

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int row=0; row < rows; row++) {
            for (int col = 0;  col < cols; col++) {
                if (matrix[row][col] == 1 && !visited[row][col]) {
                    int riverSize = bfs(matrix, row, col, visited);
                    res.add(riverSize);
                }
            }
        }
        return res;
    }

    public static void main(String[] a) {

        int[][] arr = {
                {1,0,0,1,0},
                {1,0,1,0,0},
                {0,0,1,0,1},
                {1,0,1,0,1},
                {1,0,1,1,0}
        };

        List<Integer> list = RiverSizes.riverSizes(arr);
        for (int n: list)
            System.out.print(n +" ");
    }
}
