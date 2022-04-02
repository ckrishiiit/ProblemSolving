package Graphs;
import java.util.*;
import java.util.stream.Collectors;


public class OpenTheLock {

    private static Map<Character, Character> nextDigitMap =
            Map.of(
                     '0','1',
                     '1','2',
                     '2','3',
                     '3','4',
                     '4','5',
                     '5','6',
                     '6','7',
                     '7','8',
                     '8','9',
                    '9','0'
                    );

    private static Map<Character, Character> prevDigitMap =
            nextDigitMap.entrySet().stream().collect(
                    Collectors.toMap(Map.Entry:: getValue, Map.Entry:: getKey));

    public static int numSteps(String combo, List<String> trappedCombos) {
        if (combo.equals("0000"))
            return 0;
        Deque<String> queue = new ArrayDeque<>();
        queue.add("0000");

        Map<String, Integer> map = new HashMap<>();
        map.put("0000", 0);

        Set<String> set = new HashSet<>(trappedCombos);

        while (!queue.isEmpty()){
          String top = queue.poll();
          for (int i=0; i < 4; i++) {
              String prevWord = top.substring(0,i);
              String nextDigit = String.valueOf(nextDigitMap.get(top.charAt(i)));
              String nextWord = top.substring(i+1);
              String newCombo = prevWord.concat(nextDigit).concat(nextWord);

              if (!set.contains(newCombo) && !map.containsKey(newCombo)){
                  queue.offer(newCombo);
                  map.put(newCombo, map.get(top) + 1);

                  if (newCombo.equals(combo))
                      return map.get(newCombo);
              }

              String prevDigit = String.valueOf(prevDigitMap.get(top.charAt(i)));
              newCombo = prevWord.concat(prevDigit).concat(nextWord);
              if (!set.contains(newCombo) && !map.containsKey(newCombo)) {
                  queue.offer(newCombo);
                  map.put(newCombo, map.get(top) + 1);
                  if (newCombo.equals(combo))
                      return map.get(newCombo);
              }
          }
        }
        return -1;
    }

    public static void main(String[] a) {
        List<String> trappedCombos = new ArrayList<>(Arrays.asList("8888"));
        String combo = "0009";
        int count = OpenTheLock.numSteps(combo, trappedCombos);
        System.out.println("count= "+count);
    }
}
