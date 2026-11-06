import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int numLogs = logs.length;
        long[] flatLogs = new long[numLogs];
        
        // Pack logs: upper 32 bits = time, lower 32 bits = server_id
        for (int i = 0; i < numLogs; i++) {
            flatLogs[i] = ((long) logs[i][1] << 32) | logs[i][0];
        }
        Arrays.sort(flatLogs);
        
        int qLen = queries.length;
        long[] flatQueries = new long[qLen];
        
        // Pack queries: upper 32 bits = query time, lower 32 bits = original index
        for (int i = 0; i < qLen; i++) {
            flatQueries[i] = ((long) queries[i] << 32) | i;
        }
        Arrays.sort(flatQueries);
        
        int[] ans = new int[qLen];
        int[] counts = new int[n + 1];
        int uniqueActive = 0;
        
        int left = 0;
        int right = 0;
        
        for (int i = 0; i < qLen; i++) {
            long qData = flatQueries[i];
            int qTime = (int) (qData >>> 32);
            int qIdx = (int) (qData & 0xFFFFFFFFL);
            
            int minTime = qTime - x;
            
            // Expand window right to include incoming requests
            while (right < numLogs && (int) (flatLogs[right] >>> 32) <= qTime) {
                int serverId = (int) (flatLogs[right] & 0xFFFFFFFFL);
                if (counts[serverId] == 0) {
                    uniqueActive++;
                }
                counts[serverId]++;
                right++;
            }
            
            // Shrink window left to drop expired requests
            while (left < right && (int) (flatLogs[left] >>> 32) < minTime) {
                int serverId = (int) (flatLogs[left] & 0xFFFFFFFFL);
                counts[serverId]--;
                if (counts[serverId] == 0) {
                    uniqueActive--;
                }
                left++;
            }
            
            ans[qIdx] = n - uniqueActive;
        }
        
        return ans;
    }
}