class Solution {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int m = w1.length;
        int n = w2.length;
        int [] dp = new int [n+1];
        for (int j = 0; j<=n;j++){
            dp[j]=j;
        }
        for (int i = 1; i<= m;i++){
            int prev = dp[0];
            dp[0]=i;
            for (int j = 1; j<=n; j++){
                int temp= dp[j];
                if (w1[i-1]==w2[j-1]){
                    dp[j]=prev;
                }
                else{
                    dp[j]=1+Math.min(prev, Math.min(dp[j], dp[j-1]));
                }
                prev = temp;
            }
        }
        return dp[n];
    }
}