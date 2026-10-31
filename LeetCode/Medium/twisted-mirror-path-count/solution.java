class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m==1 && n==1) return 1;
        grid[0][0]=0;
        int[] dpdown = new int[n];
        int[] dpright = new int[n];
        int MOD = 1000000007;
        for (int r = m-1; r>= 0; r--){
            for (int c = n-1; c>= 0; c--){
                if (r==m-1 && c==n-1){
                    dpdown[c]=1;
                    dpright[c]=1;
                    continue;
                }
                int rightval = (c+1<n)?dpright[c+1]:0;
                int downval = (r+1<m)?dpdown[c]:0;
                if (grid[r][c]==0){
                    int ways = rightval+downval;
                    if (ways>MOD) ways -= MOD;
                    dpright[c]=ways;
                    dpdown[c]=ways;
                }
                else{
                    dpright[c]=downval;
                    dpdown[c]=rightval;
                }
            }
        }
        return dpright[0];
    }
}