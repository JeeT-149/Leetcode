class Solution {
    // Precompute all divisors statically for the JVM to avoid runtime modulo operations
    private static final int[][] divs = new int[201][];
    static {
        for (int i = 1; i <= 200; i++) {
            int count = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) count++;
            }
            divs[i] = new int[count];
            int idx = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0) divs[i][idx++] = j;
            }
        }
    }

    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[][] cost = new int[n][n];

        // Step 1: Compute minimum change cost for every possible substring block
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minSubCost = 1000000;
                
                for (int d : divs[len]) {
                    int changes = 0;
                    int groupLen = len / d;
                    
                    for (int start = 0; start < d; start++) {
                        for (int p1 = 0; p1 < groupLen / 2; p1++) {
                            int left = i + start + p1 * d;
                            int right = i + start + (groupLen - 1 - p1) * d;
                            if (arr[left] != arr[right]) {
                                changes++;
                            }
                        }
                    }
                    if (changes < minSubCost) {
                        minSubCost = changes;
                    }
                }
                cost[i][j] = minSubCost;
            }
        }

        int[] dp = new int[n + 1];
        
        // Base state setup for p = 1 partition
        for (int i = 1; i <= n; i++) {
            dp[i] = cost[0][i - 1];
        }

        // Step 2: Compress DP states for partitions 2 to K in-place
        for (int p = 2; p <= k; p++) {
            for (int i = n; i >= p; i--) {
                int minVal = 1000000;
                
                for (int j = p - 1; j < i; j++) {
                    int val = dp[j] + cost[j][i - 1];
                    if (val < minVal) {
                        minVal = val;
                    }
                }
                dp[i] = minVal;
            }
        }

        return dp[n];
    }
}