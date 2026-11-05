class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distvaluecount = 0;
        for (int x : arr1){
            int left = 0;
            int right = arr2.length - 1;
            boolean isvalid = true;
            while (left <= right){
                int mid = left + (right - left) / 2;
                if (Math.abs(arr2[mid] - x) <= d){
                    isvalid = false;
                    break;
                }
                if (arr2[mid] < x){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
            if (isvalid){
                distvaluecount++;
            }
        }
        return distvaluecount;
    }
}