package Graphs;
import java.util.*;
import java.util.stream.Collectors;

public class OpenLock {

    public static Map<Character, Character> nextDigit = Map.of(
            '0', '1',
            '1', '2',
            '2', '3',
            '3', '4',
            '4', '5',
            '5', '6',
            '6', '7',
            '7', '8',
            '8', '9',
            '9', '0'
    );

    public static Map<Character, Character> prevDigit =
            nextDigit.entrySet().stream().collect(
                    Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)
            );

    public int openLock(List<String> deadends, String target) {
        if (target.equals("0000"))
            return 0;
        HashSet<String> trappedComboSet = new HashSet<>(deadends);
        HashMap<String, Integer> bfsMap = new HashMap<>();
        bfsMap.put("0000", 0);

        Deque<String> queue = new ArrayDeque<>();
        queue.offer("0000");

        while (!queue.isEmpty()) {
            String top = queue.poll();
            for (int i=0; i < 4; i++) {
                char ch = top.charAt(i);
                String newCombo = top.substring(0,i)
                        .concat(String.valueOf(nextDigit.get(ch))
                        .concat(top.substring(i+1))
                );
                if (!trappedComboSet.contains(newCombo)
                && !bfsMap.containsKey(newCombo)) {
                    queue.offer(newCombo);
                    bfsMap.put(newCombo, bfsMap.get(top) + 1);
                    if (newCombo.equals(target))
                        return bfsMap.get(newCombo);
                }
                newCombo = top.substring(0,i)
                        .concat(String.valueOf(prevDigit.get(ch))
                                .concat(top.substring(i+1))
                        );
                if (!trappedComboSet.contains(newCombo)
                        && !bfsMap.containsKey(newCombo)) {
                    queue.offer(newCombo);
                    bfsMap.put(newCombo, bfsMap.get(top) + 1);
                    if (newCombo.equals(target))
                        return bfsMap.get(newCombo);
                }
            }
        }
        return -1;
     }
}
