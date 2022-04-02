package Backtracking;
import java.util.*;

/***
 * Time Pow(2,n)
 * https://leetcode.com/problems/palindrome-partitioning/
 */
public class PalindromePartitioning {

    private static boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    private static void helper(String s, int start, List<List<String>> res ,
                              List<String> list) {

        if (start == s.length()){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int j = start+1; j <= s.length(); j++) {
            String subStr = s.substring(start, j);
            if (isPalindrome(subStr)){
                list.add(subStr);
                helper(s, j, res, list);
                list.remove(list.size()-1);
            }
        }
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        helper(s, 0, res, list);
        return res;
    }

    public static void main(String[] a) {
        List<List<String>> list = PalindromePartitioning.partition("aabc");
        for (List<String> l :list) {
            for(String s: l){
                System.out.print(s +" ");
            }
            System.out.println();
        }
    }
}
