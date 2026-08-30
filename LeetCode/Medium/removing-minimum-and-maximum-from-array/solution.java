class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n<=2){
            return n;
        }
        int minindex = 0;
        int maxindex = 0;
        for (int i = 1;i<n;i++){
            if (nums[i]<nums[minindex]){
                minindex = i;
            }
            if (nums[i]>nums[maxindex]){
                maxindex = i;
            }
        }
        int left = Math.min(minindex,maxindex);
        int right = Math.max(minindex,maxindex);
        int frontcost = right+1;
        int backcost = n-left;
        int bothcost = (left+1)+(n-right);
        return Math.min(Math.min(frontcost, backcost),bothcost);
    }
}