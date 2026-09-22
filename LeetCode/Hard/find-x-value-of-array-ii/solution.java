import java.util.Arrays;

class Solution {
    // Flattened Segment Tree strictly using primitives
    private int[] tree_prod;
    private int[][] tree_freq;
    private int k;
    
    // Global accumulators to guarantee O(1) space allocation during queries
    private int[] acc_freq;
    private int[] temp_freq;
    private int acc_prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;
        
        // 4*N is the standard safe size for a flattened Segment Tree
        tree_prod = new int[4 * n + 1];
        tree_freq = new int[4 * n + 1][k];
        
        build(1, 0, n - 1, nums);
        
        int[] result = new int[queries.length];
        acc_freq = new int[k];
        temp_freq = new int[k];
        
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int targetX = queries[i][3];
            
            // 1. Persistently mutate the array
            update(1, 0, n - 1, idx, val);
            
            // 2. Clear our hardware accumulators for the new query
            Arrays.fill(acc_freq, 0);
            acc_prod = 1;
            
            // 3. Query the specific dynamic subrange [start, n - 1]
            if (start <= n - 1) {
                query(1, 0, n - 1, start, n - 1);
            }
            
            result[i] = acc_freq[targetX];
        }
        
        return result;
    }
    
    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int modVal = nums[l] % k;
            tree_prod[node] = modVal;
            tree_freq[node][modVal] = 1;
            return;
        }
        
        int mid = l + (r - l) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        
        build(leftNode, l, mid, nums);
        build(rightNode, mid + 1, r, nums);
        
        merge(node, leftNode, rightNode);
    }
    
    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int modVal = val % k;
            tree_prod[node] = modVal;
            
            // Reset state for this specific leaf
            Arrays.fill(tree_freq[node], 0);
            tree_freq[node][modVal] = 1;
            return;
        }
        
        int mid = l + (r - l) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;
        
        if (idx <= mid) {
            update(leftNode, l, mid, idx, val);
        } else {
            update(rightNode, mid + 1, r, idx, val);
        }
        
        merge(node, leftNode, rightNode);
    }
    
    private void merge(int node, int leftNode, int rightNode) {
        tree_prod[node] = (tree_prod[leftNode] * tree_prod[rightNode]) % k;
        
        // Inherit prefixes from the left chunk
        for (int i = 0; i < k; i++) {
            tree_freq[node][i] = tree_freq[leftNode][i];
        }
        
        // Append right chunk prefixes, scaling them by the left chunk's total product
        int leftProd = tree_prod[leftNode];
        for (int i = 0; i < k; i++) {
            if (tree_freq[rightNode][i] > 0) {
                int combinedMod = (leftProd * i) % k;
                tree_freq[node][combinedMod] += tree_freq[rightNode][i];
            }
        }
    }
    
    private void query(int node, int l, int r, int ql, int qr) {
        // If the chunk is completely engulfed by our query range, merge it into our global accumulator
        if (ql <= l && r <= qr) {
            
            // Fast primitive copy to avoid overwriting during evaluation
            System.arraycopy(acc_freq, 0, temp_freq, 0, k);
            
            for (int i = 0; i < k; i++) {
                if (tree_freq[node][i] > 0) {
                    int combinedMod = (acc_prod * i) % k;
                    temp_freq[combinedMod] += tree_freq[node][i];
                }
            }
            
            System.arraycopy(temp_freq, 0, acc_freq, 0, k);
            acc_prod = (acc_prod * tree_prod[node]) % k;
            
            return;
        }
        
        int mid = l + (r - l) / 2;
        // Strictly evaluate left-to-right to maintain prefix continuity
        if (ql <= mid) {
            query(2 * node, l, mid, ql, qr);
        }
        if (qr > mid) {
            query(2 * node + 1, mid + 1, r, ql, qr);
        }
    }
}