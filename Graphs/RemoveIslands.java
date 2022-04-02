package Graphs;
import java.util.*;

public class RemoveIslands {

    private static int[][] DIRECTIONS =  {{0,1}, {1,0}, {0,-1}, {-1,0}};

    private void bfs(int[][] matrix, boolean[][] visited, int row, int col) {

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});
        matrix[row][col] = 2;
        visited[row][col] = true;

        int rows = matrix.length;
        int cols = matrix[0].length;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            row = current[0];
            col = current[1];

            for (int[] dir : DIRECTIONS) {
                int newRow = dir[0] + row;
                int newCol = dir[1] + col;

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || visited[newRow][newCol]
                        || matrix[newRow][newCol] == 0) {
                    continue;
                }

                matrix[newRow][newCol] = 2;
                queue.offer(new int[]{newRow,newCol});
                visited[newRow][newCol] = true;
            }
        }
    }

    public int[][] removeIslands(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int row=0; row < matrix.length; row++) {
            for (int col=0; col < matrix[row].length; col++) {

                boolean rowIsBorder = row == 0 || row == matrix.length - 1;
                boolean colIsBorder = col == 0 || col == matrix[row].length-1;
                boolean isBorder = rowIsBorder || colIsBorder;

                if (!isBorder)
                    continue;

                if (matrix[row][col] != 1)
                    continue;

                bfs(matrix, visited, row, col);

            }
        }

            //change 2'to 1's and 1's to 0.
            for (int i=0; i < rows; i++) {
                for (int j=0; j < cols; j++) {

                    if (matrix[i][j] == 2) {
                        matrix[i][j] = 1;
                    }else if (matrix[i][j] == 1) {
                        matrix[i][j] = 0;
                    }
                }
            }
        return matrix;
    }

    public static void main(String[] a){
       /* int[][] matrix = {
                {1,0,0,0,0,0},
                {0,1,0,1,1,1},
                {0,0,1,0,1,0},
                {1,1,0,0,1,0},
                {1,0,1,1,0,0},
                {1,0,0,0,0,1}
        };*/

        int[][] matrix = {
                {1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0}
        };

        RemoveIslands obj = new RemoveIslands();
        matrix = obj.removeIslands(matrix);
        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] +" ");
            }
            System.out.println();
        }
    }
}
