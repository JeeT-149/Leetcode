class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[]used = new boolean[nums.length];
        backtrack (result, current, nums, used);
        return result;
    }
    private void backtrack (List<List<Integer>> result, List<Integer> current, int[] nums,boolean[] used){
        if (current.size()==nums.length){
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0;i<nums.length;i++){
            if(used[i]){
                continue;
            }
            if(i > 0 && nums[i]==nums[i-1]&& !used[i-1]){
                continue;
            }
            current.add(nums[i]);
            used[i]=true;
            backtrack(result, current,nums, used);
            current.remove(current.size()-1);
            used[i]=false;
        }
    }
}