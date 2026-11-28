import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] factor = new int[n];
        int[] price = new int[n];
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            factor[i] = items[i][0];
            price[i] = items[i][1];
            if (price[i] < minPrice) {
                minPrice = price[i];
            }
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int p = price[i];
            if (p > budget) continue;

            int f = factor[i];
            int freeCap = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && factor[j] % f == 0) {
                    freeCap++;
                }
            }

            int maxUseful = budget / p;
            if (freeCap > maxUseful) {
                freeCap = maxUseful;
            }

            // Binary-split the bonus-eligible copies into O(log(freeCap)) 0-1 bundles
            int rem = freeCap;
            for (int k = 1; rem > 0; k <<= 1) {
                int take = k < rem ? k : rem;
                rem -= take;

                int w = take * p;
                int v = take << 1; // Each bonus-eligible copy gives 1 bought + 1 free = 2
                for (int b = budget; b >= w; b--) {
                    int prev = dp[b - w];
                    if (prev != -1 && prev + v > dp[b]) {
                        dp[b] = prev + v;
                    }
                }
            }
        }

        int maxItems = 0;
        for (int b = 0; b <= budget; b++) {
            if (dp[b] != -1) {
                int total = dp[b] + (budget - b) / minPrice;
                if (total > maxItems) {
                    maxItems = total;
                }
            }
        }

        return maxItems;
    }
}