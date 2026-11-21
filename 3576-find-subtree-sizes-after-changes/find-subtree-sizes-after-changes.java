
class Solution {
    List<Integer>[] adj;
    List<Integer>[] newTree;
    int[] last;
    int[] ans;
    int[] newParent;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        this.s = s;

        adj = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        newParent = parent.clone();

        // Find nearest same-character ancestor
        dfs1(0);

        // Build modified tree
        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        ans = new int[n];

        // Calculate subtree sizes
        dfs2(0);

        return ans;
    }

    void dfs1(int node) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];

        if (old != -1) {
            newParent[node] = old;
        }

        last[ch] = node;

        for (int child : adj[node]) {
            dfs1(child);
        }

        // Backtracking
        last[ch] = old;
    }

    int dfs2(int node) {
        int size = 1;

        for (int child : newTree[node]) {
            size += dfs2(child);
        }

        ans[node] = size;

        return size;
    }
}
