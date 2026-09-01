import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startr = -1; 
        int startc = -1;
        int littercount = 0;
        int[][] litterid = new int[m][n];
        
        for (int r = 0; r < m; r++){
            for (int c = 0; c < n; c++){
                char ch = classroom[r].charAt(c);
                if (ch == 'S'){
                    startr = r;
                    startc = c;
                }
                else if (ch == 'L'){
                    litterid[r][c] = littercount++;
                }
            }
        }
        
        int targetmask = (1 << littercount) - 1;
        if (targetmask == 0) return 0;
        
        int[][][] visited = new int[m][n][1 << littercount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startr, startc, 0, energy});
        visited[startr][startc][0] = energy;
        
        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while(!queue.isEmpty()){
            int levelsize = queue.size();
            for (int i = 0; i < levelsize; i++){
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int currentenergy = curr[3];
                
                if(currentenergy == 0) continue;
                
                for (int[] d : dirs){
                    int nr = r + d[0];
                    int nc = c + d[1];
                    
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X'){
                        int nextenergy = currentenergy - 1;
                        int nextmask = mask;
                        char nextcell = classroom[nr].charAt(nc);
                        
                        if (nextcell == 'R'){
                            nextenergy = energy;
                        }
                        else if (nextcell == 'L'){
                            nextmask |= (1 << litterid[nr][nc]);
                        }
                        
                        if(nextmask == targetmask){
                            return moves + 1;
                        }
                        
                        if (nextenergy > visited[nr][nc][nextmask]){
                            visited[nr][nc][nextmask] = nextenergy;
                            queue.offer(new int[]{nr, nc, nextmask, nextenergy});
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}