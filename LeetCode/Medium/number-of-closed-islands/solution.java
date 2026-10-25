class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int col = grid[0].length;
        for (int r = 0; r<rows; r++){
            if (grid[r][0] == 0) dfssink(grid, r, 0, rows, col);
            if (grid[r][col-1]==0) dfssink (grid, r, col-1, rows, col);
        }
        for (int c = 0; c<rows; c++){
            if (grid[0][c] == 0) dfssink(grid, 0, c, rows, col);
            if (grid[rows-1][c]==0) dfssink (grid, rows-1, c, rows, col);
        }
        int closedcount = 0;
        for (int r=1; r<rows-1; r++){
            for (int c=1; c<col-1; c++){
                if(grid[r][c]==0){
                    closedcount++;
                    dfssink (grid, r, c, rows, col);
                }
            }
        }
        return closedcount;
    }
    private void dfssink(int[][] grid, int r, int c, int rows, int cols) {
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 1) return;
        grid[r][c]=1;
        dfssink(grid, r + 1, c, rows, cols);
        dfssink(grid, r - 1, c, rows, cols);
        dfssink(grid, r, c + 1, rows, cols);
        dfssink(grid, r, c - 1, rows, cols);
    }
}