class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasodd = false;
        for (int num : nums1){
            if (num<min){
                min = num;
            }
            if ((num&1) != 0){
                hasodd = true;
            }
        }
        return (min&1) != 0 || !hasodd;
    }
}