class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int [] merged = new int [n+m];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i<m && j<n){
            if(nums1[i]<nums2[j]){
                merged[k] = nums1[i];
                i++;
            }
            else{
                merged[k] = nums2[j];
                j++;
            }k++;
        }
        while (i<m){
            merged[k] = nums1[i];
            i++;
            k++;
        }
        while (j<n){
            merged[k] = nums2[j];
            j++;
            k++;
        }
        int totallength = merged.length;
        if (totallength % 2 == 1){
            return (double) merged[totallength/2];
        }
        else{
            int mid1 = merged[(totallength/2) - 1];
            int mid2 = merged[totallength/2];
            return ((double)mid1 + (double)mid2) / 2.0;
        }
    }
}