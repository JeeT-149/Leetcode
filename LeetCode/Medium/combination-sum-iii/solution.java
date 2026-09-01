class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        int[] path = new int[k];
        dfs(k,n,1,0,path,result);
        return result;
    }
    private void dfs(int k, int target, int start, int index, int[] path, List<List<Integer>> result){
        if (target<0) return;
        if (index == k){
            if (target==0){
                List<Integer> validcombo = new ArrayList<>();
                for (int num : path){
                    validcombo.add(num);
                }
                result.add(validcombo);
            }
            return;
        }
        for (int i = start; i<= 9;i++){
            path[index]=i;
            dfs(k,target-i,i+1,index+1,path,result);
        }
    }
}