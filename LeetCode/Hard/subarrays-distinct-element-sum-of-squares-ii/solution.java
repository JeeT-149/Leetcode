class Solution {
    private static final int MOD = 1000000007;
    private long[] sqSum;
    private long[] sum;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sqSum = new long[4*n];
        sum = new long[4*n];
        lazy = new long[4*n];
        int maxval = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > maxval) {
                maxval = nums[i];
            }
        }
        int[] lastseen = new int[maxval+1];
        Arrays.fill(lastseen, -1);
        long totalresult = 0;
        for (int i = 0; i<n; i++){
            int val = nums[i];
            int left = lastseen[val]+1;
            int right = i;
            update(1, 0, n - 1, left, right);
            totalresult = (totalresult+sqSum[1])%MOD;
            lastseen[val]=i;
        }
        return (int) totalresult;
    }
    private void update(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            applyLazy(node, start, end, 1);
            return;
        }
        
        pushDown(node, start, end);
        
        int mid = start + (end - start) / 2;
        if (l <= mid) {
            update(2 * node, start, mid, l, r);
        }
        if (r > mid) {
            update(2 * node + 1, mid + 1, end, l, r);
        }
        
        pushUp(node);
    }
    
    private void applyLazy(int node, int start, int end, long val) {
        long len = end - start + 1;
        long newSqSum = (sqSum[node] + 2 * val * sum[node] % MOD + len * val % MOD * val % MOD) % MOD;
        long newSum = (sum[node] + len * val % MOD) % MOD;
        
        sqSum[node] = newSqSum;
        sum[node] = newSum;
        lazy[node] = (lazy[node] + val) % MOD;
    }
    
    private void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            int mid = start + (end - start) / 2;
            applyLazy(2 * node, start, mid, lazy[node]);
            applyLazy(2 * node + 1, mid + 1, end, lazy[node]);
            lazy[node] = 0;
        }
    }
    
    private void pushUp(int node) {
        sqSum[node] = (sqSum[2 * node] + sqSum[2 * node + 1]) % MOD;
        sum[node] = (sum[2 * node] + sum[2 * node + 1]) % MOD;
    }
}