class Solution {
    public int numTilings(int n) {
        if (n==0 || n==1) return 1;
        if (n==2) return 2;
        int MOD = 1_000_000_007;
        long a = 1;
        long b = 1;
        long c = 2;
        for (int i = 3; i<=n;i++){
            long next = (2*c+a) % MOD;
            a = b;
            b = c;
            c = next;
        }
        return (int) c;
    }
}