package Graphs;

public class YoungestCommonAncestor {

    private static int depth(AncestralTree topAncestor, AncestralTree node) {
        int depth = 0;
        while (node.ancestor != null){
            depth++;
            node = node.ancestor;
        }
        return  depth;
    }

    public static AncestralTree getYoungestCommonAncestor(
            AncestralTree topAncestor, AncestralTree p, AncestralTree q) {

        int depthP = depth(topAncestor, p);
        int depthQ = depth(topAncestor, q);

        if (depthQ > depthP){
            return getYoungestCommonAncestor(topAncestor, q, p);
        }

        int diff = depthP - depthQ;

        while (diff-- > 0) {
            p = p.ancestor;
        }

        while (p != q) {
            p = p.ancestor;
            q = q.ancestor;
        }
        return p;
    }

    static class AncestralTree {
        public char name;
        public AncestralTree ancestor;

        AncestralTree(char name) {
            this.name = name;
            this.ancestor = null;
        }

        // This method is for testing only.
        void addAsAncestor(AncestralTree[] descendants) {
            for (AncestralTree descendant : descendants) {
                descendant.ancestor = this;
            }
        }
    }
}
