class Solution {
    public int countCommas(int n) {
        int commas = 0;
        long step = 1000;
        while(n>=step){
            commas += (n-step+1);
            step*=1000;
        }
        return commas;
    }
}