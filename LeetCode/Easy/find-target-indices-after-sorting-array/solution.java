class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;
        int countsmaller = 0;
        int countequal = 0;
        for (int i = 0; i<n; i++){
            if (nums[i]<target){
                countsmaller++;
            }
            else if (nums[i]==target){
                countequal++;
            }
        }
        List<Integer> res = new ArrayList<>(countequal);
        for (int i=0; i<countequal; i++){
            res.add(countsmaller+i);
        }
        return res;
    }
}