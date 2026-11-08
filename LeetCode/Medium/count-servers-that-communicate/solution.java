class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] rowCount = new int[m];
        int[] colCount = new int[n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }
        
        int communicators = 0;
        
        for (int i = 0; i < m; i++) {
            if (rowCount[i] > 1) {
                communicators += rowCount[i];
            } else if (rowCount[i] == 1) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        if (colCount[j] > 1) {
                            communicators++;
                        }
                        break;
                    }
                }
            }
        }
        
        return communicators;
    }
}