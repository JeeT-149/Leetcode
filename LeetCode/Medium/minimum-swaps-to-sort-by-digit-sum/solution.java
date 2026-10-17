class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        for (int i = 0; i<n; i++){
            long num = nums[i];
            long sum = getDigitSum(num);
            arr[i]= (sum<<50) | (num<<18) | (long) i;
        }
        Arrays.sort(arr);
        int swaps = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i<n; i++){
            int originalindex = (int) (arr[i] & 0x3FFFFL);
            if (visited[i] || originalindex == i){
                continue;
            }
            int cyclesize = 0;
            int curr = i;
            while(!visited[curr]){
                visited[curr]=true;
                curr = (int) (arr[curr] & 0x3FFFFL);
                cyclesize++;
            }
            if (cyclesize > 0){
                swaps += (cyclesize-1);
            }
        }
        return swaps;
    }
    private long getDigitSum(long num){
        long sum = 0;
        while (num>0){
            sum += num%10;
            num /= 10;
        }
        return sum;
    }
}