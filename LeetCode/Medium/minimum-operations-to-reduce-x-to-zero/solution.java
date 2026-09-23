class Solution {
    public int minOperations(int[] nums, int x) {
        int totalsum = 0;
        for (int num : nums){
            totalsum += num;
        }
        int target = totalsum-x;
        if (target==0) return nums.length;
        if (target<0) return -1;
        int maxlen = -1;
        int currentsum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++){
            currentsum += nums[right];
            while (currentsum > target && left <= right){
                currentsum -= nums[left];
                left++;
            }
            if (currentsum==target){
                int currentlen = right-left +1;
                if (currentlen>maxlen){
                    maxlen = currentlen;
                }
            }
        }
        return maxlen == -1 ? -1 : nums.length - maxlen;
    }
}