class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        long[] pairs = new long[n];
        for (int i = 0; i<n ; i++){
            pairs[i]=((long) nums[i]<<32) | i;
        }
        Arrays.sort(pairs);
        int[] res = new int[n];
        int[] tempindices = new int[n];
        int start = 0;
        while(start<n){
            int end = start + 1;
            tempindices[0]=(int) pairs[start];
            while(end < n){
                int preval = (int) (pairs[end-1]>>>32);
                int currval = (int) (pairs[end]>>>32);
                if(currval-preval <= limit){
                    tempindices[end-start]=(int) pairs[end];
                    end++;
                }
                else{
                    break;
                }
            }
            Arrays.sort(tempindices, 0, end-start);
            for (int i = 0; i<end-start;i++){
                int originalindex = tempindices[i];
                int sortedvalue = (int) (pairs[start+i] >>>32);
                res[originalindex]=sortedvalue;
            }
            start=end;
        }
        return res;
    }
}