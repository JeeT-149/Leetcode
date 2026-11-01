class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums){
            if (num<min){
                min = num;
            }
        }
        int totalmoves = 0;
        for (int num: nums){
            totalmoves += (num-min);

        }
        return totalmoves;
    }
}