package Graphs;
import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {

    private static final char[] ALPHABETS =  new char[26];
    static {

        for (int i=0; i < 26; i++) {
            ALPHABETS[i] = (char) ('a' + i);
        }
    }

    public static int wordLadder(String begin, String end, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        Deque<String> queue = new ArrayDeque<>();
        queue.add(begin);
        int distance = 0;

        while (!queue.isEmpty()){

            int n = queue.size();
            distance++;

            for (int i=0; i < n; i++) {

                String word = queue.poll();

                for (int j=0; j < word.length(); j++) {

                    for (char ch : ALPHABETS) {

                        StringBuilder sb = new StringBuilder(word.length());
                        sb.append(word.substring(0,j));
                        sb.append(ch);
                        sb.append(word.substring(j+1));
                        String newWord = sb.toString();

                        if (!words.contains(newWord)){
                            continue;
                        }

                        if (newWord.equals(end))
                            return distance;

                        queue.add(newWord);
                        words.remove(word);
                    }
                }
            }
        }
        return 0;
    }
}
