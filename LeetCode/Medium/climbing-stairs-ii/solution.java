class Solution {
    public int climbStairs(int n, int[] costs) {
        int prev3 = 1000000000;
        int prev2 = 1000000000;
        int prev1 = 0;
        for (int i = 0; i< n; i++){
            int cost1 = prev1+1;
            int cost2 = prev2+4;
            int cost3 = prev3+9;
            int mincost = cost1;
            if (cost2<mincost) mincost = cost2;
            if (cost3<mincost) mincost = cost3;
            int curr = costs[i]+mincost;
            prev3 = prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}