class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        if ((m+n-1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m-1][n-1]== '(') return false;
        int maxbalance = (m+n)/2;
        visited = new boolean[m][n][maxbalance+1];
        return dfs(0,0,1);
    }
    private boolean dfs(int r, int c, int balance){
        if(r==m-1 & c==n-1) return balance==0;
        if(visited[r][c][balance]) return false;
        visited[r][c][balance]=true;
        if (r+1<m){
            int nextbalance = balance + (grid[r+1][c]=='(' ? 1 : -1);
            int remainingstep = (m-1-(r+1))+(n-1-c);
            if (nextbalance >= 0 && nextbalance <= remainingstep){
                if (dfs(r+1,c,nextbalance)) return true;
            }
        }
        if (c+1<m){
            int nextbalance = balance + (grid[r][c+1]=='(' ? 1 : -1);
            int remainingstep = (m-1-r)+(n-1-(c+1));
            if (nextbalance >= 0 && nextbalance <= remainingstep){
                if (dfs(r,c+1,nextbalance)) return true;
            }
        }
        return false;
    }
}