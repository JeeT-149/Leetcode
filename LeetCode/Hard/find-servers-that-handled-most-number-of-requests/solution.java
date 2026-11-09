import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] tree;
    private long[] busyHeap;
    private int busySz;

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        tree = new int[4 * k];
        build(1, 0, k - 1);
        
        int n = arrival.length;
        busyHeap = new long[Math.min(k, n) + 1];
        busySz = 0;
        
        int[] count = new int[k];
        
        for (int i = 0; i < n; i++) {
            int arr = arrival[i];
            int ld = load[i];
            
            while (busySz > 0 && (busyHeap[0] >>> 32) <= arr) {
                long finished = popBusy();
                int sId = (int) (finished & 0xFFFFFFFFL);
                update(1, 0, k - 1, sId, 1);
            }
            
            int target = i % k;
            int sId = queryPos(1, 0, k - 1, target);
            
            if (sId == -1) {
                sId = queryPos(1, 0, k - 1, 0);
            }
            
            if (sId != -1) {
                count[sId]++;
                update(1, 0, k - 1, sId, 0);
                pushBusy(((long) (arr + ld) << 32) | sId);
            }
        }
        
        int maxReq = 0;
        for (int i = 0; i < k; i++) {
            if (count[i] > maxReq) {
                maxReq = count[i];
            }
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxReq) {
                res.add(i);
            }
        }
        
        return res;
    }
    
    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = 1;
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
    
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
    
    private int queryPos(int node, int start, int end, int idx) {
        if (tree[node] == 0 || end < idx) {
            return -1;
        }
        if (start == end) {
            return start;
        }
        int mid = (start + end) / 2;
        int res = queryPos(2 * node, start, mid, idx);
        if (res == -1) {
            res = queryPos(2 * node + 1, mid + 1, end, idx);
        }
        return res;
    }
    
    private void pushBusy(long val) {
        int curr = busySz++;
        while (curr > 0) {
            int p = (curr - 1) >> 1;
            if (busyHeap[p] <= val) {
                break;
            }
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
            if (val <= busyHeap[child]) {
                break;
            }
            busyHeap[curr] = busyHeap[child];
            curr = child;
        }
        busyHeap[curr] = val;
        return res;
    }
}