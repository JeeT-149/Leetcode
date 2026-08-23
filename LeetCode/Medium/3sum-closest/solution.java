class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestsum = nums[0]+nums[1]+nums[2];
        for (int i = 0;i<nums.length - 2; i++){
            int left = i+1;
            int right = nums.length-1;
            while(left<right){
                int currentsum = nums[i]+nums[left]+nums[right];
                if (currentsum == target){
                    return currentsum;
                }
                if (Math.abs(target-currentsum)<Math.abs(target-closestsum)){
                    closestsum = currentsum;
                }
                if (currentsum<target){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        return closestsum;
    }
}