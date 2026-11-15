class Solution {
    public int numOfSubarrays(int[] arr) {
        long ans = 0;
        int oddcount = 0;
        int evencount = 1;
        int parity = 0;
        for (int x : arr){
            parity ^= (x&1);
            if (parity == 1){
                ans += evencount;
                oddcount++;
            }
            else{
                ans += oddcount;
                evencount++;
            }
        }
        return (int) (ans % 1000000007);
    }
}