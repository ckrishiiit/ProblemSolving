package Graphs;
import java.util.*;
/**
 * https://leetcode.com/problems/flood-fill/
 */
public class FloodFill {

    private class Coordinate{

        int row;
        int col;

        public Coordinate(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] DIRECTIONS = {{1,0}, {0,1}, {0,-1}, {-1,0}};

    private List<Coordinate> getNeighbours(int[][] image, Coordinate grid) {
        List<Coordinate> list = new ArrayList<>();

        int row = grid.row;
        int col = grid.col;

        int rows = image.length;
        int cols = image[0].length;

        for (int[] dir : DIRECTIONS) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols)
                continue;

            list.add(new Coordinate(newRow, newCol));
        }

        return list;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        Coordinate coord = new Coordinate(sr, sc);
        int srcColor = image[sr][sc];
        image[sr][sc] = newColor;
        Deque<Coordinate> queue = new ArrayDeque<>();
        queue.add(coord);

        boolean[][] visited = new boolean[image.length][image[0].length];
        visited[sr][sc] = true;

        while (!queue.isEmpty()){

            Coordinate grid = queue.pop();
            for (Coordinate neighbour: getNeighbours(image, grid)) {
                if (visited[neighbour.row][neighbour.col])
                    continue;

                if (image[neighbour.row][neighbour.col] == srcColor) {
                    image[neighbour.row][neighbour.col] = newColor;
                    queue.add(neighbour);
                    visited[neighbour.row][neighbour.col] = true;
                }
            }
        }
        return image;
    }

    public static void main(String[] a) {
        int[][] image = {{0,0,0},
                         {0,1,1}};

        FloodFill obj = new FloodFill();
        image = obj.floodFill(image, 1,1,1);
        for (int i=0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }
}
