class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitcounts = new int[32];
        for (int num: nums){
            for (int i = 0; i<32; i++){
                if ((num & (1<<i)) != 0){
                    bitcounts[i]++;
                }
            }
        }
        long ans = 0;
        long MOD = 1_000_000_007;
        for (int i = 0; i<k; i++){
            long currentnum = 0;
            for (int j = 0; j<32; j++){
                if (bitcounts[j]>0){
                    currentnum |= (1L << j);
                    bitcounts[j]--;
                }
            }
            ans = (ans + (currentnum * currentnum ) % MOD) % MOD;
        }
        return (int) ans;
    }
}