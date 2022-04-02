package Backtracking;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {

    Map<Integer, Integer> memo = new HashMap<>();
    public int numDecodings(String s) {
        return helper(s, 0);
    }

    private int helper(String s, int index) {
        if (memo.containsKey(index))
            return memo.get(index);

        if (index == s.length())
            return 1;

        if (s.charAt(index) == '0')
            return 0;

        if (index == s.length() - 1)
            return 1;

        int ans = helper(s, index+1);
        if (Integer.parseInt(s.substring(index, index+2)) <= 26)
            ans += helper(s, index+2);
        memo.put(index, ans);
        return memo.get(index);
    }

    public static void main(String[] a){
       int ways = new DecodeWays().numDecodings("123");
       System.out.println(ways);
    }
}
