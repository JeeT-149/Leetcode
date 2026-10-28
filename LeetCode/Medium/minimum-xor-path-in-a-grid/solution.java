import java.util.Arrays;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] dp = new boolean[n][1024];
        
        boolean[] nextState = new boolean[1024];
        
        dp[0][0] = true;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue;
                
                int val = grid[r][c];
                
                Arrays.fill(nextState, false);
                
                for (int x = 0; x < 1024; x++) {
                    boolean reachableFromTop = (r > 0 && dp[c][x]);
                    boolean reachableFromLeft = (c > 0 && dp[c - 1][x]);
                    
                    if (reachableFromTop || reachableFromLeft) {
                        nextState[x ^ val] = true;
                    }
                }
                
                System.arraycopy(nextState, 0, dp[c], 0, 1024);
            }
        }
        
        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) {
                return x;
            }
        }
        
        return 0;
    }
}