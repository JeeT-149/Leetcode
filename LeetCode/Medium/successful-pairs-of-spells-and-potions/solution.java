class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i<n ; i++){
            long target = (success+spells[i]-1)/spells[i];
            int low = 0;
            int high = m-1;
            int firstvalid = m;
            while (low<=high){
                int mid = low + (high-low)/2;
                if (potions[mid]>= target){
                    firstvalid = mid;
                    high = mid-1;
                }
                else{
                    low = mid+1;
                }
            }
            pairs[i]=m-firstvalid;
        }
        return pairs;
    }
}