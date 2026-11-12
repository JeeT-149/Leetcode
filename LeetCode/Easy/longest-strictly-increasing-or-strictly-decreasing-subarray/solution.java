class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxlen = 1;
        int declen = 1;
        int inclen = 1;
        for (int i = 1; i< nums.length; i++){
            if (nums[i] > nums[i-1]){
                inclen ++;
                declen = 1;
            }
            else if (nums[i] < nums[i-1]){
                declen ++;
                inclen = 1;
            }
            else {
                inclen = 1;
                declen = 1;
            }
            if (inclen > maxlen){
                maxlen = inclen;
            }
            if (declen > maxlen){
                maxlen = declen;
            }
        }
        return maxlen;
    }
}