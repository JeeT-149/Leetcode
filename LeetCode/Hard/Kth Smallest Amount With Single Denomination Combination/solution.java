class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numsubsets = (1<<n)-1;
        long[] lcms = new long[numsubsets];
        int[] signs = new int[numsubsets];
        long mincoin = Long.MAX_VALUE;
        int idx = 0;
        for (int i = 1; i<= numsubsets; i++){
            long currentlcm = 1;
            int setbits = 0;
            for (int j = 0; j<n;j++){
                if ((i & (1<<j)) != 0){
                    currentlcm = lcm(currentlcm, coins[j]);
                    setbits++;
                    mincoin = Math.min(mincoin, coins[j]);
                }
            }
            lcms[idx] = currentlcm;
            signs[idx] = (setbits % 2 == 1) ? 1 : -1;
            idx++;
        }
        long low = 1;
        long high = mincoin * k;
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (countValidMultiples(mid, lcms, signs) >= k) {
                ans = mid; 
                high = mid - 1;
            } else {
                low = mid + 1; 
            }
        }
        return ans;
    }
    private long countValidMultiples(long target, long[] lcms, int[] signs) {
        long count = 0;
        for (int i = 0; i < lcms.length; i++) {
            count += signs[i] * (target / lcms[i]);
        }
        return count;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}