class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] rightmin = new int[n];
        rightmin[n-1]=nums[n-1];
        for (int i = n-2; i>= 0;i--){
            rightmin[i]= Math.min(rightmin[i+1], nums[i]);
        }
        int currentmax = nums[0];
        for (int i = 0; i<n;i++){
            if (nums[i]>currentmax){
                currentmax = nums[i];
            }
            if (currentmax-rightmin[i]<=k){
                return i;
            }
        }
        return -1;
    }
}