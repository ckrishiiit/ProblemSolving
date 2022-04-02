package Graphs;
import java.util.*;
/**
 * https://leetcode.com/problems/find-all-people-with-secret/
 */
public class FindAllPeopleWithSecret {

    class DSU {
        int[] parent;
        int[] rank;
        public DSU(int N) {
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
        public void reset(int x) {
            parent[x] = x;
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        DSU dsu = new DSU(n);
        dsu.union(0, firstPerson);
        Map<Integer, List<Integer>> sameTimeMeeting = new TreeMap<>();
        int N = meetings.length;
        for (int i = 0; i < N; i++)
            sameTimeMeeting.computeIfAbsent(meetings[i][2], x -> new ArrayList<>()).add(i);
        for (int time : sameTimeMeeting.keySet()) {
            Set<Integer> pool = new HashSet<>();
            for (int id : sameTimeMeeting.get(time)) {
                int a = meetings[id][0];
                int b = meetings[id][1];
                dsu.union(a, b);
                pool.add(a);
                pool.add(b);
            }
            for (int i : pool) if (dsu.find(0) != dsu.find(i)) dsu.reset(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (dsu.find(i) == dsu.find(0)) res.add(i);
        return res;
    }

    public static void main(String[] a) {
        int[][] meetings = { {1,4,5}, {2,4,2},{1,4,10}, {2,4,12}};
        int firstPerson = 1;
        FindAllPeopleWithSecret obj = new FindAllPeopleWithSecret();
        List<Integer> res = obj.findAllPeople(6, meetings, firstPerson);
        for (int n: res)
            System.out.print(n +" ");
    }
}
