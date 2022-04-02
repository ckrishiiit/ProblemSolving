package Backtracking;
import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/permutations/
 */
public class Permutations {

    List<List<Integer>> res;
    private void helper(List<Integer> list, int index){
          if (index == list.size()){
              res.add(new ArrayList<>(list));
          }else{
              for (int j=index; j < list.size(); j++) {
                  Collections.swap(list, j, index);
                  helper(list, index+1);
                  Collections.swap(list, j, index);
              }
          }
    }

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        List<Integer> list = Arrays.stream(nums).boxed()
                                                .collect(Collectors.toList());

        helper(list, 0);
        return res;
    }

    public static void main(String[] a){

        Permutations obj = new Permutations();
        int[] nums = {1,2,3};
        List<List<Integer>> list = obj.permute(nums);
        for (List<Integer> l : list) {
            for (int no: l) {
                System.out.print(no +" ");
            }
            System.out.println();
        }
    }
}
