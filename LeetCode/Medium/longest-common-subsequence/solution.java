class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        if (s1.length < s2.length){
            char[] temp = s1;
            s1 = s2;
            s2 = temp;
        }
            int[] dp = new int[s2.length+1];
            for (int i = 1; i<=s1.length;i++){
                int prev = 0;
                for (int j = 1; j<=s2.length;j++){
                    int temp = dp[j];
                    if (s1[i-1]==s2[j-1]){
                        dp[j]= prev+1;
                    }
                    else{
                        dp[j]= Math.max(dp[j],dp[j-1]);
                    }
                    prev=temp;
                }
            }
            return dp[s2.length];
    }
}