package Graphs;

public class SingleCycleCheck {

    public static boolean hasSingleCycle(int[] arr) {
        int numElementVisited = 0;
        int currIdx = 0;

        while (numElementVisited < arr.length) {

            if (currIdx == 0 && numElementVisited > 0)
                return false;

            numElementVisited++;
            currIdx = getNextIdx(arr, currIdx);
        }
        return currIdx == 0;
    }

    private static int getNextIdx(int[] arr, int currIdx) {
        int jump = arr[currIdx];
        int nextIdx = (jump + currIdx) % arr.length;
        return nextIdx >= 0 ? nextIdx : nextIdx + arr.length;
    }

    public static void main(String[] a) {
        int[] arr = {2,3,1,-4,-4,2};
        boolean cycle = SingleCycleCheck.hasSingleCycle(arr);
        System.out.println(cycle);
    }
}
