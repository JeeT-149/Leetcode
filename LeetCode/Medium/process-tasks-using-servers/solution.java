class Solution {
    private long[] freeHeap;
    private int freeSz = 0;
    private long[] busyHeap;
    private int busySz = 0;

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        
        freeHeap = new long[n];
        busyHeap = new long[n];
        
        for (int i = 0; i < n; i++) {
            pushFree(((long) servers[i] << 32) | i);
        }
        
        int[] ans = new int[m];
        long currentTime = 0;
        
        for (int j = 0; j < m; j++) {
            if (currentTime < j) {
                currentTime = j;
            }
            
            if (freeSz == 0 && busySz > 0) {
                long nextTime = busyHeap[0] >>> 18;
                if (currentTime < nextTime) {
                    currentTime = nextTime;
                }
            }
            
            while (busySz > 0 && (busyHeap[0] >>> 18) <= currentTime) {
                long finished = popBusy();
                int idx = (int) (finished & 0x3FFFFL);
                pushFree(((long) servers[idx] << 32) | idx);
            }
            
            long bestFree = popFree();
            int idx = (int) (bestFree & 0xFFFFFFFFL);
            ans[j] = idx;
            
            pushBusy(((currentTime + tasks[j]) << 18) | idx);
        }
        
        return ans;
    }
    
    private void pushFree(long val) {
        int curr = freeSz++;
        while (curr > 0) {
            int p = (curr - 1) >> 1;
            if (freeHeap[p] <= val) break;
            freeHeap[curr] = freeHeap[p];
            curr = p;
        }
        freeHeap[curr] = val;
    }
    
    private long popFree() {
        long res = freeHeap[0];
        long val = freeHeap[--freeSz];
        int curr = 0;
        while ((curr << 1) + 1 < freeSz) {
            int child = (curr << 1) + 1;
            int right = child + 1;
            if (right < freeSz && freeHeap[right] < freeHeap[child]) {
                child = right;
            }
            if (val <= freeHeap[child]) break;
            freeHeap[curr] = freeHeap[child];
            curr = child;
        }
        freeHeap[curr] = val;
        return res;
    }
    
    private void pushBusy(long val) {
        int curr = busySz++;
        while (curr > 0) {
            int p = (curr - 1) >> 1;
            if (busyHeap[p] <= val) break;
            busyHeap[curr] = busyHeap[p];
            curr = p;
        }
        busyHeap[curr] = val;
    }
    
    private long popBusy() {
        long res = busyHeap[0];
        long val = busyHeap[--busySz];
        int curr = 0;
        while ((curr << 1) + 1 < busySz) {
            int child = (curr << 1) + 1;
            int right = child + 1;
            if (right < busySz && busyHeap[right] < busyHeap[child]) {
                child = right;
            }
            if (val <= busyHeap[child]) break;
            busyHeap[curr] = busyHeap[child];
            curr = child;
        }
        busyHeap[curr] = val;
        return res;
    }
}