class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1+k2;
        int[] count = new int [100001];
        int maxdiff = 0;
        for (int i = 0; i<n; i++){
            int diff = Math.abs(nums1[i]-nums2[i]);
            count[diff]++;
            if (diff>maxdiff){
                maxdiff = diff;
            }
        }
        for (int i = maxdiff; i>0 && k>0 ; i--){
            if (count[i]>0){
                long decrement = Math.min((long) count[i],k);
                count[i]-=decrement;
                count[i-1]+=decrement;
                k-=decrement;
            }
        }
        long ans = 0;
        for (int i = 0; i<= maxdiff; i++){
            if(count[i]>0){
                ans += (long) i*i*count[i];
            }
        }
        return ans;
    }
}