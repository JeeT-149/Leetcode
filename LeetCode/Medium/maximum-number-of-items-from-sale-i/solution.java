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

        int[] gain = new int[n];
        for (int i = 0; i < n; i++) {
            int f = factor[i];
            int count = 1;
            for (int j = 0; j < n; j++) {
                if (i != j && factor[j] % f == 0) {
                    count++;
                }
            }
            gain[i] = count;
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int p = price[i];
            if (p > budget) continue;
            int g = gain[i];
            for (int b = budget; b >= p; b--) {
                int prev = dp[b - p];
                if (prev != -1 && prev + g > dp[b]) {
                    dp[b] = prev + g;
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