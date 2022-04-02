package Backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/permutations-ii/
 * ex: [1,1,2]
 * o/p: [1,1,2], [1,2,1], [2,1,1]
 */
public class PermutationsII {

    List<List<Integer>> res;
    int len = 0;

    private void helper(Map<Integer, Integer> count, List<Integer> perm) {

        if (perm.size() == len) {
            res.add(new ArrayList<>(perm));
        }else {
            for(int n: count.keySet()){
                if (count.get(n) > 0) {
                    perm.add(n);
                    count.put(n, count.get(n)-1);
                    helper(count, perm);
                    count.put(n, count.get(n)+1);
                    perm.remove(perm.size()-1);
                }
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {

        res = new ArrayList<>();
        len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int no: nums){
            map.merge(no, 1, Integer::sum);
        }

        List<Integer> perm = new ArrayList<>();
        helper(map, perm);
        return res;
    }

    public static void main(String[] a){

        PermutationsII obj = new PermutationsII();
        int[] nums = {1,1,2};
        List<List<Integer>> list = obj.permuteUnique(nums);
        for (List<Integer> l : list) {
            for (int no: l) {
                System.out.print(no +" ");
            }
            System.out.println();
        }
    }
}

