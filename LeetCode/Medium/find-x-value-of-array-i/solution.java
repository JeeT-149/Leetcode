class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] currentfreq = new long[k];
        long[] nextfreq = new long[k];
        for (int num : nums){
            Arrays.fill(nextfreq, 0);
            int val = num%k;
            nextfreq[val]++;
            for (int i = 0; i<k; i++){
                if(currentfreq[i]>0){
                    int nextmod = (i*val)%k;
                    nextfreq[nextmod] += currentfreq[i];
                }
            }
            for (int i = 0; i<k ;i++){
                result[i] += nextfreq[i];
            }
            long[] temp = currentfreq;
            currentfreq = nextfreq;
            nextfreq = temp;
        }
        return result;
    }
}