package Graphs;
import java.util.*;

/**
 * https://leetcode.com/problems/sliding-puzzle/
 */
public class SlidingPuzzle {

    private static final int[][] DIRECTIONS =  { {1,0}, {0,1}, {0,-1}, {-1,0} };
    private static int target = 123450;


    public static ArrayList<List<Integer>> deserialize(int state) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        for (int i = 1; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                int exponent = i * 3 + j;
                int digit = state / (int) (Math.round(Math.pow(10, exponent))) % 10;
                result.get(1 - i).add(digit);
            }
        }
        return result;
    }

    private static int serialize(List<List<Integer>> initPos) {

        int total = 0;
        for (List<Integer> list : initPos) {
            for (Integer n : list){
                total *= 10;
                total += n;
            }
        }
        return total;
    }

    public static int numSteps(List<List<Integer>> initPos) {


        int initState = serialize(initPos);
        if (initState == target)
            return 0;


        Map<Integer, Integer> movesMap = new HashMap<>();
        Deque<Integer> movesQueue = new ArrayDeque<>();
        movesQueue.add(initState);
        movesMap.put(initState, 1);

        while (!movesQueue.isEmpty()) {

            int topState = movesQueue.poll();
            ArrayList<List<Integer>> topPosition = deserialize(topState);

            int row = 0, col = 0;
            for (int i=0; i < 2; i++) {
                for (int j=0; j < 3; j++) {
                    if (topPosition.get(i).get(j) == 0) {
                        row = i;
                        col = j;
                    }
                }
            }

            for (int[] dir : DIRECTIONS) {
                int newRow = dir[0] + row;
                int newCol = dir[1] + col;

                if (newRow >= 0 && newRow < 2 && newCol >= 0 && newCol < 3) {
                    ArrayList<List<Integer>> newPosition = deserialize(topState);
                    newPosition.get(row).set(col, topPosition.get(newRow).get(newCol));
                    newPosition.get(newRow).set(newCol, topPosition.get(row).get(col));

                    int newState = serialize(newPosition);
                    if (!movesMap.containsKey(newState)) {

                        if (newState == target) {
                            return movesMap.get(topState) + 1;
                        }
                        movesMap.put(newState, movesMap.get(topState) + 1);
                        movesQueue.add(newState);

                    }

                }
            }
        }
        return -1;
    }
    public static void main(String[] a) {

    }
}

