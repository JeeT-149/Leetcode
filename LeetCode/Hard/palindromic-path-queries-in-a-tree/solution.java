import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private int[] head, to, nxt;
    private int edgeCount;
    
    private int[] entry, exit, depth;
    private int[][] up;
    private int timer;
    
    private int[] bit; 
    private int[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        // Step 1: Pre-allocate primitive CSR graph to bypass object overhead
        head = new int[n];
        Arrays.fill(head, -1);
        to = new int[n * 2];
        nxt = new int[n * 2];
        edgeCount = 0;
        
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }

        // Pre-allocate tracking structures
        entry = new int[n];
        exit = new int[n];
        depth = new int[n];
        up = new int[n][18]; 
        chars = new int[n];
        
        for (int i = 0; i < n; i++) {
            chars[i] = 1 << (s.charAt(i) - 'a');
        }

        // Step 2: Flatten the tree and build Binary Lifting table
        timer = 1;
        dfs(0, 0, 0);

        // Step 3: Initialize the Fenwick Tree with the starting character masks
        bit = new int[n + 2];
        for (int i = 0; i < n; i++) {
            updateBit(entry[i], chars[i]);
            updateBit(exit[i] + 1, chars[i]);
        }

        List<Boolean> res = new ArrayList<>(queries.length);
        
        // Step 4: Process queries via hardware-level parsing
        for (String q : queries) {
            if (q.charAt(0) == 'u') { 
                // Parse "update u c"
                int u = 0;
                int i = 7;
                while (q.charAt(i) != ' ') {
                    u = u * 10 + (q.charAt(i) - '0');
                    i++;
                }
                int newMask = 1 << (q.charAt(i + 1) - 'a');
                
                // Diff captures the toggled bits. Apply range update to the subtree.
                int diff = chars[u] ^ newMask;
                chars[u] = newMask;
                updateBit(entry[u], diff);
                updateBit(exit[u] + 1, diff);
                
            } else { 
                // Parse "query u v"
                int u = 0;
                int i = 6;
                while (q.charAt(i) != ' ') {
                    u = u * 10 + (q.charAt(i) - '0');
                    i++;
                }
                i++;
                int v = 0;
                while (i < q.length()) {
                    v = v * 10 + (q.charAt(i) - '0');
                    i++;
                }

                // Calculate exact path parity using LCA cancellation
                int lca = getLca(u, v);
                int maskU = queryBit(entry[u]);
                int maskV = queryBit(entry[v]);
                int pathMask = maskU ^ maskV ^ chars[lca];

                // A valid palindrome has at most 1 bit set in its parity mask
                res.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        return res;
    }

    private void addEdge(int u, int v) {
        to[edgeCount] = v;
        nxt[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    private void dfs(int u, int p, int d) {
        entry[u] = timer++;
        depth[u] = d;
        up[u][0] = p;
        
        // Construct binary lifting table dynamically
        for (int i = 1; i < 18; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int e = head[u]; e != -1; e = nxt[e]) {
            int v = to[e];
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        exit[u] = timer - 1;
    }

    // Fenwick Tree (BIT) Point-Update (used structurally as a Range Update)
    private void updateBit(int idx, int val) {
        for (; idx < bit.length; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    // Fenwick Tree (BIT) Point-Query (evaluates the cumulative XOR to node)
    private int queryBit(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }

    // O(log N) Lowest Common Ancestor calculation
    private int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        
        // Level the depths
        for (int i = 17; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        
        // Climb identically until just below the LCA
        for (int i = 17; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        
        return up[u][0];
    }
}