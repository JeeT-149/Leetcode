class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i<n; i++){
            arr[i]=nums.get(i);
        }
        int minsum = Integer.MAX_VALUE;
        for (int i = 0; i< n; i++){
            int sum = 0;
            for (int j = i ; j<n && (j-i+1) <= r; j++){
                sum+=arr[j];
                if (j-i+1>= l && sum>0){
                    if (sum<minsum){
                        minsum = sum;
                    }
                }
            }
        }
        if (minsum == Integer.MAX_VALUE){
            return -1;
        }
        return minsum;
    }
}