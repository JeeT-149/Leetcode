import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int p = items[i][1];
            if (f > maxFactor) maxFactor = f;
            if (p < minPrice) minPrice = p;
        }

        // Count factor frequencies and accumulate multiples via harmonic sieve
        int[] freq = new int[maxFactor + 1];
        for (int i = 0; i < n; i++) {
            freq[items[i][0]]++;
        }

        int[] multCount = new int[maxFactor + 1];
        for (int d = 1; d <= maxFactor; d++) {
            if (freq[d] == 0) continue;
            int sum = 0;
            for (int m = d; m <= maxFactor; m += d) {
                sum += freq[m];
            }
            multCount[d] = sum - 1; // exclude self (j != i)
        }

        // Pack (price, freeCap) into a single primitive long[] to sort without objects
        long[] packed = new long[n];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int p = items[i][1];
            packed[i] = ((long) p << 32) | (multCount[f] & 0xFFFFFFFFL);
        }
        Arrays.sort(packed);

        long spent = 0;
        long bonus = 0;
        long maxItems = budget / minPrice;

        for (int i = 0; i < n; i++) {
            long p = packed[i] >>> 32;
            long cap = packed[i] & 0xFFFFFFFFL;
            if (cap == 0) continue;
            if (spent + p > budget) break;

            long canBuy = (budget - spent) / p;
            long take = cap < canBuy ? cap : canBuy;

            spent += take * p;
            bonus += take;

            long total = (bonus << 1) + (budget - spent) / minPrice;
            if (total > maxItems) {
                maxItems = total;
            }
        }

        return (int) maxItems;
    }
}