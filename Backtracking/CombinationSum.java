package Backtracking;
import java.util.*;

/**
 * https://leetcode.com/problems/combination-sum/
 */
public class CombinationSum {

    List<List<Integer>> res;
    private void helper(int[] nums, int remaining, int index, List<Integer> path) {
        if (remaining == 0) {
            res.add(new ArrayList<>(path));
        }
        else if (remaining < 0)
            return;

        for (int i=index; i < nums.length; i++) {
            path.add(nums[i]);
            helper(nums, remaining - nums[i], i, path);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] nums, int target){
        res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper(nums, target, 0, path);
        return res;
    }

    public static void main(String[] a) {
        int[] nums = {2,3,5};
        int target = 8;
        List<List<Integer>> arr = new CombinationSum().combinationSum(nums, target);
        for (List<Integer> l : arr) {
            System.out.println();
            for (Integer no: l)
                System.out.print(no +" ");
        }
    }
}
