class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int totalpoints = n+k-1;
        int pointstopick = 2*k;
        if (pointstopick > totalpoints) return 0;
        long numerator = 1;
        long denominator = 1;
        for (int i = 1; i<=pointstopick; i++){
            numerator = (numerator * (totalpoints-i+1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        long ans = (numerator * modpow(denominator, MOD-2,MOD))%MOD;
        return (int) ans;
    }
    private long modpow (long base, long exp, int mod){
        long res = 1;
        base = base%mod;
        while(exp>0){
            if((exp&1) ==1){
                res = (res*base)%mod;
            }
            base = (base*base) % mod;
            exp >>=1;
        }
        return res;
    }
}