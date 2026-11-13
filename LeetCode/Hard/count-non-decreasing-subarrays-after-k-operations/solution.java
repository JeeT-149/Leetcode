class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] val = new int[n];
        int[] len = new int[n];
        int head = n;
        int tail = n;
        int R = n-1;
        long cost = 0;
        long ans = 0;
        for (int L = n-1; L>=0; L--){
            int v = nums[L];
            int mergedlen = 1;
            while (head < tail && val[head] <= v){
                cost += (long) (v-val[head]) * len[head];
                mergedlen += len[head];
                head++;
            }
            head--;
            val[head] = v;
            len[head] = mergedlen;
            while (cost>k){
                int rval = nums[R];
                int maxval = val[tail - 1];
                cost -= (maxval - rval);
                len[tail-1]--;
                if (len[tail-1]==0){
                    tail--;
                }
                R--;
            }
            ans += (R-L+1);
        }
        return ans;
    }
}