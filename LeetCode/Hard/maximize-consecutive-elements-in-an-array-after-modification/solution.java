import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int prev = -10;
        int dp0 = 0;
        int dp1 = 0;
        int dp2 = 0;
        int maxLen = 1;

        for (int x : nums) {
            int diff = x - prev;

            if (diff == 0) {
                dp2 = dp1 + 1;
                dp1 = dp0 + 1;
            } else if (diff == 1) {
                dp0 = dp1;
                dp1 = dp2;
                dp2 = dp1 + 1;
                dp1 = dp0 + 1;
            } else if (diff == 2) {
                dp0 = dp2;
                dp2 = 1;
                dp1 = dp0 + 1;
            } else {
                dp0 = 0;
                dp1 = 1;
                dp2 = 1;
            }

            prev = x;
            if (dp1 > maxLen) maxLen = dp1;
            if (dp2 > maxLen) maxLen = dp2;
        }

        return maxLen;
    }
}