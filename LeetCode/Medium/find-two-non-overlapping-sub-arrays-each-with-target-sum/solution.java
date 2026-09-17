class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        int INF = Integer.MAX_VALUE /2;
        Arrays.fill(best, INF);
        int sum = 0;
        int left = 0;
        int currentbest = INF;
        int minsumlength = INF;
        for (int right = 0 ; right<n; right++){
            sum += arr[right];
            while(sum>target && left<=right){
                sum -= arr[left];
                left++;
            } 
            if (sum == target){
                int currentlength = right-left+1;
                if(left>0 && best[left-1] != INF){
                    minsumlength = Math.min(minsumlength, currentlength + best[left-1]);
                }
                currentbest = Math.min(currentlength, currentbest);
            }
            best[right]= currentbest;
        }
        return minsumlength >= INF ? -1 : minsumlength;
    }
}