class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int p = -1;
        for (int i = 0; i<n; i++){
            if (nums[i]==0){
                p=i;
                break;
            }
        }
        boolean isinc = true;
        boolean isdec = true;
        for (int i = 1; i<n; i++){
            if (nums[(p+i) % n] != i){
                isinc = false;
            }
            if (nums[(p-i+n) % n] != i){
                isdec = false;
            }
            if (!isinc && !isdec){
                break;
            }
        }
        int minops = Integer.MAX_VALUE;
        if (isinc){
            minops = Math.min(minops, Math.min(p, n-p+2));
        }
        if (isdec){
            minops = Math.min(minops, Math.min(p+2, n-p));
        }
        return minops == Integer.MAX_VALUE ? -1 : minops;
    }
}