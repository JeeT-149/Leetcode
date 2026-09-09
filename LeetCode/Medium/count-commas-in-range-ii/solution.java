class Solution {
    public long countCommas(long n) {
        long commas = 0;
        long step = 1000;
        while(n>=step){
            commas += (n-step+1);
            step*=1000;
        }
        return commas;
    }
}