class Solution {
    public int countIslands(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int validIslands = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] > 0) {
                    long islandSum = getIslandSumAndSink(grid, r, c, rows, cols);                    
                    if (islandSum % k == 0) {
                        validIslands++;
                    }
                }
            }
        }
        
        return validIslands;
    }
    
    private int getIslandSumAndSink(int[][] grid, int r, int c, int rows, int cols) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 0) {
            return 0L;
        }
        
        int val = grid[r][c];
        
        grid[r][c] = 0;
        
        long totalSum = val;
        totalSum += getIslandSumAndSink(grid, r + 1, c, rows, cols);
        totalSum += getIslandSumAndSink(grid, r - 1, c, rows, cols);
        totalSum += getIslandSumAndSink(grid, r, c + 1, rows, cols);
        totalSum += getIslandSumAndSink(grid, r, c - 1, rows, cols);
        
        return totalSum;
    }
}