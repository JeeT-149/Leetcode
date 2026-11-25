import java.util.Arrays;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] tmp = new int[n];

        for (int i = 0; i < n; i++) sa[i] = order[i];
        rank[sa[0]] = 0;
        for (int i = 1; i < n; i++) {
            rank[sa[i]] = rank[sa[i - 1]] + (nums[sa[i]] != nums[sa[i - 1]] ? 1 : 0);
        }

        int[] cnt = new int[n + 1];
        for (int k = 1; rank[sa[n - 1]] < n - 1; k <<= 1) {
            int maxRank = rank[sa[n - 1]] + 1;

            int idx = 0;
            for (int i = n - k; i < n; i++) tmp[idx++] = i;
            for (int i = 0; i < n; i++) {
                if (sa[i] >= k) tmp[idx++] = sa[i] - k;
            }

            Arrays.fill(cnt, 0, maxRank + 1, 0);
            for (int i = 0; i < n; i++) cnt[rank[tmp[i]]]++;
            for (int i = 1; i < maxRank; i++) cnt[i] += cnt[i - 1];
            for (int i = n - 1; i >= 0; i--) {
                sa[--cnt[rank[tmp[i]]]] = tmp[i];
            }

            tmp[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                int prev = sa[i - 1];
                int curr = sa[i];
                int r1Prev = rank[prev];
                int r1Curr = rank[curr];
                int r2Prev = prev + k < n ? rank[prev + k] : -1;
                int r2Curr = curr + k < n ? rank[curr + k] : -1;
                tmp[curr] = tmp[prev] + ((r1Prev != r1Curr || r2Prev != r2Curr) ? 1 : 0);
            }
            System.arraycopy(tmp, 0, rank, 0, n);
        }

        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) {
                    h++;
                }
                lcp[rank[i]] = h;
                if (h > 0) h--;
            }
        }

        int ans = n;
        for (int i = 0; i < n; i++) {
            int leftLcp = lcp[i];
            int rightLcp = (i + 1 < n) ? lcp[i + 1] : 0;
            int need = (leftLcp > rightLcp ? leftLcp : rightLcp) + 1;
            if (sa[i] + need <= n && need < ans) {
                ans = need;
            }
        }

        return ans;
    }
}