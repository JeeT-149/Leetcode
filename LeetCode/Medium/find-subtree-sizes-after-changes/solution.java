import java.util.Arrays;

class Solution {
    private int[] head;
    private int[] next;
    private int[] parent;
    private int[] ans;
    private int[] lastSeen;
    private char[] chars;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.parent = parent;
        this.chars = s.toCharArray();
        this.head = new int[n];
        this.next = new int[n];
        this.ans = new int[n];
        this.lastSeen = new int[26];

        Arrays.fill(head, -1);
        Arrays.fill(lastSeen, -1);

        for (int i = 1; i < n; i++) {
            int p = parent[i];
            next[i] = head[p];
            head[p] = i;
        }

        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        int c = chars[u] - 'a';
        int prevAncestor = lastSeen[c];
        int newParent = prevAncestor != -1 ? prevAncestor : parent[u];

        lastSeen[c] = u;
        ans[u] = 1;

        for (int v = head[u]; v != -1; v = next[v]) {
            dfs(v);
        }

        lastSeen[c] = prevAncestor;

        if (newParent != -1) {
            ans[newParent] += ans[u];
        }
    }
}