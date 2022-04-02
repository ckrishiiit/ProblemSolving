package Graphs;
import java.util.*;

/**
 * https://leetcode.com/problems/swim-in-rising-water/
 */
public class SwimInRiseWater {

    private static int[][] DIRECTIONS = { {1,0}, {0,1}, {0,-1}, {-1,0}};
    public static int swimInWater(int[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        int N = grid.length;

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>( (a,b) ->
                grid[a/N][a%N] - grid[b/N][b%N]);

        visited.add(0);
        pq.offer(0);
        int time = 0;

        while (!pq.isEmpty()){
            int gridNum = pq.poll();

            int currRow = gridNum / N;
            int currCol = gridNum % N;

            time = Math.max(time, grid[currRow][currCol]);
            if (currRow == N-1 && currCol == N-1)
                return time;

            for (int[] dir : DIRECTIONS){
                int newRow = dir[0] + currRow;
                int newCol = dir[1] + currCol;

                int currGrid = newRow * N + newCol;

                if (newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length
                        || visited.contains(currGrid)) {
                    continue;
                }

                pq.offer(currGrid);
                visited.add(currGrid);
            }
        }
        return -1;
    }

    public static void main(String[] a){
        int[][] grid = {
                {0,1,2,3,4},
                {24,23,22,21,5},
                {12,13,14,15,16},
                {11,17,18,19,20},
                {10,9,8,7,6}
           };

       int max =  SwimInRiseWater.swimInWater(grid);
       System.out.println(max);
    }
}
