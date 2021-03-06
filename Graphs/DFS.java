package Graphs;
import java.util.*;

public class DFS {

        // Do not edit the class below except
        // for the depthFirstSearch method.
        // Feel free to add new properties
        // and methods to the class.
        static class Node {
            String name;
            List<Node> children = new ArrayList<Node>();

            public Node(String name) {
                this.name = name;
            }

            public List<String> depthFirstSearch(List<String> arr) {
                arr.add(this.name);
                for (int i=0; i < children.size(); i++) {
                    children.get(i).depthFirstSearch(arr);
                }
                return arr;
            }

            public Node addChild(String name) {
                Node child = new Node(name);
                children.add(child);
                return this;
            }
        }
}
