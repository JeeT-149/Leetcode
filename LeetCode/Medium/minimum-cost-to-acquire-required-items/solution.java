class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long c3 = costBoth;
        long n1 = need1;
        long n2 = need2;
        long opt1 = (n1 * c1)+(n2*c2);
        long opt2 = n1 * c3;
        if (n2>n1){
            opt2 += (n2-n1)*c2;
        }
        long opt3 = n2 * c3;
        if (n1 > n2){
            opt3 += (n1-n2)*c1;
        }
        long mincost = opt1;
        if (opt2<mincost) mincost = opt2;
        if (opt3<mincost) mincost = opt3;
        return mincost;
    }
}