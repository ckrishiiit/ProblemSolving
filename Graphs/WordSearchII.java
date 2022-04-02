package Graphs;
import java.util.*;

/**
 * https://leetcode.com/problems/word-search-ii/
 * step1: Build A Trie for all input words.
 * step2: Iterate over the board, if any char matches
 *        TrieNode (root) char then explore the matrix
 *        if word exists or not.
 */
public class WordSearchII {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    static TrieNode root = null;
    private static int[][] DIRECTIONS = {{1,0}, {0,1}, {0,-1}, {-1,0},
            {-1,-1},{1,1},{1,-1},{-1,1}};

    public WordSearchII() {
        this.root = new TrieNode();
    }

    private static void buildTrie(String word) {
        TrieNode curr = root;
        for (char ch: word.toCharArray()) {
            TrieNode node = curr.children.get(ch);
            if (node == null){
                node = new TrieNode();
                curr.children.put(ch, node);
            }
            curr = node;
        }
        curr.word = word;
    }

    public static List<String> boggleBoard(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        for (String word: words)
            buildTrie(word);

        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++) {
                char ch = board[row][col];
                if (root.children.containsKey(ch)) {
                    dfs(board, row, col, res, root);
                }
            }
        }
        return res;
    }

    private static void dfs(char[][] board, int row, int col,
                     List<String> res, TrieNode parent) {

        char letter = board[row][col];
        TrieNode curr = parent.children.get(letter);

        if (curr.word != null){
            res.add(curr.word);
            curr.word = null;
        }

        board[row][col] = ' ' ;
        for (int[] dir : DIRECTIONS) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;

            if (newRow < 0 || newRow >= board.length || newCol < 0
            || newCol >= board[0].length) {
                continue;
            }

            if (curr.children.containsKey(board[newRow][newCol])) {
                dfs(board, newRow, newCol, res, curr);
            }
        }
        board[row][col] = letter;
     }

     public static void main(String[] a ){
        String[] word = {"agmsy", "agmsytojed", "agmsytojedinhcbgl", "agmsytojedinhcbfl"};
        char[][] board =  {
                {'a', 'b', 'c', 'd', 'e'},
               {'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o'},
                {'p', 'q', 'r', 's', 't'},
                {'u', 'v', 'w', 'x', 'y'}
        };

         List<String> words = new WordSearchII().boggleBoard(board, word);
         for (String s: words)
             System.out.println(s);
     }
}


