class Solution {
    private static final int MOD = 1000000007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        table = new long[n][n];
        for (int i = 0; i<n; i++){
            table[i][0]=1;
            table[i][i]=1;
            for (int j = 1; j<i; j++){
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }
        long totalways = solve(nums);
        return (int) ((totalways - 1 + MOD) % MOD);
    }
    private long solve (int[] arr){
        int m = arr.length;
        if (m<=2){
            return 1;
        }
        int root = arr[0];
        int leftcount = 0;
        for (int i = 1; i< m; i++){
            if (arr[i]<root){
                leftcount++;
            }

        }
        int rightcount = m-1-leftcount;
        int[] left = new int[leftcount];
        int[] right = new int[rightcount];
        int lptr = 0;
        int rptr = 0;
        for (int i = 1; i<m; i++){
            if (arr[i]<root){
                left[lptr]=arr[i];
            }
            else{
                right[rptr++]=arr[i];
            }
        }
        long leftways = solve(left);
        long rightways = solve(right);
        long interleaved = table[m-1][leftcount];
        long total = (interleaved * leftways) % MOD;
        total = (total * rightways) % MOD;
        return total;
    }
}