class Solution {
    int[] head, to, next, cost;
    int edgeindex = 0;
    public int minReorder(int n, int[][] connections) {
        head = new int[n];
        Arrays.fill(head,-1);
        int m = connections.length;
        to = new int[m*2];
        next = new int[m * 2];
        cost = new int[m * 2];
        for (int[] conn:connections){
            addedge(conn[0],conn[1],1);
            addedge(conn[1],conn[0],0);
        }
        return dfs(0,-1);
    }
    private void addedge(int u, int v, int c){
        to[edgeindex]=v;
        cost[edgeindex]=c;
        next[edgeindex]=head[u];
        head[u]=edgeindex++;
    }
    private int dfs(int node, int parent) {
        int totalReversals = 0;        
        for (int i = head[node]; i != -1; i = next[i]) {
            int neighbor = to[i];
            if (neighbor != parent) {
                totalReversals += cost[i] + dfs(neighbor, node);
            }
        }
        return totalReversals;
    }
}