class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue <int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{entrance[0],entrance[1],0});
        maze[entrance[0]][entrance[1]]='+';
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int steps = curr[2];
            for(int[] d : dirs){
                int nr = r+d[0];
                int nc = c+d[1];
                if (nr>=0 && nr<rows && nc >=0 && nc<cols && maze[nr][nc]=='.'){
                    if (nr == 0 || nr == rows - 1 || nc == 0 || nc == cols - 1){
                        return steps+1;
                    }
                    maze[nr][nc] = '+';
                    queue.offer(new int[]{nr, nc, steps + 1});
                }
            }
        }
        return -1;
    }
}