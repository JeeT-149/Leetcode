class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            long prod = nums[i];
            long g = nums[i];
            long l = nums[i];

            for (int j = i + 1; j < n; j++) {
                long v = nums[j];
                prod *= v;
                g = gcd(g, v);
                l = (l / gcd(l, v)) * v;

                if (prod == l * g) {
                    int len = j - i + 1;
                    if (len > maxLen) {
                        maxLen = len;
                    }
                } else if (prod > l * g) {
                    break;
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}