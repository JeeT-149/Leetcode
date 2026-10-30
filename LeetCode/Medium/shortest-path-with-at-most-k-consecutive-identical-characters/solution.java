import java.util.Arrays;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) return 0;
        
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int m = edges.length;
        int[] to = new int[m];
        int[] weight = new int[m];
        int[] nxt = new int[m];
        
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            to[i] = edges[i][1];
            weight[i] = edges[i][2];
            nxt[i] = head[u];
            head[u] = i;
        }
        
        int[] hw = new int[128];
        int[] hu = new int[128];
        int[] hc = new int[128];
        int sz = 0;
        
        hw[0] = 0; 
        hu[0] = 0; 
        hc[0] = 1; 
        sz = 1;
        
        int[] minCount = new int[n];
        Arrays.fill(minCount, Integer.MAX_VALUE);
        
        byte[] chars = labels.getBytes();
        
        while (sz > 0) {
            int w = hw[0];
            int u = hu[0];
            int c = hc[0];
            
            sz--;
            int lastW = hw[sz];
            int lastU = hu[sz];
            int lastC = hc[sz];
            
            int i = 0;
            while ((i << 1) + 1 < sz) {
                int left = (i << 1) + 1;
                int right = left + 1;
                int best = left;
                
                if (right < sz && hw[right] < hw[left]) {
                    best = right;
                }
                if (lastW <= hw[best]) {
                    break;
                }
                
                hw[i] = hw[best];
                hu[i] = hu[best];
                hc[i] = hc[best];
                i = best;
            }
            hw[i] = lastW;
            hu[i] = lastU;
            hc[i] = lastC;
            
            if (c >= minCount[u]) continue;
            minCount[u] = c;
            
            if (u == n - 1) return w;
            
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                int nextC = (chars[u] == chars[v]) ? c + 1 : 1;
                
                if (nextC > k || nextC >= minCount[v]) continue;
                
                int nextW = w + weight[e];
                
                if (sz == hw.length) {
                    hw = Arrays.copyOf(hw, sz << 1);
                    hu = Arrays.copyOf(hu, sz << 1);
                    hc = Arrays.copyOf(hc, sz << 1);
                }
                
                int idx = sz++;
                while (idx > 0) {
                    int p = (idx - 1) >> 1;
                    if (hw[p] <= nextW) break;
                    hw[idx] = hw[p];
                    hu[idx] = hu[p];
                    hc[idx] = hc[p];
                    idx = p;
                }
                hw[idx] = nextW;
                hu[idx] = v;
                hc[idx] = nextC;
            }
        }
        
        return -1;
    }
}