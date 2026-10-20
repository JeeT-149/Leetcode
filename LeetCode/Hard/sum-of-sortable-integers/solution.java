class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 1; i<=n; i++){
            if (n%i==0){
                if(isvalid (nums,i,n)){
                    sum+=i;
                }
            }
        }
        return sum;
    }
    private boolean isvalid (int[] nums, int k, int n){
        int prevmax = Integer.MIN_VALUE;
        for (int i = 0; i<n; i+=k){
        int start = i; 
        int end = i+k-1;
        int drops = 0;
        int dropidx = -1;
        for (int j = start; j<end; j++){
            if (nums[j]>nums[j+1]){
                drops++;
                dropidx = j;
            }
        }
        if (drops>1){
            return false;
        }
        if (drops == 1 && nums[end] > nums[start]) {
                return false; 
            }
            
            int currentMin;
            int currentMax;
            
            if (drops == 0) {
                currentMin = nums[start];
                currentMax = nums[end];
            } else {
                currentMin = nums[dropidx + 1];
                currentMax = nums[dropidx];
            }
            
            if (currentMin < prevmax) {
                return false; 
            }
            
            prevmax = currentMax;
        }
        return true;
    }
}