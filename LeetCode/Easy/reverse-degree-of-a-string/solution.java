class Solution {
    public int reverseDegree(String s) {
        char[] arr = s.toCharArray();
        int degree = 0;
        for (int i = 0; i<arr.length; i++){
            degree+= (i+1)*(123-arr[i]);
        }
        return degree;
    }
}