class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int[] arr = new int[n];
        int maxval = 0;
        for (int i = 0; i<n;i++){
            arr[i]=nums.get(i);
            if(arr[i]>maxval){
                maxval = arr[i];
            }
        }
        int [] lastseen = new int[maxval+1];
        for (int k = 0; k<= maxval; k++){
            lastseen[k]=-1;
        }
        int totalsum = 0;
        for (int i = 0; i<n;i++){
            int distinctcount = 0;
            for (int j = i; j<n;j++){
                int val = arr[j];
                if (lastseen[val] != i){
                    distinctcount++;
                    lastseen[val]=i;
                }
                totalsum += distinctcount*distinctcount;
            }
        }
        return totalsum;
    }
}