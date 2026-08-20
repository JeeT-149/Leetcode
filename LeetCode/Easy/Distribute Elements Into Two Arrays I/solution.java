class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        arr1[0]=nums[0];
        arr2[0]=nums[1];
        int count1 = 1;
        int count2 = 1;
        for(int i = 2; i<n;i++){
            if(arr1[count1-1]>arr2[count2-1]){
                arr1[count1]=nums[i];
                count1++;
            }
            else{
                arr2[count2]=nums[i];
                count2++;
            }
        }
        int[] result = new int[n];
        System.arraycopy(arr1,0,result,0,count1);
        System.arraycopy(arr2,0,result,count1,count2);
        return result;
    }
}