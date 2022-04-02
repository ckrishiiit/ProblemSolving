package Backtracking;
import java.util.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LettersCombinationofAPhonenumber {

    private static final Map<Character, char[]> KEYBOARD =
            Map.of(
                    '2', "abc".toCharArray(),
                    '3', "def".toCharArray(),
                    '4', "ghi".toCharArray(),
                    '5', "jkl".toCharArray(),
                    '6', "mno".toCharArray(),
                    '7', "pqrs".toCharArray(),
                    '8', "tuv".toCharArray(),
                    '9', "wxyz".toCharArray()
               );

   private static void dfs(StringBuilder path,
                           List<String> res, char[] digits){
       if (path.length() == digits.length) {
           res.add(path.toString());
           return;
       }

       char next_digit = digits[path.length()];
       for (char letter : KEYBOARD.get(next_digit)){
           path.append(letter);
           dfs(path, res, digits);
           path.deleteCharAt(path.length()-1);
       }
   }

    public List<String> letterCombination(String digits){
        List<String> res = new ArrayList<>();
        dfs(new StringBuilder(), res, digits.toCharArray());
        return res;
    }

    public static void main(String[] a){
        LettersCombinationofAPhonenumber obj = new LettersCombinationofAPhonenumber();
        List<String> res = obj.letterCombination("79");
        for (String s : res)
            System.out.println(s);
    }
}
