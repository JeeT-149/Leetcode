class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        int hottest = 0;
        for (int i = n-1; i>=0;i--){
            int currenttemp = temperatures[i];
            if (currenttemp >= hottest){
                hottest = currenttemp;
                continue;
            }
            int days = 1;
            while (temperatures [i+days] <= currenttemp){
                days += ans[i+days];
            }
            ans[i]=days;
        }
        return ans;
    }
}