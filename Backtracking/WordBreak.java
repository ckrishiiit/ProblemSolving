package Backtracking;
import java.util.*;
/**
 * https://leetcode.com/problems/word-break/
 */
public class WordBreak {

    private static boolean helper(String s, Set<String> set, int index,
                                  Boolean[] memo){

        if (index == s.length())
            return true;

        if (memo[index] != null)
            return memo[index];

        for (int j=index+1; j <= s.length(); j++) {
          String subStr = s.substring(index, j);
          if (set.contains(subStr) && helper(s, set, j, memo))
              return memo[index] = true;
        }
        memo[index] = false;
        return memo[index];
    }
    public static boolean wordBreak(String s, List<String> words) {

        Set<String> set = new HashSet<>(words);
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, set, 0, memo);
    }

    public static void main(String[] a) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("al");
        list.add("alg");
        list.add("algo");
        list.add("monster");
        boolean res = WordBreak.wordBreak("algomonster", list);
        System.out.println(res);
    }
}
