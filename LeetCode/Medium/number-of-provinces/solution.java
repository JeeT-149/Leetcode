class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int province = 0;
        for (int i = 0;i<n;i++){
            if (!visited[i]){
                province++;
                dfs(isConnected, visited, i);
            }
        }
        return province;
    }
    private void dfs(int[][] isConnected, boolean[] visited, int city){
        visited[city]=true;
        for (int neighbour=0; neighbour<isConnected.length;neighbour++){
            if(isConnected[city][neighbour] == 1 && !visited[neighbour]){
                dfs(isConnected, visited, neighbour);
            }
        }
    }
}